package de.thowl.klimaralley.server.web.wasserarm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/water")
@Tag(name = "Wasserarmsatt", description = "Wasserarmsatt API")
public class WasserAPI {

	/**
	 * Healthcheck method to 'ping' the API
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/'
	 *
	 * @return status code {@code 200}
	 */
	@Operation(summary = "Healthcheck")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Can be used to check if api is up", content = @Content),
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
		@ApiResponse(responseCode = "501", description = "Wasserarmsatt shopitems as json", content = @Content),	
	})
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllItems() {
		log.info("entering getAllItems (GET-Method: /water/items)");
		// TODO: Stub
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}

	/**
	 * Calculates score bases on given items
	 *  
	 * @return status code {@code 501} // TODO: Delete
	 * @return repsonse (code {@code 200}) with the calculated score
	 */
	@Operation(summary = "Retrieves game score based on items")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "501", description = "Wasserarmsatt score", content = @Content),
	})
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ResponseEntity<Object> getScore() {
		log.info("entering getScore (GET-Method: /water/score)");
		// TODO: Stub
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}
}
