package de.thowl.klimaralley.server.web.api.flut;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.thowl.klimaralley.server.core.services.flut.FlutService;
import de.thowl.klimaralley.server.storage.entities.flut.Building;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import de.thowl.klimaralley.server.storage.entities.flut.FlutMaxLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * 
 * REST controller for handling MaxFlutLevel data.
 * Allows fetching and saving the maximum flood level for a user.
 * 
 * @author Cedric Bourgeois
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/flut")
public class FlutAPI {

    @Autowired
    private FlutService flutService;

    /**
     * Retrieves the maximum flood level for a specific user.
     * 
     * @param userId The ID of the user whose maximum flood level is to be retrieved.
     * @return The maximum flood level as an Integer for the specified user.
     */
    @GetMapping("/{userId}")
    public Integer getFlutMaxLevel(@PathVariable Long userId) {
        return flutService.getFlutMaxLevelByUserId(userId);
    }

    /**
     * Saves the maximum flood level for a specific user.
     * 
     * @param request An object containing the user ID and the flood level value.
     * @return The saved FlutMaxLevel object.
     */
    @PostMapping
    public FlutMaxLevel saveFlutMaxLevel(@RequestBody FlutRequest request) {
        return flutService.saveFlutMaxLevel(request.getUserId(), request.getValue());
    }

    /**
     * Helper class to represent the request for saving a flood level.
     * Contains the user ID and the flood level value.
     */
    public static class FlutRequest {
        private Long userId;
        private Integer value;

        // Getters and setters

        /**
         * Retrieves the user ID.
         * 
         * @return The user ID.
         */
        public Long getUserId() {
            return userId;
        }

        /**
         * Sets the user ID.
         * 
         * @param userId The user ID to be set.
         */
        public void setUserId(Long userId) {
            this.userId = userId;
        }

        /**
         * Retrieves the maximum flood level value.
         * 
         * @return The flood level value.
         */
        public Integer getValue() {
            return value;
        }

        /**
         * Sets the maximum flood level value.
         * 
         * @param value The flood level value to be set.
         */
        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
/* 
**
 * RESTful API of the Wasserarm satt game.
 *
 * This API is a stub and subject to change.
 *
 * @author Cedric Bourgeois
 * @version 0.1.0
 *
@Slf4j
@RestController
@RequestMapping("/flut")
@Tag(name = "DieFlutKommt", description = "DieFlutKommt API")
public class FlutAPI {
    @Autowired
    private FlutService flutsvc;

    
    
    
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
*/