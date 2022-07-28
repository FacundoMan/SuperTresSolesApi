package com.springboot.TresSolesApi.Repositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;

public interface LineaDeCarritoRepository extends CrudRepository<LineaDeCarrito,Long> {

	@Query(value="Select * from lineas_de_carrito l where l.carrito_id=:query", nativeQuery = true)
	List<LineaDeCarrito> findByCarritoId(Long query);

	@Modifying
	@Query(value="Delete from lineas_de_carrito where lineas_de_carrito.carrito_id=:carritoId", nativeQuery = true)
	void deleteByIdCarrito(@Param("carritoId")Long carritoId);

}
