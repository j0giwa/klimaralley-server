package de.thowl.klimaralley.server.web.schema.auth;

import lombok.Data;

@Data
public class RegisterSchema {

	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String password;
	private String verifyPassword;
}
