package com.springboot.TresSolesApi.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;

public interface UsuarioService {
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	public Boolean existsByNombreUsuario(String nombreUsuario);
	public void addNuevoUsuario(Usuario usuario);
	public List<Usuario> findAllUsuarios();
	public List<Usuario> findByIdRolUsuarios(Long rol);
	public List<Usuario> findByRolUsuarios(String rolNombre);
	public Usuario findUsuarioById(Long id) throws SupermercadoException;
}
