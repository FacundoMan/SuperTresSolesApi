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
	@Column(name="pedido_fecha")
	private Date fecha;
	@Column(name="pedido_descripcion_casa")
	private String descripcionCasa;
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
	@Column (name="pedido_total")
	private double total;
	@ManyToOne()
	@JoinColumn(name="pedido_estado_id")
	private Estado estado;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<LineaDePedido> getLineasPedido() {
		return lineasPedido;
	}
	public void setLineasPedido(List<LineaDePedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcionCasa() {
		return descripcionCasa;
	}
	public void setDescripcionCasa(String descripcionCasa) {
		this.descripcionCasa = descripcionCasa;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public long getCambio() {
		return cambio;
	}
	public void setCambio(long cambio) {
		this.cambio = cambio;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	public String getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(String tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public String getEnvioORetiro() {
		return envioORetiro;
	}
	public void setEnvioORetiro(String envioORetiro) {
		this.envioORetiro = envioORetiro;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	

}
