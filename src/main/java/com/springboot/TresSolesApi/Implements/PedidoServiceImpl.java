package com.springboot.TresSolesApi.Implements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.TresSolesApi.Modelo.Carrito;
import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.LineaDePedido;
import com.springboot.TresSolesApi.Modelo.Pedido;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Repositorio.CarritoRepository;
import com.springboot.TresSolesApi.Repositorio.LineaDePedidoRepository;
import com.springboot.TresSolesApi.Repositorio.PedidoRepository;
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.UsuarioService;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	LineaDePedidoRepository lineaDePedidoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	UsuarioService usuarioService;
	

	@Override
	public List<Pedido> obtenerMisPedidos(Long idUsuario) {
		return pedidoRepository.obtenerPedidos(idUsuario) ;
	}


	@Override
	public Pedido obtenerPedido(Long idPedido) {
		
		return pedidoRepository.findById(idPedido).get();
	}

	@Override
	public void cancelarPedido(Long idPedido) throws SupermercadoException {
		Pedido p=obtenerPedido(idPedido);
		if(p.getEstado()!="En Espera" && p.getEstado()!="Armado")throw new SupermercadoException("No puede cancelar un pedido que no sea (En Espera) o (Armado)");
		pedidoRepository.updateById(idPedido, "Cancelado");
	}

	@Override
	public List<Pedido> obtenerTodosLosPedidos() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void confirmarPedido(List<LineaDeCarrito> lineaDeCarrito,Pedido pedido) throws SupermercadoException {
		if(lineaDeCarrito.isEmpty())throw new SupermercadoException("El carrito esta vacio");
		Pedido p=new Pedido();
		p.setEstado("En Espera");
		//Falta setear los otros datos del pedido
		if(pedido.getUsuario()==null) throw new SupermercadoException("El usuario es null");
		p.setUsuario(pedido.getUsuario());
		Pedido p2=pedidoRepository.save(p);
		if(p2.getId()==null)throw new SupermercadoException("El id de ese pedido no existe");
		for (LineaDeCarrito l : lineaDeCarrito) {
			LineaDePedido lp=new LineaDePedido();
			lp.setCantidad(l.getCantidad());
			lp.setProducto(l.getProducto());
			lp.setPedido(p2);
			lineaDePedidoRepository.save(lp);	
		}
	}


	@Override
	public boolean existePedidoById(Long pedidoId) {
		
		return pedidoRepository.existsById(pedidoId);
	}


	@Override
	public boolean comprobarPedidoUser(Long idUser, Long idPedido) {
		Pedido p=pedidoRepository.findById(idPedido).get();;
		return idUser.equals(p.getUsuario().getId());
	}

}
