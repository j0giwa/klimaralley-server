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

@Slf4j
@RestController
@RequestMapping("/water")
@Tag(name = "Wasserarmsatt", description = "Wasserarmsatt API")
public class WasserAPI {

	/**
	 *  
	 */
	@Operation(summary = "Retrieves all shop items from the Database")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Wasserarmsatt shopitems as json", content = @Content),	
	})
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllItems() {
		log.info("entering getTask (GET-Method: /api/task/get)");

		return ResponseEntity.status(HttpStatus.OK).body("hello world");
	}

}