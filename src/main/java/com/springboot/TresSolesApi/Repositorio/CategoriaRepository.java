package com.springboot.TresSolesApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.TresSolesApi.Modelo.Categoria;


public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

	@Query(value="Select * from categoria c where "+ "categoria_nombre =:query", nativeQuery = true)
	Categoria findCategoriaByNombre(String query);
	
}
