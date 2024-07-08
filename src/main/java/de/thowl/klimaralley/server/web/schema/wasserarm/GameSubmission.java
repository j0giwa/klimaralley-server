package de.thowl.klimaralley.server.web.schema.wasserarm;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Game submission from player. Will be used to caclulate score.
 * 
 * @author Jonas Schwind
 * @version 0.2.0
 */
@Data
@Schema(name = "Wasserarm-satt game submission", description = "Schema for sending in a wasserarm satt game.")
public class GameSubmission {

	@Schema(description = "ID of the Player's Eater", example = "115")
	private long eaterId;

	@Schema(description = "Players shopping cart")
	private List<WasserarmShopItem> items = new ArrayList<>();

}
