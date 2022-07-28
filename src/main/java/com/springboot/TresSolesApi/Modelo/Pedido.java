package com.springboot.TresSolesApi.Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "pedidos")
@SequenceGenerator(name = "pedidosSeq", initialValue = 500, allocationSize = 1)
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidosSeq")
	@Column(name="pedido_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	@Column(name="pedido_estado")
	private String estado;
	@Column(name="pedido_fecha")
	private Date fecha;
	@Column(name="pedido_direccion")
	private String direccion;
	@Column(name="pedido_contacto")
	private String contacto;
	@Column(name="pedido_nombre_cliente")
	private String nombre;
	@Column(name="pedido_apellido_cliente")
	private String apellido;
	//Esta es para saber si el cliente necesita cambio
	@Column(name="pedido_cambio")
	private long cambio;
	@Column(name="pedido_forma_pago")
	private String formaDePago;
	@Column (name="pedido_tiempo_estimado")
	private String tiempoEstimado;
	//Esto es para saber si se retira en caja o se arma el envio
	@Column (name="pedido_envio_retiro")
	private String envioORetiro;
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL)
	private List<LineaDePedido> lineasPedido=new ArrayList<>();
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<LineaDePedido> getLineasPedido() {
		return lineasPedido;
	}
	public void setLineasPedido(List<LineaDePedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}


}
