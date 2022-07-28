package com.springboot.TresSolesApi.Utilidad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Service.UsuarioService;

public class Utilidades {
	@Autowired
	UsuarioService serviceUsuario;
	
	//Devuelve true si hay un caracter especial
	static public boolean verificarCaracteresEspeciales(String a) { 
		 Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
		return (regex.matcher(a).find());
	}
	//Devuelve true si hay un numero
	static public boolean verificarNumero(String a) {
		 Pattern regex = Pattern.compile("^[0-9]");
			return (regex.matcher(a).find());
	}
	//Devuelve true si hay una letra
	static public boolean verificarLetras(String a) {
		Pattern regex = Pattern.compile("[A-Za-z]");
		return (regex.matcher(a).find());
	}

	static public boolean hayEspacio(String s) {
		if(s.contains(" ")) {
			return true;
		}
		return false;
	}
	
	
	
}
