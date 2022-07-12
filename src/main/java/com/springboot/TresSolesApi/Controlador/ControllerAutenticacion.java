package com.springboot.TresSolesApi.Controlador;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Rol;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Modelo.DTO.JWTAuthResponseDTO;
import com.springboot.TresSolesApi.Modelo.DTO.LoginDTO;
import com.springboot.TresSolesApi.Modelo.DTO.RegistroDTO;
import com.springboot.TresSolesApi.Repositorio.RolRepository;
import com.springboot.TresSolesApi.Seguridad.JwtTokenProvider;
import com.springboot.TresSolesApi.Service.RolService;
import com.springboot.TresSolesApi.Service.UsuarioService;
import com.springboot.TresSolesApi.Utilidad.ConvertirDTOaNormal;

@RestController
@RequestMapping("api/aut")
public class ControllerAutenticacion {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private RolService rolService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/iniciarSesion")
	public ResponseEntity<JWTAuthResponseDTO> autenticacionUsuario(@RequestBody LoginDTO loginDTO){
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getNombreUsuario(), loginDTO.getPassword()));
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//Obtenemos el token del  jwtTokenProvider
		String token=jwtTokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}
	
	@PostMapping("/registrarUsuario")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
		Map<String,Object>response=new HashMap<>();
		try {
		if(usuarioService.existsByNombreUsuario(registroDTO.getNombreUsuario()))throw new SupermercadoException("Ese usuario ya existe.");
		//Convierto del DTO a usuario y verifico los datos
		Usuario usuario=ConvertirDTOaNormal.registroDtoAUsuario(registroDTO);
		
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		//Se busca el rol en la bd y se le setea, en este caso uso singleton, porque es solo se le setea User es el cliente
		Rol roles=rolService.findByNombre("ROLE_USER").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioService.addNuevoUsuario(usuario);
		response.put("Mensaje","El usuario se registro existosamente");	
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	

}
