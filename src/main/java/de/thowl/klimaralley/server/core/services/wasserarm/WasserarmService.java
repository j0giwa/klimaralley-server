package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.core.expections.wasserarm.InvalidGameException;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;

/**
 * Provides servicelevel functions for the Wasserarm satt game.
 *
 * @author Jonas Schwind
 * @version 1.0.0
 */
public interface WasserarmService {

	/**
	 * Retrieves {@link WasserarmShopItem}s from the Database
	 *
     * @return a list of {@link WasserarmShopItem}
	 */
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

	/**
     * Gets the Water with the given id
	 *
	 * @param id Id of the eater
	 * @return Water of the {@link User} with the given id
     */
	public int getWater(long id);

    /**
	 * Add the Water with to the {@link User}
	 * {@see Eater}
	 *
	 * @param id Id of the eater
	 * @param amount of water to add
	 */
	public void addWater(long id, int amount);

    /**
     * Gets amount of Coins for the {@link User} with the given id
     *
	 * @param id Id of the eater
	 * @return Water of the {@link User} with the given id
     */
	public int getCoins(long id);

	/**
	 *
	 * @param eaterId     The players Eater
	 * @param items       The players chosen Items
	 * @param playerCoins The amount of coins the player has at their disposal
	 * @param playerWater The amount of water (in litres) the player has at their
	 *                    disposal
	 *
	 * @return Calculated game score
	 *
	 * @throws InvalidGameException when At least one param is {@code null} or
	 *                              eaterId matches no {@link Eater}.
	 */
	public int getScore(long eaterId, WasserarmShopItem[] items, int playerCoins, int playerWater) throws InvalidGameException;

}
