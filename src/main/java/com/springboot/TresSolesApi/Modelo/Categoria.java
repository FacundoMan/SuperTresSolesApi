package com.springboot.TresSolesApi.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.springboot.TresSolesApi.Utilidad.Utilidades;

@Entity
@Table(name="Categoria")
@SequenceGenerator(name="categoriaSeq", initialValue=1000, allocationSize=200)
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "categoriaSeq")
	@Column(name="categoriaId")
	private Long id;
	@Column(name="categoriaNombre", unique=true)
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
	
	public void verificarCategoria() throws SupermercadoException {
		if(this.getNombre().isEmpty())throw new SupermercadoException("El nombre no puede ser vacio");
		if(this.getNombre().length()<3)throw new SupermercadoException("El nombre tiene que tener mas de 3 caracteres");		
		if(this.getNombre().length()>40)throw new SupermercadoException("El nombre tiene que ser menor a 40 caracteres");
		if(Utilidades.verificarCaracteresEspeciales(this.getNombre()))throw new SupermercadoException("No se permiten caracteres especiales");
	}
	
}
