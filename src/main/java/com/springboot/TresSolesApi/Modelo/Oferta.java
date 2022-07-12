package com.springboot.TresSolesApi.Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Oferta")
@SequenceGenerator(name="ofertaSeq", initialValue=1000, allocationSize=100)
public class Oferta {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "ofertaSeq")
	@Column(name="ofertaId")
	private Long id;
	@Column(name="ofertaTipo", unique=true)
	private String tipo;
	@Column(name="ofertaDescripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "oferta")
	@JsonIgnore
	private List<Producto> productos=new ArrayList<>();
	
	public Oferta() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void verificarOferta() throws SupermercadoException {
		if(this.getDescripcion().isEmpty()) throw new SupermercadoException("La descripcion no puede ser vacia");		
		if(this.getTipo().isEmpty())throw new SupermercadoException("El nombre del tipo de oferta no puede ser vacio");
		if(this.getDescripcion().length()>300) throw new SupermercadoException("El maximo de la descripcion es de 300 caracteres");
		if(this.getTipo().length()>20)throw new SupermercadoException("El maximo de del nombre del tipo de oferta es de 20 caracteres");
	}
	
}
