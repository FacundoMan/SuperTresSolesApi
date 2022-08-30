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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LineasDeCarrito")
@SequenceGenerator(name = "lineaCSeq", initialValue = 7000, allocationSize = 1)
public class LineaDeCarrito {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lineaCSeq")
	@Column(name="linea_de_carrito_id")
	private Long id;
	@Column(name="linea_de_carrito_cantidad")
	private int cantidad;
	@ManyToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@ManyToOne()
	@JoinColumn(name = "carrito_id")
	@JsonIgnore
	private Carrito carrito;
	
	
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
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	
	
}
