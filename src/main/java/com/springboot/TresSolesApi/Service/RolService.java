package com.springboot.TresSolesApi.Service;

import java.util.Optional;

import com.springboot.TresSolesApi.Modelo.Rol;

public interface RolService {
	public Optional<Rol> findByNombre(String Nombre);
}
