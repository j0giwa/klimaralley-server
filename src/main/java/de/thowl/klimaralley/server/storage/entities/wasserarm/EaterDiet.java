package de.thowl.klimaralley.server.storage.entities.wasserarm;

/**
 * The diet an {@link Eater} can have, the diet determines the pool of possible food preferences.
 * 
 * An {@link Eater} is either vegetarian, vergan frutarian or does not have a special diet.
 * An {@link Eeater} cannot have mutliple Diets.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
public enum EaterDiet {
	NORMAL,
	VEGETARIAN,
	VEGAN,
        FRUTARIAN;
}
