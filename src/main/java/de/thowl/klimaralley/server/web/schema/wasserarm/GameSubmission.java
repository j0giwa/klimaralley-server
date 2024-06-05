package de.thowl.klimaralley.server.web.schema.wasserarm;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "Wasserarm-satt game submission", description = "Schema for sending in a wasserarm satt game.")
public class GameSubmission {

	@Schema(description = "ID of the Player's Eater", example = "115")
	private long eaterId;

}
