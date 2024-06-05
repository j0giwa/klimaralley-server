package de.thowl.klimaralley.server.web.schema.auth;

import de.thowl.klimaralley.server.web.schema.util.ResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "User Authentification Token", description = "User Authentification response")
public class AuthentificationToken extends ResponseBody{

	@Schema(description = "jwt authentification token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
	private String token;
	
}
