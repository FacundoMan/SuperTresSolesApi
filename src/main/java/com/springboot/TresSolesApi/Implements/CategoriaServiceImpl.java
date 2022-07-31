package com.springboot.TresSolesApi.Implements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
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
	public Categoria getCategoriaById(Long id) throws SupermercadoException {
		
		if(repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		}else {
			throw new SupermercadoException("Esa categoria no existe");
		}
		
	}

	@Override
	public void addCategoria(Categoria categoria) {
		repository.save(categoria);
	}

	@Override
	public Categoria getCategoriaByNombre(String nombre) throws SupermercadoException {
		if(repository.findCategoriaByNombre(nombre)!=null) {
			return repository.findCategoriaByNombre(nombre);
		}else {
			throw new SupermercadoException("Esa categoria no existe");
		}
	}


 
}
