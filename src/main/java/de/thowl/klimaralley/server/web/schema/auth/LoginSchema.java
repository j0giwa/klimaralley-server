package de.thowl.klimaralley.server.web.schema.auth;

import lombok.Data;

@Data
public class LoginSchema {

	private String email;
	private String password;
}
