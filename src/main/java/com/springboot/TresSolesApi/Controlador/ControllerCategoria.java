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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.TresSolesApi.Modelo.Categoria;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Service.CategoriaService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/api/Categoria")
public class ControllerCategoria {
	@Autowired
	CategoriaService service;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Categoria categoria){
		Map<String,Object>response=new HashMap<>();
		try {
			categoria.verificarCategoria();
			categoria.setBorrado(false);
			service.addCategoria(categoria);
			response.put("Mensaje","Se creo correctamente la categoria "+categoria.getNombre());
		} catch (SupermercadoException e) {
			response.put("Mensaje", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getCategorias")
	public List<Categoria> getAll(){
		return service.getAllCategoria();
	}
	
	@GetMapping("/getCategoria/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Map<String,Object>response=new HashMap<>();
		try {
			return new ResponseEntity<Categoria>(service.getCategoriaById(id),HttpStatus.OK);
		} catch (Exception e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
