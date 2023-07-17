package com.codewithrizwan.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
							name = "Rizwan Ahmed",
							email = "rizwanahmed827013@gmail.com"
						),
				title = "Blogging App API",
				version = "1.0"
			
			
				
		),
		servers = {
				@Server(
						description = "Local Environment",
						url = "http://localhost:9090"
				)
		},
		security = @SecurityRequirement(
					name = "bearerAuth"
				)
)

@SecurityScheme(
		name = "bearerAuth",
		description = "JWT Auth description",
		scheme = "bearer",
		type=SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
