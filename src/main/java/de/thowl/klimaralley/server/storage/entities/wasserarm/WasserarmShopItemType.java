package de.thowl.klimaralley.server.storage.entities.wasserarm;

/**
 * The type of an {@link WasserarmShopItem}.
 * The type signalises which items are legible for certeain {@link EaterDiet}'s
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
public enum WasserarmShopItemType {
	FRUIT,
	VEGETABLE,
	NUTS,
	MEAT_AND_POULTRY,
	FISH_AND_SEAFOOD,
	DAIRY_FOODS,
	DRINKS;
}