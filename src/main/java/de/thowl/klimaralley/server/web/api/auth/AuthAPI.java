package de.thowl.klimaralley.server.web.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.expections.auth.DuplicateUserException;
import de.thowl.klimaralley.server.core.expections.auth.InvalidCredentialsException;
import de.thowl.klimaralley.server.core.services.auth.AuthenticationService;
import de.thowl.klimaralley.server.web.schema.auth.AuthentificationResponse;
import de.thowl.klimaralley.server.web.schema.auth.LoginSchema;
import de.thowl.klimaralley.server.web.schema.auth.RegisterSchema;
import de.thowl.klimaralley.server.web.schema.util.ResponseBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
	@Operation(
		summary = "Authenticate a user",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Successful operation",
				content= @Content(
					schema = @Schema(implementation = AuthentificationResponse.class),
					examples = @ExampleObject(
						value = "{ 'message': 'Authentication successful', 'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...'}"))),
			@ApiResponse(
				responseCode = "401",
				description = "Invalid credentials",
				content= @Content(
					schema = @Schema(implementation = ResponseBody.class),
					examples = @ExampleObject(
						value = "{ 'message': 'Invalid credentials' }")))
	})
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> doLogin(
		@Parameter(
			description = "User Login schema containing username and password",
			required = true,
			content = @Content(schema = @Schema(implementation = LoginSchema.class)))
        @RequestBody
        LoginSchema schema
	) {

		ResponseBody body;
		String email, password, token;

		log.info("entering doLogin (POST-Method: /login)");

		email = schema.getEmail();
		password = schema.getPassword();

		try {
			token = authsvc.login(email, password);
		} catch (InvalidCredentialsException e) {
			body = new ResponseBody();
			body.setMessage("Invalid credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}

		body = new AuthentificationResponse();
		body.setMessage("Authentication successful");
		((AuthentificationResponse) body).setToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(body);
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
	@Operation(
		summary = "Register a new User",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "User Registered",
				content = @Content(
					schema = @Schema(implementation = ResponseBody.class),
					examples = @ExampleObject(
						value = "{ 'message': 'User Registered' }"))),
			@ApiResponse(
				responseCode = "400",
				description = "Invalid credentials",
				content = @Content(
					schema = @Schema(implementation = ResponseBody.class),
					examples = @ExampleObject(
						value = "{ 'message': 'Invalid credentials' }"))),
			@ApiResponse(
				responseCode = "500",
				description = "User already exists",
				content = @Content(
					schema = @Schema(implementation = ResponseBody.class),
					examples = @ExampleObject(
						value = "{ 'message': 'User already exists' }"))),
	})
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> doRegister(
		@Parameter(
        	description = "User Register schema containing username and password",
        	required = true,
        	content = @Content(
            	schema = @Schema(implementation = RegisterSchema.class)))
        @RequestBody
        RegisterSchema schema
	) {

		ResponseBody body;

		log.info("entering doRegister (POST-Method: /register)");

		body = new ResponseBody();

		if (!authsvc.validateEmail(schema.getEmail())) {
			body.setMessage("Invalid Email");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		} else if (!authsvc.validatePassword(schema.getPassword())) {
			body.setMessage("Invalid Password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		} else if (!schema.getPassword().equals(schema.getVerifyPassword())) {
			body.setMessage("Passwords don't match");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		}

		try {
			this.authsvc.register(schema.getUsername(),schema.getEmail(), schema.getPassword());
		} catch (DuplicateUserException e) {
			body.setMessage("User already exists");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}

		body.setMessage("User registered");
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

}
