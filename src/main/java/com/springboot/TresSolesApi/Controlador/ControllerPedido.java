package com.springboot.TresSolesApi.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Pedido;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.UsuarioService;

@RestController
@RequestMapping("api/Pedido")
@PreAuthorize("hasRole('ROLE_PICKER')||hasRole('ROLE_REPARTIDOR')||hasRole('ROLE_GERENTE')")
public class ControllerPedido {
	@Autowired
	PedidoService pedidoService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/todosLosPedidos")
	public List<Pedido> getPedidos(){
		return pedidoService.obtenerTodosLosPedidos();
	}
	
	@GetMapping("/pedidosEntregados")
	public List<Pedido>getPedidosEntregados(){
		return pedidoService.pedidosFiltradosPorEstado((long) 6);
	}
	
	//El id de los pedidos se encuentra en utilidades "EstadosPedido"
	@GetMapping("/pedidosCancelados")
	public List<Pedido>getPedidosCancelados(){
		//5 =  Cancelado
		return pedidoService.pedidosFiltradosPorEstado((long) 5);
	}
	
	@GetMapping("/getPedidosByEstado/{idEstado}")
	public List<Pedido> getPedidosByIdEstado(@PathVariable("idEstado") Long idEstado){
		return pedidoService.pedidosFiltradosPorEstado(idEstado);
	}

	@PutMapping("/cambiarEstado/{idPedido}/{idEstado}")
	public ResponseEntity<?> cambiarEstado(@PathVariable("idPedido") Long idPedido,@PathVariable("idEstado") Long idEstado){
		Map<String,Object>response=new HashMap<>();
		try {
			pedidoService.cambiarEstado(idPedido, idEstado);
			response.put("Mensaje","Se cambio correctamente el estado");
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
