package de.thowl.klimaralley.server.storage.entities.wasserarm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * A reprensentation of an Eater (a client)
 * 
 * The player is suposed to compile a meal for this Eater,
 * while concidering the Eaters diet (normal, vegetarian, vegan, frutarian), 
 * and personal proferences.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eater {
        private long id;

	private String name;

	private EaterDiet diet;

	private WasserarmShopItem[] preferernces;

}
