package com.springboot.TresSolesApi.Implements;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.TresSolesApi.Modelo.Rol;
import com.springboot.TresSolesApi.Repositorio.RolRepository;
import com.springboot.TresSolesApi.Service.RolService;

@Service
@Transactional
public class RolServiceImpl implements RolService {

	@Autowired
	RolRepository repository;
	
	@Override
	public Optional<Rol> findByNombre(String Nombre) {
		
		return repository.findByNombre(Nombre);
	}
	
}
