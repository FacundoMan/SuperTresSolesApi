package com.springboot.TresSolesApi.Implements;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.springboot.TresSolesApi.Modelo.Rol;
import com.springboot.TresSolesApi.Modelo.Usuario;
import com.springboot.TresSolesApi.Repositorio.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario=usuarioRepository.findByNombreUsuario(nombreUsuario)
				.orElseThrow(()->new UsernameNotFoundException("El usuario "+nombreUsuario+" no se encontro."));
		return new User(usuario.getNombreUsuario(), usuario.getPassword(),mappearRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mappearRoles(Set<Rol> roles){
		return roles.stream().map(rol-> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
}
