package com.springboot.TresSolesApi.Implements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.TresSolesApi.Modelo.Carrito;
import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Repositorio.CarritoRepository;
import com.springboot.TresSolesApi.Repositorio.LineaDeCarritoRepository;
import com.springboot.TresSolesApi.Repositorio.UsuarioRepository;
import com.springboot.TresSolesApi.Service.CarritoService;
import com.springboot.TresSolesApi.Service.UsuarioService;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {
	
	@Autowired
	CarritoRepository carritoRepository;
	@Autowired
	LineaDeCarritoRepository lineaDeCarritoRepository;
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public Carrito getCarritoByUser(Long usuarioId) throws SupermercadoException {
		if(!carritoRepository.existsByUsuarioId(usuarioId)) {
			carritoRepository.save(new Carrito(usuarioService.findUsuarioById(usuarioId)));
		}
		return carritoRepository.findByUsuarioId(usuarioId);
	}
	

	@Override
	public List<LineaDeCarrito> getListaCarrito(Long carritoId) {	
		return lineaDeCarritoRepository.findByCarritoId(carritoId);
	}

	@Override
	public void updateLineaCarrito(LineaDeCarrito lineaDeCarrito) {
		lineaDeCarritoRepository.save(lineaDeCarrito);
	}

	@Override
	public void borrarLineaCarrito(Long lineaCarrito) {
		lineaDeCarritoRepository.deleteById(lineaCarrito);	
	}

	@Override
	public void addLineaCarrito(LineaDeCarrito lineaDeCarrito) {
		lineaDeCarritoRepository.save(lineaDeCarrito);	
	}


	@Override
	public boolean existeLineaDeCarritoById(Long lineaCarrito) {
		return lineaDeCarritoRepository.existsById(lineaCarrito);
	}


	@Override
	//Esto comprueba que la linea del usuario es del usuario y no de otro usuario
	public boolean comprobarLineaCarritoUser(Long idUser, Long idLinea) {
		Carrito c=carritoRepository.findByUsuarioId(idUser);
		Optional<LineaDeCarrito> l=lineaDeCarritoRepository.findById(idLinea);
		return c.getId()==l.get().getCarrito().getId();
	}


	@Override
	//Esto es para borrar todas las lineas del carrito
	public void limpiarCarrito(Long idCarrito) {
		lineaDeCarritoRepository.deleteByIdCarrito(idCarrito);
	}

}
