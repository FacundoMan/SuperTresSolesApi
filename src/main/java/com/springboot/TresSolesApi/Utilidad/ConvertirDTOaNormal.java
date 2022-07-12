package com.springboot.TresSolesApi.Utilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Modelo.DTO.RegistroDTO;

public class ConvertirDTOaNormal {
		
		public static Usuario registroDtoAUsuario(RegistroDTO registroDTO) throws SupermercadoException {
			
			Usuario usuario=new Usuario();
			usuario.setNombre(registroDTO.getNombre());
			usuario.setApellido(registroDTO.getApellido());
			usuario.setNombreUsuario(registroDTO.getNombreUsuario());
			usuario.setPassword(registroDTO.getPassword());
			usuario.setNumeroContacto(registroDTO.getNumeroContacto());
			usuario.validarUsuario();
			return usuario;
		}
}
