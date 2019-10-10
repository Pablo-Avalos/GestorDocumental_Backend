package com.gestorDocumental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.gestorDocumental.entity.Cliente;
import com.gestorDocumental.helper.SQLHelper;


//@ComponentScan({"com.delivery.request"})


@SpringBootApplication
@ComponentScan(basePackages = { "com.gestorDocumental.*"})
//@EnableJpaRepositories
@EnableJpaAuditing
public class GestorDocumentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorDocumentalApplication.class, args);
	}
	
	@Bean
	public SQLHelper getSQLHelper() {
		return new SQLHelper();
	}
	@Bean
	public Cliente getCliente() {
		return new Cliente();
	}
	//@Bean
	//public DocumentoDigitalRepositorio getDocumentoDigitalRepositorio() {
	//	return getDocumentoDigitalRepositorio();
	//}
	
}
