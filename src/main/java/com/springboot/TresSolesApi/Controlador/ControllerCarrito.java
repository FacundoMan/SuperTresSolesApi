package com.springboot.TresSolesApi.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Carrito;
import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Service.CarritoService;
import com.springboot.TresSolesApi.Service.UsuarioService;
import com.springboot.TresSolesApi.Utilidad.Utilidades;


@RestController
@EnableAutoConfiguration
@RequestMapping(value="/api/Carrito")
@PreAuthorize("hasRole('ROLE_USER')")
public class ControllerCarrito {
	@Autowired
	CarritoService carritoService;
	@Autowired
	UsuarioService serviceUsuario;
	
	@PostMapping("/agregarAlCarrito")
	public ResponseEntity<?> add(@RequestBody LineaDeCarrito lineaCarrito){
	
		Map<String,Object>response=new HashMap<>();
		try {
		Utilidades u=new Utilidades();
		//Busco el carrito del usuario o lo creo en el mismo service
		Long id=obtenerIdUsuarioConectado();
		Carrito carrito=carritoService.getCarritoByUser(id);
		LineaDeCarrito lcarrito=new LineaDeCarrito();
		lcarrito.setCantidad(lineaCarrito.getCantidad());
		lcarrito.setCarrito(carrito);
		lcarrito.setProducto(lineaCarrito.getProducto());
		carritoService.addLineaCarrito(lcarrito);
		response.put("Mensaje","Se agrego correctamente");
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/obtenerProductosCarrito")
	public List<LineaDeCarrito> getListaCarrito(){
		Carrito carrito;
		try {
			carrito = carritoService.getCarritoByUser(obtenerIdUsuarioConectado());
			return carritoService.getListaCarrito(carrito.getId());
		} catch (SupermercadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@DeleteMapping("/borrarLinea/{id}")
	public ResponseEntity<?> borrarLineaById(@PathVariable Long id) {
		Map<String,Object>response=new HashMap<>();
		try {
			
			if(!carritoService.existeLineaDeCarritoById(id))throw new SupermercadoException("Ese id no existe");
			if(!carritoService.comprobarLineaCarritoUser(obtenerIdUsuarioConectado(), id)) throw new SupermercadoException("No puede borrar un Linea de carrito que no es de tu usuario");
			carritoService.borrarLineaCarrito(id);
			response.put("Mensaje","Se borro correctamente");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	private Long obtenerIdUsuarioConectado() {
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usuario=((UserDetails)principal).getUsername();
		Optional<Usuario> usuarioCompleto=serviceUsuario.findByNombreUsuario(usuario);
		
		return usuarioCompleto.get().getId();
	}
}
