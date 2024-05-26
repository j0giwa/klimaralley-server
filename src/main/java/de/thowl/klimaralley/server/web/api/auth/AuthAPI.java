package de.thowl.klimaralley.server.web.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.expections.auth.DuplicateUserException;
import de.thowl.klimaralley.server.core.expections.auth.InvalidCredentialsException;
import de.thowl.klimaralley.server.core.services.auth.AuthenticationService;
import de.thowl.klimaralley.server.web.schema.auth.LoginSchema;
import de.thowl.klimaralley.server.web.schema.auth.RegisterSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;

/**
 * RESTful API of the Wasserarm satt game.
 * This API is a stub and subject to change.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "auth", description = "Authentfication API")
public class AuthAPI {

	@Autowired
	private AuthenticationService authsvc;

	/**
	 * Performs a login action
	 */
	@Operation(summary = "login")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "JSON Web Token", content = @Content),
		@ApiResponse(responseCode="401",description="Invalid credentials",content=@Content),
	})
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> doLogin(LoginSchema form) {

		String email, password, token;

		log.info("entering doLogin (POST-Method: /login)");

		email = form.getEmail();
		password = form.getPassword();

		try {
			token = authsvc.login(email, password);
		} catch (InvalidCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}

		return ResponseEntity.status(HttpStatus.OK).body(token);
	}

	/**
	 * Registers a new user.
	 * 
	 * @return to register page
	 */
	@Operation(summary = "register")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "null", content = @Content),
			@ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content),
			@ApiResponse(responseCode = "500", description = "User already exists", content = @Content),
	})
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Object> doRegister(RegisterSchema form) {

		log.info("entering doRegister (POST-Method: /register)");

		if (!authsvc.validateEmail(form.getEmail())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); 
		}

		if (!authsvc.validatePassword(form.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}

		if (!form.getPassword().equals(form.getVerifyPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}

		try {
			this.authsvc.register(form.getFirstname(), form.getLastname(), form.getUsername(),
					form.getEmail(),
					form.getPassword());
		} catch (DuplicateUserException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exists");
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
