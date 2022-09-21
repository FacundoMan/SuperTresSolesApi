package com.springboot.TresSolesApi.Implements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.TresSolesApi.Modelo.Carrito;
import com.springboot.TresSolesApi.Modelo.Estado;
import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.LineaDePedido;
import com.springboot.TresSolesApi.Modelo.Pedido;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;
import com.springboot.TresSolesApi.Repositorio.CarritoRepository;
import com.springboot.TresSolesApi.Repositorio.LineaDePedidoRepository;
import com.springboot.TresSolesApi.Repositorio.PedidoRepository;
import com.springboot.TresSolesApi.Service.PedidoService;
import com.springboot.TresSolesApi.Service.UsuarioService;
import com.springboot.TresSolesApi.Utilidad.EstadosPedido;
import com.springboot.TresSolesApi.Utilidad.Utilidades;
import com.springboot.TresSolesApi.Repositorio.EstadoRepository;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	LineaDePedidoRepository lineaDePedidoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
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
		if(p==null)throw new SupermercadoException("Ese pedido no existe");
		String nombreEstado=p.getEstado().getNombre();
		if(nombreEstado.equals(EstadosPedido.Cancelado.toString()) || nombreEstado.equals(EstadosPedido.En_Camino.toString()) || nombreEstado.equals(EstadosPedido.Entregado.toString()))throw new SupermercadoException("Solo se pueden cancelar pedidos En Espera, Armado o Por Recoger)");
		//El id 5 es el estado "Cancelado"
		pedidoRepository.updateById(idPedido,(long) 5);
	}

	@Override
	public List<Pedido> obtenerTodosLosPedidos() {	
		return  (List<Pedido>) pedidoRepository.findAll();
	}


	@Override
	public void confirmarPedido(List<LineaDeCarrito> lineaDeCarrito,Pedido pedido) throws SupermercadoException {
		if(lineaDeCarrito.isEmpty())throw new SupermercadoException("El carrito esta vacio");
		Pedido p=new Pedido();
		p.setEstado(new Estado((long) 1,"En_Espera"));
		//Falta setear los otros datos del pedido
		if(pedido.getUsuario()==null) throw new SupermercadoException("El usuario es null");
		p.setUsuario(pedido.getUsuario());
		p.setNombre(pedido.getNombre());
		p.setApellido(pedido.getApellido());
		p.setFecha(pedido.getFecha());
		p.setDescripcionCasa(pedido.getDescripcionCasa());
		p.setDireccion(pedido.getDireccion());
		p.setContacto(pedido.getContacto());
		p.setCambio(pedido.getCambio());
		p.setFormaDePago(pedido.getFormaDePago());
		p.setEnvioORetiro(pedido.getEnvioORetiro());
		double totalConDescuento=0;
		for (LineaDeCarrito l : lineaDeCarrito) {
			if(l.getProducto().getOferta()==0) {
				totalConDescuento=totalConDescuento+(l.getCantidad()*l.getProducto().getPrecio());
			}else {
				totalConDescuento=totalConDescuento+(l.getCantidad()*Utilidades.calulcarDescuento(l.getProducto().getPrecio(),l.getProducto().getOferta()));
			}
			
		}
		p.setTotal(totalConDescuento);
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


	@Override
	public List<Pedido> pedidosFiltradosPorEstado(Long estado) {
		return pedidoRepository.pedidosEstado(estado);
	}


	@Override
	public List<Pedido> pedidosFiltradosPorDosEstados(Long estado1, Long estado2) {
		return pedidoRepository.pedidosEstados(estado1, estado2);
	}


	@Override
	public List<Pedido> pedidosFiltradosPorTresEstados(Long estado1, Long estado2, Long estado3) {
		return pedidoRepository.pedidosEstados(estado1, estado2,estado3);
	}


	@Override
	public void cambiarEstado(Long idPedido, Long estado) throws SupermercadoException {
		Pedido p=obtenerPedido(idPedido);
		Estado e=estadoRepository.findById(estado).get();
		if(p==null)throw new SupermercadoException("Ese pedido no existe");
		if(e==null)throw new SupermercadoException("Ese estado no existe");
		pedidoRepository.updateById(idPedido, estado);	
	}

}
