package com.springboot.TresSolesApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.TresSolesApi.Modelo.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
		
	@Query(value="Select * from producto join producto_categoria on producto.producto_id=producto_categoria.producto_id where producto_categoria.categoria_id=:query", nativeQuery = true)
	List<Producto> findProductosByCategoria(Long query);
	
	@Query(value="Select * from producto join producto_categoria on producto.producto_id=producto_categoria.producto_id where producto_oferta>0", nativeQuery = true)
	List<Producto> findProductosConOfertas();
	
	@Modifying
	@Query(value="Update producto set producto_precio=:precio,producto_oferta=:oferta  where producto_id=:productoId", nativeQuery = true)
	void updateById(@Param("productoId")Long productoId,@Param("precio")double precio,@Param("oferta")int oferta);
	
	
	
}
