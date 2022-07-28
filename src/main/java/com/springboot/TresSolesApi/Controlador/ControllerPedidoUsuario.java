package com.springboot.TresSolesApi.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Carrito;
import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.LineaDePedido;
import com.springboot.TresSolesApi.Modelo.Pedido;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Service.CarritoService;
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.UsuarioService;

@RestController
@RequestMapping("api/PedidoUsuario")
@PreAuthorize("hasRole('ROLE_USER')")
public class ControllerPedidoUsuario {
	
	@Autowired
	PedidoService pedidoService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CarritoService carritoService;
	
	@PostMapping("/confirmarPedido")
	public ResponseEntity<?> confirmarPedido(@RequestBody Pedido p) {
		Map<String,Object>response=new HashMap<>();
		
		try {
			//Busco el carrito del usuario
			Carrito c=carritoService.getCarritoByUser(obtenerIdUsuarioConectado());
			//Cargo las lineas del carrito
			List<LineaDeCarrito> listaCarrito=carritoService.getListaCarrito(c.getId());
			//Le seteo el usuario al pedido
			p.setUsuario(obtenerUsuarioConectado().get());
			//Confirmo el pedido
			pedidoService.confirmarPedido(listaCarrito, p);
			//Borro las lineas del carrito
			
			carritoService.limpiarCarrito(c.getId());
			response.put("Mensaje","Se creo correctamente el pedido");
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/obtenerPedidos")
	public List<Pedido> getPedidos(){
		return pedidoService.obtenerMisPedidos(obtenerIdUsuarioConectado());
	} 
	
	@GetMapping("/obtenerPedido/{id}")
	public ResponseEntity<?> obtenerPedidoId(@PathVariable Long id) {
		try {
			if(!pedidoService.existePedidoById(id))throw new SupermercadoException("Ese pedido no existe");
			if(!pedidoService.comprobarPedidoUser(obtenerIdUsuarioConectado(), id))throw new SupermercadoException("No puedes ver el pedido de otro usuario");
			Pedido p=pedidoService.obtenerPedido(id);
			return new ResponseEntity<Pedido>(p, HttpStatus.OK);
		} catch (SupermercadoException e) {
			Map<String,Object>response=new HashMap<>();
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/cancelarPedido/{id}")
	public ResponseEntity<?> cancelarPedido(@PathVariable Long id) {
		Map<String,Object>response=new HashMap<>();
		try {
			if(!pedidoService.existePedidoById(id))throw new SupermercadoException("Ese pedido no existe");
			if(!pedidoService.comprobarPedidoUser(obtenerIdUsuarioConectado(),id))throw new SupermercadoException("No puedes modificar un pedido que no es tuyo");
			//Me falta comprobar q solo pueda cancelar el pedido "En espera" o "Armado"
			pedidoService.cancelarPedido(id);
			response.put("Mensaje","Se cancelo correctamente el pedido");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (SupermercadoException e) {
			
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Long obtenerIdUsuarioConectado() {
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usuario=((UserDetails)principal).getUsername();
		Optional<Usuario> usuarioCompleto=usuarioService.findByNombreUsuario(usuario);
		
		return usuarioCompleto.get().getId();
	}
	
	private Optional<Usuario> obtenerUsuarioConectado() throws SupermercadoException{
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usuario=((UserDetails)principal).getUsername();
		Optional<Usuario> usuarioCompleto=usuarioService.findByNombreUsuario(usuario);
		if(usuarioCompleto.isEmpty())throw new SupermercadoException("Usuario no encontrado");
		return usuarioCompleto;
	}
	
	
	
	
	
	
	
	
	

}
