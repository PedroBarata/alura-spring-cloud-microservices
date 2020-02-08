package br.com.barata.microservice.fornecedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class FornecedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FornecedorApplication.class, args);
	}

}
