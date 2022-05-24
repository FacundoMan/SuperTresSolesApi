package com.springboot.TresSolesApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.BeanProperty;
import com.springboot.TresSolesApi.Utilidad.Utilidades;

//(scanBasePackages={"com.springboot.TresSolesApi"})
@SpringBootApplication
public class TresSolesApiApplication implements  CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TresSolesApiApplication.class, args);
		System.out.println("Probando");
		
	}

	@Override
	public void run(String... args) throws Exception {
//		String sql="Select * from Probando";
//	List<Probando> probandos=	jdbcTemplates.query(sql, BeanPropertyRowMapper.newInstance(Probando.class));
//		probandos.forEach(System.out::println);
	}

}
