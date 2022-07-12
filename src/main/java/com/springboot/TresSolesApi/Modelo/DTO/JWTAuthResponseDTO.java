package com.springboot.TresSolesApi.Modelo.DTO;

public class JWTAuthResponseDTO {
	private String tokenAcceso;
	private String tipoToken="Bearer";
	
	
	
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
	
	
}
