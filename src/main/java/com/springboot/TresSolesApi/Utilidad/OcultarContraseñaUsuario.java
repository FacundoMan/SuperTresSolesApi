package com.springboot.TresSolesApi.Utilidad;

import com.springboot.TresSolesApi.Modelo.Usuario;

public class OcultarContrase├▒aUsuario {
	public static Usuario ocultarContrase├▒a(Usuario usuario) {
		Usuario u=new Usuario();
		u.setApellido(usuario.getApellido());
		u.setNombre(usuario.getNombre());
		u.setId(usuario.getId());
		u.setNombreUsuario(usuario.getNombreUsuario());
		u.setNumeroContacto(usuario.getNumeroContacto());
		u.setPassword("*********************");
		u.setRoles(usuario.getRoles());
		return u;
	} 
}
