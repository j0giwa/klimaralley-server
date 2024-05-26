package de.thowl.klimaralley.server.web.api.wasserarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.services.wasserarm.WasserarmService;
import de.thowl.klimaralley.server.core.utils.auth.JWTtokenizer;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/water")
@Tag(name = "Wasserarmsatt", description = "Wasserarmsatt API")
public class WasserAPI {

	@Autowired
	private WasserarmService wassersvc;

	/**
	 * Healthcheck method to 'ping' the API
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/'
	 *
	 * @return status code {@code 200}
	 */
	@Operation(summary = "Healthcheck")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "Can be used to check if api is up",
			content = @Content),
	})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> healthCheck() {
		log.info("entering getTask (GET-Method: /water/get)");
		// TODO: Stub
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	/**
	 * Get all shop items from the Database
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/items'
	 * 
	 * @return status code {@code 501} // TODO: Delete
	 * @return repsonse (code {@code 200}) with all shop items as JSON
	 */
	@Operation(summary = "Retrieves all shop items from the Database")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", 
			description = "Wasserarmsatt shopitems as json", 
			content = @Content),
		})
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllItems() {
		log.info("entering getAllItems (GET-Method: /water/items)");
		return ResponseEntity.status(HttpStatus.OK).body(wassersvc.getAll());
	}

	/**
	 * Retrieves a {@link Eater} to serve.
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/eater'
	 * 
	 * @return repsonse (code {@code 200}) with all shop items as JSON
	 */
	@Operation(summary = "Retrieves a Eater to serve")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Wasserarmsatt Eaater information as json", content = @Content),
		}
	)
	@RequestMapping(value = "/eater", method = RequestMethod.GET)
	public ResponseEntity<Object> getEater() {
		
		Eater eater;

		log.info("entering getAllItems (GET-Method: /water/eater)");

		eater = this.wassersvc.generateEater();

		return ResponseEntity.status(HttpStatus.OK).body(eater);
	}

	/**
	 * Calculates score bases on given items
	 *  
	 * @return repsonse (code {@code 200}) with the calculated score
	 */
    	@Operation(summary = "Retrieves game score based on items")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "501", description = "Wasserarmsatt score", content = @Content)
	})
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ResponseEntity<Object> getScore(@Parameter(description = "JWT token") @RequestParam(required = false) String token) {

		Claims claims;

		log.info("entering getScore (GET-Method: /water/score)");

		claims = JWTtokenizer.parseToken(token);

		// enter scoreboard: TODO: stub
		if (claims != null) {
			String userId = claims.getSubject();
			log.info("Authenticated user ID: " + userId);
		}

		// TODO: Stub
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}
}
