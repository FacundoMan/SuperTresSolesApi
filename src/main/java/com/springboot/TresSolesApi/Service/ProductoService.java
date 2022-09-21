package com.springboot.TresSolesApi.Service;

import java.util.List;


import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;

public interface ProductoService {
	
	public List<Producto> getAllProducto();
	
	public Producto getProductoById(Long id);
	
	public void addProducto(Producto producto);
	
	public List<Producto> getProductosByCategoria(Long idCategoria);
	
	public List<Producto> getProductosOfertas();
	
	public void modificarProducto(Long idProducto, Producto p) throws SupermercadoException;
}
