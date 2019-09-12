package com.gestorDocumental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.gestorDocumental.helper.SQLHelper;

@SpringBootApplication
public class GestorDocumentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorDocumentalApplication.class, args);
	}
	
	@Bean
	public SQLHelper getSQLHelper() {
		return new SQLHelper();
	}

}
