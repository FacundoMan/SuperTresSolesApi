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
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.UsuarioService;


@RestController
@RequestMapping("api/Picker")
@PreAuthorize("hasRole('ROLE_PICKER')||hasRole('ROLE_GERENTE')")
public class ControllerPicker {
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/pedidosPicker")
	public List<Pedido> getPedidosPicker(){
		//id 1 En_Espera id 2 Armando id 3 Por_Recoger
		return pedidoService.pedidosFiltradosPorTresEstados((long)1,(long) 2,(long) 3);
	}

}
