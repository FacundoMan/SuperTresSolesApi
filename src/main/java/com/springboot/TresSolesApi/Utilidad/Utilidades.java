package com.springboot.TresSolesApi.Utilidad;

import java.util.Iterator;
import java.util.regex.Pattern;

public class Utilidades {
	
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
