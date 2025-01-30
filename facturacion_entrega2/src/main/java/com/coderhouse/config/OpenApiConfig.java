package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI facturacionAPI() {
		return new OpenAPI().info(new Info()
				.title("API REST Full | Java | Proyecto final")
				.version("3.0.0")
				.description("La API REST proporciona endpoints para administrar facturación "
                		+ "cuenta los modelos de producto, factura y cliente, con los que se "
                		+ "pueden realizar operacones CRUD (Crear, Leer, Actualizar, Eliminar) "
                		+ ". Los endpoints permiten listar, agregar, mostrar, "
                		+ "editar y eliminar los modelos mencionados. La API está documentada utilizando "
                		+ "Swagger, lo que facilita la comprensión de los endpoints y su uso.")
				.contact(new Contact()
						.name("Sara Guerrero")
						.email("saraguerrero234@gmail.com")
						)
				)
				.servers(List.of(new Server()
						.url("http://localhost:8080")
						.description("Servidor Local")));
	}

}
