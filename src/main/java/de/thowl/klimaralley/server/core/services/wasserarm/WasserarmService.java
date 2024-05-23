package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;

/**
 * Provides servicelevel functions for the Wasserarm satt game.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
public interface WasserarmService {

	public List<WasserarmShopItem> getAll();

        /**
         * Generates a new instance of {@link Eater}
         * {@see Eater}
         * 
         * @return a instance of {@link Eater}
         */
        public Eater generateEater();

        /**
         * Gets the {@link Eater} with the given id
         * {@see Eater}
         * 
         * @param id Id of the eater
         * @return {@link Eater} with the given id 
         */
        public Eater getEater(long id);
}
