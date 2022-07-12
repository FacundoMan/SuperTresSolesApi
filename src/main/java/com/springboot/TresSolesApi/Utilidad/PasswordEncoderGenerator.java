package com.springboot.TresSolesApi.Utilidad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
	public static void main(String[] args) {
		PasswordEncoder passwordEnc= new BCryptPasswordEncoder();
	System.out.println(passwordEnc.encode("gerente"));
	}
	
}
