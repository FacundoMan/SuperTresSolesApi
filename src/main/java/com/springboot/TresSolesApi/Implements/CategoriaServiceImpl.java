package com.springboot.TresSolesApi.Implements;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Repositorio.CategoriaRepository;
import com.springboot.TresSolesApi.Service.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaRepository repository;
	
	@Override
	public List<Categoria> getAllCategoria() {
		return (List<Categoria>)repository.findAll();
	}

	@Override
	public Categoria getCategoriaById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void addCategoria(Categoria categoria) {
		repository.save(categoria);
	}

 
}
