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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.springboot.TresSolesApi.Utilidad.Utilidades;


@Entity
@Table(name="Producto")
@SequenceGenerator(name="productoSeq", initialValue=1000, allocationSize=1)
public class Producto {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="productoSeq")
	@Column(name="productoId")
	private Long id;
	@Column(name="productoNombre")
	private String nombre;
	
	@ManyToMany(cascade= {CascadeType.ALL, CascadeType.MERGE})
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
	
	public void addNuevaCategoria(Categoria c) {
		categorias.add(c);
	}
	
	public void verificarProducto() throws SupermercadoException {
		//Nombre
		if(this.getNombre().isEmpty())throw new SupermercadoException("El nombre no puede ser vacio");
		if(this.getNombre().length()<3)throw new SupermercadoException("El nombre no puede ser menor a 3 caracteres");
		if(this.getNombre().length()>40)throw new SupermercadoException("El nombre no puede ser mayor a 40 caracteres");
		if(Utilidades.verificarCaracteresEspeciales(this.getNombre()))throw new SupermercadoException("El nombre no puede contener caracteres especiales");
		//Precio
		if(this.getPrecio()<0)throw new SupermercadoException("El precio tiene que ser mayor a 0");
		if(this.getPrecio()>1000000)throw new SupermercadoException("El precio tiene que ser menor a 1 millon");
		//Imagen
		if(this.getUrlImagen().isEmpty()|| this.getUrlImagen()==null)throw new SupermercadoException("El url de la imagen no puede ser vacio");
		//Descripcion
		if(this.getDescripcion().isEmpty())throw new SupermercadoException("La descripcion no puede ser vacia");
		if(this.getDescripcion().length()>1000)throw new SupermercadoException("La descripcion no puede ser mayor a 1000 caracteres");
		//Categoria
		if(this.getCategorias().isEmpty())throw new SupermercadoException("Tiene que tener al menos 1 categoria");
		
		
		
	}
	
	
}
