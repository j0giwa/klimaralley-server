package de.thowl.klimaralley.server.web.schema.auth;

import de.thowl.klimaralley.server.web.schema.util.ResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "User Authentification Response", description = "User Authentification token")
public class AuthentificationResponse extends ResponseBody{

	@Schema(description = "jwt authentification token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
	private String token;
	
}
