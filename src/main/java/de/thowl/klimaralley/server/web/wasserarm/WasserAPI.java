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
 * API is a stub and subject to change.
 */
@Slf4j
@RestController
@RequestMapping("/water")
@Tag(name = "Wasserarmsatt", description = "Wasserarmsatt API")
public class WasserAPI {

	/**
	 *  
	 */
	@Operation(summary = "Healthcheck")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Can be used to check if api is up", content = @Content),
	})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> test() {
		log.info("entering getTask (GET-Method: /water/get)");
		return ResponseEntity.status(HttpStatus.OK).body("hello world");
	}

	/**
	 *  
	 */
	@Operation(summary = "Retrieves all shop items from the Database")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "501", description = "Wasserarmsatt shopitems as json", content = @Content),	
	})
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllItems() {
		log.info("entering getAllItems (GET-Method: /water/items)");
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}

	/**
	 *  
	 */
	@Operation(summary = "Retrieves all shop items from the Database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "501", description = "Wasserarmsatt score", content = @Content),
	})
	@RequestMapping(value = "/get/score", method = RequestMethod.GET)
	public ResponseEntity<Object> getScore() {
		log.info("entering getScore (GET-Method: /water/score)");
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}

	/**
	 *  
	 */
	@Operation(summary = "Retrieves all shop items from the Database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "501", description = "Submit cart items", content = @Content),
	})
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public ResponseEntity<Object> setCartItems() {
		log.info("entering setCartItems (POST-Method: /water/items)");
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}

}