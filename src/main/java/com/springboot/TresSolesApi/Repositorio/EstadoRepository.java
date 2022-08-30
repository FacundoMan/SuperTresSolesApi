package com.springboot.TresSolesApi.Repositorio;

import org.springframework.data.repository.CrudRepository;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Modelo.Estado;

public interface EstadoRepository extends CrudRepository<Estado, Long>  {

}
