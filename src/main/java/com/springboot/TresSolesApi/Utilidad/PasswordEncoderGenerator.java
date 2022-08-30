package com.springboot.TresSolesApi.Utilidad;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
	public static void main(String[] args) {
		PasswordEncoder passwordEnc= new BCryptPasswordEncoder();
	System.out.println(passwordEnc.encode("repartidor"));
	 LocalDate todaysDate = LocalDate.now();
     System.out.println(todaysDate);
	
	}
	
}
