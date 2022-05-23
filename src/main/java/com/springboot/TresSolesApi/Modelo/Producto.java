package com.springboot.TresSolesApi.Modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Producto")
public class Producto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="productoId")
	private Long id;
	@Column(name="productoNombre")
	private String nombre;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="productoCategoria",
			joinColumns=@JoinColumn(name="productoId"),
			inverseJoinColumns=@JoinColumn(name="categoriaId"))
	private List<Categoria> categorias=new ArrayList<>();
	@Column(name="productoPrecio")
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="productoOferta")
	private Oferta oferta;
	
	@Column(name="productoUrlImagen")
	private String urlImagen;
	
	@Column(name="productoDescripcion")
	private String descripcion;
	
	//Esto es para el borrado logico, si es true es que esta borrado
	@Column(name="productoBorrado")
	private boolean	borrado;

	
	public Producto() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Oferta getOferta() {
		return oferta;
	}


	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}


	public String getUrlImagen() {
		return urlImagen;
	}


	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isBorrado() {
		return borrado;
	}


	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	
	
}
