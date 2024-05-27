package de.thowl.klimaralley.server.web.api.wasserarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.services.wasserarm.WasserarmService;
import de.thowl.klimaralley.server.core.utils.auth.Tokenizer;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * RESTful API of the Wasserarmsatt game.
 *
 * This API is a stub and subject to change.
 *
 * @author Jonas Schwind
 * @version 0.6.0
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
	 * @return status code {@code 200}
	 */
	@Operation(summary = "Healthcheck")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Can be used to check if api is up"),
	})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> healthCheck() {
		log.info("entering getTask (GET-Method: /water/get)");
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	/**
	 * Get all shop items from the Database
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/items'
	 * 
	 * @return {@link WasserarmShopItem}[] (code {@code 200 OK})
	 */
	@Operation(summary = "Retrieves all shop items from the Database")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
			description = "Wasserarmsatt shop items", 
			content = @Content(array = @ArraySchema(schema = @Schema(implementation = WasserarmShopItem.class)))),
	})
	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = "application/json")
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
		@ApiResponse(responseCode = "200",
			description = "Wasserarmsatt Eater information",
			content = @Content(schema = @Schema(implementation = Eater.class))),
	})
	@RequestMapping(value = "/eater", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getEater() {
		
		Eater eater;

		log.info("entering getAllItems (GET-Method: /water/eater)");

		eater = this.wassersvc.generateEater();

		return ResponseEntity.status(HttpStatus.OK).body(eater);
	}

	/**
	 * Calculates score bases on given items
	 *
	 * NOTE: The swagger ui generates a wrong curl, call with:
	 * curl -X 'POST' \
	 *   'localhost:8080/water/score' \
	 *   -H 'accept text/plain'
	 *   -H 'Authorization: Bearer <TOKEN>'
	 *  
	 * @return repsonse (code {@code 200}) with the calculated score
	 */
    	@Operation(summary = "Retrieves game score based on items")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "501", description = "Wasserarmsatt score")
	})
	@RequestMapping(value = "/score", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<Object> getScore(@RequestHeader(name = "Authorization", required = false) String token) {

		Claims claims;

		log.info("entering getScore (GET-Method: /water/score)");

		claims = Tokenizer.parseToken(Tokenizer.getBearer(token));

		// enter scoreboard: TODO: stub
		if (claims != null) {
			String userId = claims.getSubject();
			log.info("Authenticated user ID: " + userId);
		}

		// TODO: Stub
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Not implemented");
	}
}
