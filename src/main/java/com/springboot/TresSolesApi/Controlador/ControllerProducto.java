package com.springboot.TresSolesApi.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Modelo.Oferta;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Service.CategoriaService;
import com.springboot.TresSolesApi.Service.OfertaService;
import com.springboot.TresSolesApi.Service.ProductoService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/api/Producto")
public class ControllerProducto {
	@Autowired
	ProductoService service;
	@Autowired
	OfertaService serviceOferta;
	@Autowired
	CategoriaService serviceCategoria;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Producto producto) {
		Map<String,Object>response=new HashMap<>();
		
		try {
			producto.verificarProducto();
			Producto p = new Producto();
			p.setNombre(producto.getNombre());
			p.setPrecio(producto.getPrecio());
			p.setBorrado(false);
			p.setDescripcion(producto.getDescripcion());
			p.setUrlImagen(producto.getUrlImagen());
			//La oferta puede ser vacia
			if(producto.getOferta()!=null) {
			Oferta o=serviceOferta.getOfertaById(producto.getOferta().getId());
			if(o==null)throw new SupermercadoException("No se encontro la oferta seleccionada");
			p.setOferta(o);
			}
			
			for(Categoria c:producto.getCategorias()) {	
				Categoria cNew=new Categoria();
				if(c.getId()!=null) {
					cNew=serviceCategoria.getCategoriaById(c.getId());
				}else if(c.getNombre()!=null) {
					cNew=serviceCategoria.getCategoriaByNombre(c.getNombre());
				}
				
				if(cNew==null)throw new SupermercadoException("No se encontro la categoria seleccionada");
				p.addNuevaCategoria(cNew);
			}
			
		
			
			service.addProducto(p);
			response.put("Mensaje","Se creo correctamente el producto");
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getProductos")
	public List<Producto> getAll(){
		return service.getAllProducto();
	}
	
	
	@GetMapping("/getProducto/{id}")
	public Producto getById(@PathVariable Long id) {
		return service.getProductoById(id);
	}
	
	@GetMapping("/getProductos/{idCategoria}")
	public List<Producto> getProductosByCategoriaId(@PathVariable Long idCategoria){
		return service.getProductosByCategoria(idCategoria);
	}
	
	@GetMapping("/getProductosOfertas")
	public List<Producto> getProductosConOfertas(){
		return service.getProductosOfertas();
	}
}
