package com.springboot.TresSolesApi.Modelo.DTO;

import java.util.ArrayList;
import java.util.List;

public class JWTAuthResponseDTO {
	private String tokenAcceso;
	private String tipoToken="Bearer";
	private List<String> roles =new ArrayList<>();
	
	
	
	public JWTAuthResponseDTO(String tokenAcceso) {
		super();
		this.tokenAcceso = tokenAcceso;
	}
	public JWTAuthResponseDTO(String tokenAcceso, String tipoToken) {
		super();
		this.tokenAcceso = tokenAcceso;
		this.tipoToken = tipoToken;
	}
	
	public String getTokenAcceso() {
		return tokenAcceso;
	}
	public void setTokenAcceso(String tokenAcceso) {
		this.tokenAcceso = tokenAcceso;
	}
	public String getTipoToken() {
		return tipoToken;
	}
	public void setTipoToken(String tipoToken) {
		this.tipoToken = tipoToken;
	}
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRol(String rol) {
		roles.add(rol);
	}
	
	
	
	
}
