package com.springboot.TresSolesApi.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LineasDePedidos")
@SequenceGenerator(name = "lineaPSeq", initialValue = 1000, allocationSize = 1)
public class LineaDePedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lineaPSeq")
	@Column(name="linea_de_pedido_id")
	private Long id;
	@Column(name="linea_de_pedido_cantidad")
	private int cantidad;
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	@ManyToOne()
	@JoinColumn(name = "pedido_id")
	@JsonIgnore
	private Pedido pedido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
}
