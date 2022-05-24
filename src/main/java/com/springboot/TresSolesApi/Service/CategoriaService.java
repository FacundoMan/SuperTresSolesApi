package com.springboot.TresSolesApi.Service;

import java.util.List;

import com.springboot.TresSolesApi.Modelo.Categoria;

public interface CategoriaService {
	public List<Categoria> getAllCategoria();
	
	public Categoria getCategoriaById(Long id);
	
	public void addCategoria(Categoria categoria);
}
