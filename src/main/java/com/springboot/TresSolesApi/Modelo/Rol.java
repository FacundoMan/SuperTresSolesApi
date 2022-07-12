package com.springboot.TresSolesApi.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;

@Entity
@Table(name="roles",uniqueConstraints= {@UniqueConstraint(columnNames= {"nombre"})})
@SequenceGenerator(name="rolSeq", initialValue=1000, allocationSize=10)
public class Rol {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rolSeq")
	private long id;
	
	@NonNull
	private String nombre;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Rol() {
		super();
	}
	
	
	
	
}
