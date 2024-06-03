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
@Tag(name = "Authentfication", description = "Authentfication API, Contains opereations Login and Register")
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
	 * NOTE: The user needs to provide a "secure" password
	 * This is to protect the users from their own stupidity.
	 *
	 * Password requirements:
	 * <ul>
	 * <li>Minimum eight characters</li>
	 * <li>at least one upper case English letter</li>
	 * <li>at least one lower case English letter</li>
	 * <li>at least one number</li>
	 * <li>at least one special character</li>
	 * </ul>
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
	public ResponseEntity<Object> doRegister(RegisterSchema schema) {

		log.info("entering doRegister (POST-Method: /register)");

		if (!authsvc.validateEmail(schema.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email"); 
		} else if (!authsvc.validatePassword(schema.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Password");
		} else if (!schema.getPassword().equals(schema.getVerifyPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password don't match");
		}

		try {
			this.authsvc.register(schema.getFirstname(), schema.getLastname(), schema.getUsername(),
					schema.getEmail(), schema.getPassword());
		} catch (DuplicateUserException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exists");
		}

		return ResponseEntity.status(HttpStatus.OK).body("User Registered");
	}

}
