package com.springboot.TresSolesApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.TresSolesApi.Modelo.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	@Query(value="Select * from pedidos where pedidos.usuario_id=:query", nativeQuery = true)
	List<Pedido> obtenerPedidos(Long query);
	
	@Modifying
	@Query(value="Update pedidos set pedido_estado=:estado where pedidos.pedido_id=:pedidoId", nativeQuery = true)
	void updateById(@Param("pedidoId")Long pedidoId,@Param("estado")String estado);
}
