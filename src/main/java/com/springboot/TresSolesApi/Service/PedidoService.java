package com.springboot.TresSolesApi.Service;
import java.util.List;

import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.Pedido;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;

public interface PedidoService {
	public void confirmarPedido(List<LineaDeCarrito> lineaDeCarritos, Pedido pedido) throws SupermercadoException;
	public List<Pedido> obtenerMisPedidos(Long idUsuario);
	public Pedido obtenerPedido(Long idPedido);
	public void cancelarPedido(Long idPedido) throws SupermercadoException;
	public List<Pedido> obtenerTodosLosPedidos();
	public boolean existePedidoById(Long idPedido);
	public boolean comprobarPedidoUser(Long idUser,Long idPedido);	
}
