package de.thowl.klimaralley.server.web.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Klimaralley API", 
		version = "0.2.0",
                description = "RESTful Backend for the Klimaralley project prototype"),
        servers = {
                @Server(url = "http://localhost:8080", description = "Development"),
                @Server(url = "${api.server.url}", description = "Production")})
public class OpenApiConfig {

	/**
	 * Configure the OpenAPI components.
	 *
	 * @return Returns fully configure OpenAPI object
	 * @see OpenAPI
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes("bearerAuth", new SecurityScheme()
					.type(SecurityScheme.Type.HTTP)
					.description("Provide the JWT token. JWT token can be obtained from the Authenification API.")
					.scheme("bearer")
					.bearerFormat("JWT")));
	}

}