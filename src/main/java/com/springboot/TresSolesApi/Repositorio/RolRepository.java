package com.springboot.TresSolesApi.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.TresSolesApi.Modelo.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	public Optional<Rol> findByNombre(String Nombre);

}
