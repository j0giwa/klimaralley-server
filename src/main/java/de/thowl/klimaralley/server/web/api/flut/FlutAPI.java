package de.thowl.klimaralley.server.web.api.flut;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.services.flut.FlutService;
import de.thowl.klimaralley.server.storage.entities.flut.Building;

// import com.thowl.Flut.core.FlutService;
// import com.thowl.Flut.storage.entities.Building;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * RESTful API of the Wasserarm satt game.
 *
 * This API is a stub and subject to change.
 *
 * @author Cedric Bourgeois
 * @version 0.1.0
 */
@Slf4j
@RestController
@RequestMapping("/flut")
@Tag(name = "DieFlutKommt", description = "DieFlutKommt API")
public class FlutAPI {
    @Autowired
    private FlutService flutsvc;

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
		// TODO: Stub
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
    
    /**
     * 
     */
    @Operation(summary = "get Buildings")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200",
			description = "Flut Buildings information",
			content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = Building.class))),
	})
    @RequestMapping(value = "/buildings", method = RequestMethod.GET)
	public ResponseEntity<Object> getBuildings() {
		
		ArrayList<Building> buildings;

		log.info("entering getAllBuildings (GET-Method: /flut/buildings)");

		buildings = this.flutsvc.getBuildings();

		return ResponseEntity.status(HttpStatus.OK).body(buildings);
	}
}
