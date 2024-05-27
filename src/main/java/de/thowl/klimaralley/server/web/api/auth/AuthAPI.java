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
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.extern.slf4j.Slf4j;

/**
 * RESTful API for Authentication.
 *
 * @author Jonas Schwind
 * @version 1.0.0
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
	 *
	 * @param schema {@link LoginSchema} contained in the resqeust
	 *
	 * @return JWT token (status: {@code 200 OK}) on successfull login,
	 *	   {@code 201 UNAUTHORIZED} on unsuccessfull login
	 */
	@Operation(summary = "login")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successful operation",
			content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "401", description = "Invalid credentials"),
	})
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<Object> doLogin(LoginSchema schema) {

		String email, password, token;

		log.info("entering doLogin (POST-Method: /login)");

		email = schema.getEmail();
		password = schema.getPassword();

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
	 * @param schema {@link LoginSchema} contained in the resqeust
	 * 
	 * @return {@code 200 OK} on Register,
	 *         {@code 400 BAD REQUEST} on invalid credentials,
	 *         {@code 500 INTERNAL SERVER ERROR} if the user exists.
	 */
	@Operation(summary = "register")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User Registered"),
		@ApiResponse(responseCode = "400", description = "Invalid credentials"),
		@ApiResponse(responseCode = "500", description = "User already exists"),
	})
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<Object> doRegister(RegisterSchema form) {

		log.info("entering doRegister (POST-Method: /register)");

		if (!authsvc.validateEmail(form.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email"); 
		}

		if (!authsvc.validatePassword(form.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Password");
		}

		if (!form.getPassword().equals(form.getVerifyPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password don't match");
		}

		try {
			this.authsvc.register(form.getFirstname(), form.getLastname(), form.getUsername(),
					form.getEmail(), form.getPassword());
		} catch (DuplicateUserException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exists");
		}

		return ResponseEntity.status(HttpStatus.OK).body("User Registered");
	}

}
