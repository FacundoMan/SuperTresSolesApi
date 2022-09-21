package com.springboot.TresSolesApi.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Pedido;
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.UsuarioService;

@RestController
@RequestMapping("api/Repartidor")
@PreAuthorize("hasRole('ROLE_REPARTIDOR')||hasRole('ROLE_GERENTE')")
public class ControllerRepartidor {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/pedidosRepartidor")
	public List<Pedido> getPedidosRepartidor(){
		//id 3 Por_Recoger id 4 En_Camino 6 Entregado
		return pedidoService.pedidosFiltradosPorTresEstados((long)3,(long) 4,(long) 6);
	}
}
