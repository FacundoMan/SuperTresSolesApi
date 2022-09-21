package com.springboot.TresSolesApi.Controlador;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.Rol;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Modelo.DTO.RegistroDTO;
import com.springboot.TresSolesApi.Service.CategoriaService;
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.ProductoService;
import com.springboot.TresSolesApi.Service.RolService;
import com.springboot.TresSolesApi.Service.UsuarioService;
import com.springboot.TresSolesApi.Utilidad.ConvertirDTOaNormal;

@RestController
@RequestMapping("api/Gerente")
@PreAuthorize("hasRole('ROLE_GERENTE')")
public class ControllerGerente {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private RolService rolService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	PedidoService pedidoService;
	@Autowired
	ProductoService serviceProducto;
	@Autowired
	CategoriaService serviceCategoria;
	
	@PostMapping("/registrarPicker")
	public ResponseEntity<?> registrarPicking(@RequestBody RegistroDTO registroDTO){
		Map<String,Object>response=new HashMap<>();
		try {
		if(usuarioService.existsByNombreUsuario(registroDTO.getNombreUsuario()))throw new SupermercadoException("Ese usuario ya existe.");
		//Convierto del DTO a usuario y verifico los datos
		Usuario usuario=ConvertirDTOaNormal.registroDtoAUsuario(registroDTO);
		
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		//Se busca el rol en la bd y se le setea, en este caso uso singleton
		Rol roles=rolService.findByNombre("ROLE_PICKER").get();
		usuario.setRoles(Collections.singleton(roles));
		usuarioService.addNuevoUsuario(usuario);
		response.put("Mensaje","El picker se registro existosamente: "+usuario.getNombre()+" "+usuario.getApellido());	
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/registrarRepartidor")
	public ResponseEntity<?> registrarRepartidor(@RequestBody RegistroDTO registroDTO){
		Map<String,Object>response=new HashMap<>();
		try {
		if(usuarioService.existsByNombreUsuario(registroDTO.getNombreUsuario()))throw new SupermercadoException("Ese usuario ya existe.");
		//Convierto del DTO a usuario y verifico los datos
		Usuario usuario=ConvertirDTOaNormal.registroDtoAUsuario(registroDTO);
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		//Se busca el rol en la bd y se le setea, en este caso uso singleton
		Rol roles=rolService.findByNombre("ROLE_REPARTIDOR").get();
		usuario.setRoles(Collections.singleton(roles));
		usuarioService.addNuevoUsuario(usuario);
		response.put("Mensaje","El repartidor se registro existosamente: "+usuario.getNombre()+" "+usuario.getApellido());	
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/obtenerUsuarios")
	public List<Usuario> getAllUsuarios(){
		return usuarioService.findAllUsuarios();
	}
	
	@GetMapping("/obtenerUsuariosByIdRol/{id}")
	public List<Usuario> getUsuariosByIdRol(@PathVariable Long id){
		return usuarioService.findByIdRolUsuarios(id);		
	}
	
	@GetMapping("/obtenerUsuariosByRol/{nombre}")
	public List<Usuario> getUsuariosByIdRol(@PathVariable String nombre){
		return usuarioService.findByRolUsuarios(nombre);		
	}
	
	@GetMapping("/obtenerUsuario/{id}")
	public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
		try {
			Usuario u=usuarioService.findUsuarioById(id);
			return new ResponseEntity<Usuario>(u, HttpStatus.OK);
		} catch (SupermercadoException e) {
			Map<String,Object>response=new HashMap<>();
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/obtenerEmpleados")
	public List<Usuario> getEmpleados(){
		return usuarioService.findEmpleados();
	}
	
	
	@PutMapping("/modificarProducto/{id}")
	public ResponseEntity<?> modificarProducto(@RequestBody Producto p,@PathVariable Long id){
		Map<String,Object>response=new HashMap<>();
	try {
		serviceProducto.modificarProducto(id, p);	
		response.put("Mensaje","Se modifico correctamente el producto");
	} catch (SupermercadoException e) {
		response.put("Mensaje",e.getMessage());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	@PostMapping("/agregarProducto")
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
			p.setOferta(producto.getOferta());
	
			for(Categoria c:producto.getCategorias()) {	
				Categoria cNew=new Categoria();
				if(c.getId()!=null) {
					cNew=serviceCategoria.getCategoriaById(c.getId());
				}else if(c.getNombre()!=null) {
					cNew=serviceCategoria.getCategoriaByNombre(c.getNombre());
				}
				
				if(cNew==null)throw new SupermercadoException("No se encontro la categoria seleccionada");
				p.agregarCategoria(cNew);
			}
			
		
			
			serviceProducto.addProducto(p);
			response.put("Mensaje","Se creo correctamente el producto");
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
	
}
