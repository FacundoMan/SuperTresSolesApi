package com.springboot.TresSolesApi.Repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.TresSolesApi.Modelo.Carrito;

public interface CarritoRepository extends CrudRepository<Carrito, Long> {

	@Query(value="Select * from carritos where carritos.usuario_id=:query", nativeQuery = true)
	Carrito findByUsuarioId(Long query);
	
	boolean existsByUsuarioId(Long usuario_id);
}
