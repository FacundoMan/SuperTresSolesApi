package com.springboot.TresSolesApi.Modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.springboot.TresSolesApi.Utilidad.Utilidades;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombreUsuario" }) })
@SequenceGenerator(name = "usuarioSeq", initialValue = 50000, allocationSize = 1)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSeq")
	private long id;
	private String nombreUsuario;
	private String password;
	private String numeroContacto;
	private String nombre;
	private String apellido;
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL)
	private List<Pedido> pedidos=new ArrayList<>();
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Carrito carrito;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Set<Rol> roles = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public void validarUsuario() throws SupermercadoException {
		//Nombre Y Apellido
		if(this.getNombre().isEmpty())throw new SupermercadoException("El nombre no puede ser vacio.");
		if(this.getApellido().isEmpty())throw new SupermercadoException("El apellido no puede ser vacio.");
		if(this.getApellido().length()<3 || this.getApellido().length()>15)throw new SupermercadoException("El apellido tiene que ser de 3 a 15 caracteres.");
		if(this.getNombre().length()<3 || this.getNombre().length()>15)throw new SupermercadoException("El nombre tiene que ser de 3 a 15 caracteres.");
		if(Utilidades.hayEspacio(this.getNombre()))throw new SupermercadoException("El nombre no puede contener espacios.");
		if(Utilidades.hayEspacio(this.getApellido()))throw new SupermercadoException("El apellido no puede contener espacios.");
		if(Utilidades.verificarCaracteresEspeciales(this.getApellido()) || Utilidades.verificarNumero(this.getApellido()))throw new SupermercadoException("El apellido no puede contener caracteres especiales o numeros.");
		if(Utilidades.verificarCaracteresEspeciales(this.getNombre()) || Utilidades.verificarNumero(this.getNombre()))throw new SupermercadoException("El nombre no puede contener caracteres especiales o numeros.");

		//Celular
		if(this.getNumeroContacto().isEmpty())throw new SupermercadoException("El numero de contacto no puede ser vacio.");	
		if(!this.getNumeroContacto().matches("09(.*)"))throw new SupermercadoException("El numero de contacto tiene que empezar con 09.");
		//if(this.getNumeroContacto().toString(). || this.getNumeroContacto().charAt(1)!='1')throw new SupermercadoException("El numero de contacto tiene que empezar con 09.");
		if(this.getNumeroContacto().length()!=9)throw new SupermercadoException("El numero de contacto tiene que tener 9 numeros.");
		if(Utilidades.verificarCaracteresEspeciales(this.getNumeroContacto())||Utilidades.verificarLetras(this.getNumeroContacto()))throw new SupermercadoException("Solo se permiten numeros");
		String nombre=this.getNombre().toUpperCase().charAt(0) + this.getNombre().substring(1, this.getNombre().length()).toLowerCase();
		this.setNombre(nombre);
		String apellido=this.getApellido().toUpperCase().charAt(0) + this.getApellido().substring(1, this.getApellido().length()).toLowerCase();
		this.setApellido(apellido);
		//Contraseña
		if(this.getPassword().length()<8 ||this.getPassword().length()>15)throw new SupermercadoException("La contraseña tiene que ser de 8 a 15 de largo. Actual("+this.getPassword().length()+")");
		if(Utilidades.hayEspacio(this.getPassword()))throw new SupermercadoException("La contraseña no puede contener espacios");
		//Usuario
		if(this.getNombreUsuario().length()<5 || this.getNombreUsuario().length()>25)throw new SupermercadoException("El usuario tiene que ser de 5 a 25 de largo");
		if(Utilidades.hayEspacio(this.getNombreUsuario())||Utilidades.verificarCaracteresEspeciales(this.getNombreUsuario()))throw new SupermercadoException("El usuario no puede contener espacios ni caracteres especiales");
		
	}
	
	public String convertirAJson() {
		JsonObject usuJson = (JsonObject) Json.createObjectBuilder()
				.add("id",this.getId())
				.add("nombreUsuario",this.getNombreUsuario())
				.add("password","***************")
				.add("numeroContacto", this.getNumeroContacto())
				.add("nombre",this.getNombre())
				.add("apellido",this.getApellido())
				.add("rol","").build();
		return usuJson.toString();
	}
	
	
}
