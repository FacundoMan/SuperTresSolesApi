package com.springboot.TresSolesApi.Utilidad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.TresSolesApi.Modelo.SupermercadoException;
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
	public static double calulcarDescuento(double precio, int oferta) {
		double calcularOferta=(precio*oferta)/100;
		return precio-calcularOferta;
	}
	
	public static void verificarPassword(String p) throws SupermercadoException {
		if(p==null)throw new SupermercadoException("La contraseña no puede ser vacio");
		if(p.length()<8 ||p.length()>15)throw new SupermercadoException("La contraseña tiene que ser de 8 a 15 de largo. Actual("+p.length()+")");
		if(Utilidades.hayEspacio(p))throw new SupermercadoException("La contraseña no puede contener espacios");
	}
	
	public static void verificarCelular(String c) throws SupermercadoException {
		if(c.isEmpty() || c==null)throw new SupermercadoException("El numero de contacto no puede ser vacio.");	
		if(!c.matches("09(.*)"))throw new SupermercadoException("El numero de contacto tiene que empezar con 09.");
	}
	public static void verificarPrecio(Double p) throws SupermercadoException{
		if(p<0)throw new SupermercadoException("El precio tiene que ser mayor a 0");
		if(p>1000000)throw new SupermercadoException("El precio tiene que ser menor a 1 millon");
	}
	
	public static void verificarOferta(int o)throws SupermercadoException{
		if(o<0 || o>100)throw new SupermercadoException("La oferta no puede ser menor a 0 o mayor a 100");
	}
}
