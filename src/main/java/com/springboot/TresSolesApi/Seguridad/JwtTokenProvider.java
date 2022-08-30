package com.springboot.TresSolesApi.Seguridad;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.TresSolesApi.Modelo.SupermercadoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	//Genera a el token con su algoritmo y su llave secreta y establece el usuario
	public String generarToken(Authentication authentication) {
		String username =authentication.getName();
		Date fechaActual=new Date();
		Date fechaExpiracion=new Date(fechaActual.getTime()+ jwtExpirationInMs);
		
		String  token=Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return token;
	}
	
	//Esto es para obtener el usuario
	public String obtenerUsernameJWT(String token) {
		Claims claims=Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	//Validar que el token este correcto con su llave secreta
	public boolean validarToken(String token) throws SupermercadoException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new SupermercadoException("Firma JWT no valida");
			
		}catch (MalformedJwtException e) {
			throw new SupermercadoException("Token JWT no valida");
		}catch (ExpiredJwtException e) {
			throw new SupermercadoException("Token JWT expirado");
		}catch (UnsupportedJwtException e) {
			throw new SupermercadoException("Token JWT no compatible");
		}catch (IllegalArgumentException e) {
			throw new SupermercadoException("La cadena claims JWT esta vacia");
		}
	}
	
	
}
