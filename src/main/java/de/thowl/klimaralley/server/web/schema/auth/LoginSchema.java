package de.thowl.klimaralley.server.web.schema.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "User Login" ,description = "Schema for user login")
public class LoginSchema {

	@Schema(description = "Accounts's email address", example = "joe.shmoe@example.com")
	private String email;

	@Schema(description = "Password for the account", example = "SecurePassword123!")
	private String password;
}
