package com.springboot.TresSolesApi.Implements;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Repositorio.ProductoRepository;
import com.springboot.TresSolesApi.Service.ProductoService;
import com.springboot.TresSolesApi.Utilidad.Utilidades;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	ProductoRepository repository;
	
	@Override
	public List<Producto> getAllProducto() {
		return(List<Producto>)repository.findAll();
	}

	@Override
	public Producto getProductoById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void addProducto(Producto producto) {
		repository.save(producto);
		
	}

	@Override
	public List<Producto> getProductosByCategoria(Long idCategoria) {
		return (List<Producto>) repository.findProductosByCategoria(idCategoria);
	}

	@Override
	public List<Producto> getProductosOfertas() {
		return (List<Producto>) repository.findProductosConOfertas();
	}

	@Override
	public void modificarProducto(Long idProducto, Producto p) throws SupermercadoException {
		Utilidades.verificarOferta(p.getOferta());
		Utilidades.verificarPrecio(p.getPrecio());
		repository.updateById(idProducto, p.getPrecio(), p.getOferta());
		
	}

}
