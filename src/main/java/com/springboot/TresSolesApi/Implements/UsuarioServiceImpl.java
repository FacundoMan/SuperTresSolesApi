package com.springboot.TresSolesApi.Implements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Repositorio.UsuarioRepository;
import com.springboot.TresSolesApi.Service.UsuarioService;
import com.springboot.TresSolesApi.Utilidad.OcultarContraseñaUsuario;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository repository;
	
	@Override
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
		return repository.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public Boolean existsByNombreUsuario(String nombreUsuario) {
		return (boolean)repository.existsByNombreUsuario(nombreUsuario);
	}

	@Override
	public void addNuevoUsuario(Usuario usuario) {
		repository.save(usuario);
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		//Pase a los usuario y le hardcode la contraseña para q no se viera cuando sea llamada
		List<Usuario> usuariosReturn=new ArrayList<>();
		List<Usuario> usuarioRepo=repository.findAll();
		if(!usuarioRepo.isEmpty()) {
			for (Usuario usuario : usuarioRepo) {
				Usuario u=OcultarContraseñaUsuario.ocultarContraseña(usuario);
				usuariosReturn.add(u);
			}
		}
		return usuariosReturn;
	}

	@Override
	public List<Usuario> findByIdRolUsuarios(Long rol) {
		List<Usuario> usuariosReturn=new ArrayList<>();
		List<Usuario> usuarioRepo=repository.findUsuariosByIdRol(rol);
		if(!usuarioRepo.isEmpty()) {
			for (Usuario usuario : usuarioRepo) {
				Usuario u=OcultarContraseñaUsuario.ocultarContraseña(usuario);
				usuariosReturn.add(u);
			}
		}
		return usuariosReturn;
	}

	@Override
	public List<Usuario> findByRolUsuarios(String rolNombre) {
		List<Usuario> usuariosReturn=new ArrayList<>();
		List<Usuario> usuarioRepo=repository.findUsuariosByRol(rolNombre);
		if(!usuarioRepo.isEmpty()) {
			for (Usuario usuario : usuarioRepo) {
				Usuario u=OcultarContraseñaUsuario.ocultarContraseña(usuario);
				usuariosReturn.add(u);
			}
		}
		return usuariosReturn;
	}

	@Override
	public Usuario findUsuarioById(Long id) throws SupermercadoException {
	
		if(repository.findById(id).isPresent()) {
			Usuario u=OcultarContraseñaUsuario.ocultarContraseña(repository.findById(id).get());
			return u;
		}else {
			throw new SupermercadoException("El usuario no existe");
		}
				
	}

}
