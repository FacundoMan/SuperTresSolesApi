package com.springboot.TresSolesApi.Service;

import java.util.List;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;

public interface CategoriaService {
	public List<Categoria> getAllCategoria();
	
	public Categoria getCategoriaById(Long id) throws SupermercadoException;
	
	public Categoria getCategoriaByNombre(String nombre) throws SupermercadoException;
	
	public void addCategoria(Categoria categoria);
	
}
