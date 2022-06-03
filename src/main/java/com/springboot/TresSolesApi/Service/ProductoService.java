package com.springboot.TresSolesApi.Service;

import java.util.List;


import com.springboot.TresSolesApi.Modelo.Producto;

public interface ProductoService {
	
	public List<Producto> getAllProducto();
	
	public Producto getProductoById(Long id);
	
	public void addProducto(Producto producto);
	
	public List<Producto> getProductosByCategoria(Long idCategoria);
}
