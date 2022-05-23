package com.springboot.TresSolesApi.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Oferta;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Service.OfertaService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/api/Oferta")
public class ControllerOferta {
	@Autowired
	OfertaService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Oferta oferta) {
		Map<String,Object>response=new HashMap<>();
		
		try {
			oferta.verificarOferta();
			service.addOferta(oferta);
			response.put("Mensaje","Se creo correctamente la oferta");
		} catch (SupermercadoException e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getOfertas")
	public List<Oferta> getAll(){
		return service.getAllOfertas();
	}
	
	@GetMapping("/getOferta/{id}")
	public Oferta getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
//	@PutMapping("/borrarOferta/{id}")
//	public String deleteOferta (@PathVariable Long id) {
//		Oferta o=service.getById(id);
//		o.set
//		return "se borro con exito";
//	}
	
	
}
