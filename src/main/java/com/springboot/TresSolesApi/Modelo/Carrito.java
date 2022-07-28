package com.springboot.TresSolesApi.Modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "carritos", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id" }) })
@SequenceGenerator(name = "carritoSeq", initialValue = 300, allocationSize = 1)
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carritoSeq")
	@Column(name="carrito_id")
	private Long id;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy="carrito", cascade = CascadeType.ALL)
	private List<LineaDeCarrito> lineasCarrito=new ArrayList<>();
	
	
	

	public Carrito() {
		super();
	}

	public Carrito(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<LineaDeCarrito> getLineasCarrito() {
		return lineasCarrito;
	}

	public void setLineasCarrito(List<LineaDeCarrito> lineasCarrito) {
		this.lineasCarrito = lineasCarrito;
	}
	
	
	

}
