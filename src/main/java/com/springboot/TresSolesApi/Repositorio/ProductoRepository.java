package com.springboot.TresSolesApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.TresSolesApi.Modelo.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
		
	@Query(value="Select * from producto join producto_categoria on producto.producto_id=producto_categoria.producto_id where producto_categoria.categoria_id=:query", nativeQuery = true)
	List<Producto> findProductosByCategoria(Long query);
	
}
