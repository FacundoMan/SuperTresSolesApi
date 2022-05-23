package com.springboot.TresSolesApi.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="categoriaId")
	private Long id;
	@Column(name="categoriaNombre")
	private String nombre;
	//Esto es para el borrado logico, si es true es que esta borrado
	@ManyToMany(mappedBy="categorias")
	private List<Producto>productos=new ArrayList<>();
	
	@Column(name="categoriaBorrado")
	private boolean borrado;
	
	public Categoria() {
		
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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	
}
