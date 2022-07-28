package com.springboot.TresSolesApi.Service;

import java.util.List;

import com.springboot.TresSolesApi.Modelo.Carrito;
import com.springboot.TresSolesApi.Modelo.LineaDeCarrito;
import com.springboot.TresSolesApi.Modelo.Producto;
import com.springboot.TresSolesApi.Modelo.SupermercadoException;

public interface CarritoService {
	public Carrito getCarritoByUser(Long usuarioId) throws SupermercadoException;
	public List<LineaDeCarrito> getListaCarrito(Long carritoId);
	public void addLineaCarrito(LineaDeCarrito lineaDeCarrito);
	public void updateLineaCarrito(LineaDeCarrito lineaDeCarrito);
	public void borrarLineaCarrito(Long lineaCarrito);
	public boolean existeLineaDeCarritoById(Long lineaCarrito);
	public boolean comprobarLineaCarritoUser(Long idUser,Long idLinea);
	public void limpiarCarrito(Long idCarrito);
}
