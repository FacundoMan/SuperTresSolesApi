package com.springboot.TresSolesApi.Controlador;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Service.UsuarioService;
import com.springboot.TresSolesApi.Utilidad.Utilidades;

@RestController
@RequestMapping("api/Usuario")
public class ControllerUsuario {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PutMapping("/modoficarCelular/{celular}")
	public ResponseEntity<?> modificarProducto(@PathVariable String celular){
		Map<String,Object>response=new HashMap<>();
	try {
		Utilidades.verificarCelular(celular);
		usuarioService.modificarCelularUsuario(obtenerIdUsuarioConectado(),celular );
		response.put("Mensaje","Se modifico correctamente el numero de celular");
	} catch (SupermercadoException e) {
		response.put("Mensaje",e.getMessage());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/modoficarPassword")
	public ResponseEntity<?> modificarPassword(@RequestBody Usuario u){
		Map<String,Object>response=new HashMap<>();
	try {
		Utilidades.verificarPassword(u.getPassword());
		
		usuarioService.modificarPasswordUsuario(obtenerIdUsuarioConectado(), passwordEncoder.encode(u.getPassword()));
		response.put("Mensaje","Se modifico correctamente el password");
	} catch (SupermercadoException e) {
		response.put("Mensaje",e.getMessage());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
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
