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
	@Query(value="Update pedidos set pedido_estado_id=:estado where pedidos.pedido_id=:pedidoId", nativeQuery = true)
	void updateById(@Param("pedidoId")Long pedidoId,@Param("estado")Long estado);
	
	@Query(value="Select * from pedidos where pedido_estado_id=:query", nativeQuery = true)
	List<Pedido> pedidosEstado(Long query);
	
	@Query(value="Select * from pedidos where pedido_estado_id=:query OR pedido_estado_id=:query2", nativeQuery = true)
	List<Pedido> pedidosEstados(Long query, Long query2);
	
	@Query(value="Select * from pedidos where pedido_estado_id=:query OR pedido_estado_id=:query2 OR pedido_estado_id=:query3", nativeQuery = true)
	List<Pedido> pedidosEstados(Long query, Long query2, Long query3);
	
}

