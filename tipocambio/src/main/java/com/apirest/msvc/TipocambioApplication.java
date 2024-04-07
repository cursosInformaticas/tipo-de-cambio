package com.apirest.msvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.apirest.msvc.cliente")
@SpringBootApplication
public class TipocambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipocambioApplication.class, args);
	}

}
