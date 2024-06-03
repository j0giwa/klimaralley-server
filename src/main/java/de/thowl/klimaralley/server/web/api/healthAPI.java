package de.thowl.klimaralley.server.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/health")
@Tag(name = "Healthcheck", description = "Test if server is running")
public class healthAPI {
	
	/**
	 * Get all shop items from the Database
	 *
	 * curl -X 'GET' 'http://localhost:8080/health'
	 * 
	 * @return {@link WasserarmShopItem}[] (code {@code 200 OK})
	 */
	@Operation(summary = "Performm Healthcheck")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", 
			description = "Wasserarmsatt shop items", 
			content = @Content(array = @ArraySchema(schema = @Schema(implementation = WasserarmShopItem.class)))),
	})
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> heathCheck() {
		log.info("entering heathCheck (GET-Method: /health/)");
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

}
