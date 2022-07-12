package com.springboot.TresSolesApi.Implements;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Repositorio.ProductoRepository;
import com.springboot.TresSolesApi.Service.ProductoService;

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

}
