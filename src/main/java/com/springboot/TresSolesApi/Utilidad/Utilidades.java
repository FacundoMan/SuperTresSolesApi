package com.springboot.TresSolesApi.Utilidad;

import java.util.Iterator;
import java.util.regex.Pattern;

public class Utilidades {
	
	//Devuelve true si hay un caracter especial
	static public boolean verificarCaracteresEspeciales(String a) { 
		 Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
		return (regex.matcher(a).find());
	}

}
