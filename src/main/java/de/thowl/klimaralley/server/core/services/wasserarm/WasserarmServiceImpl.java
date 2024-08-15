package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.Base64;
import java.util.List;
import java.util.Random;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItemType;
import de.thowl.klimaralley.server.storage.repository.auth.UserRepository;
import de.thowl.klimaralley.server.storage.repository.wasserarm.EaterRepsoitory;
import de.thowl.klimaralley.server.storage.repository.wasserarm.ItemRepository;
import de.thowl.klimaralley.server.core.expections.wasserarm.InvalidGameException;
import de.thowl.klimaralley.server.core.expections.wasserarm.NoSuchEaterException;
import de.thowl.klimaralley.server.core.utils.Counter;
import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import de.thowl.klimaralley.server.storage.entities.wasserarm.EaterDiet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;

/**
 * {@inheritDoc}
 *
 * @author Jonas Schwind
 * @version 0.3.0
 */
@Slf4j
@Service
public class WasserarmServiceImpl implements WasserarmService {

	/*
	 * The spawn chances do not reflect real world statistics.
	 *
	 * BUG: Keep them in decenending order or it will break.
	 * VEGETARIAN_CHANCE > VEGAN_CHANCE > FRUTATRIAN_CHANCE
	 */
	final int VEGETARIAN_CHANCE = 15;
	final int VEGAN_CHANCE = 10;
	final int FRUTATRIAN_CHANCE = 2;

	final int PREFS_SIZE = 3;

	@Autowired
	private EaterRepsoitory eaters;

	@Autowired
	private ItemRepository wasserarmShopItems;

	@Autowired
	private UserRepository users;

	/**
	 * Base64-encodes the Icon of the {@link WasserarmShopItem}
	 *
	 * @param items A list of {@link WasserarmShopItem}s
	 * @return A list of {@link WasserarmShopItem}s with encoded attachments
	 */
	private List<WasserarmShopItem> encodeItems(List<WasserarmShopItem> items) {
		for (WasserarmShopItem item : items) {
			if(item.getWebp() == null){
				continue;
			}
			item.setIcon(Base64.getEncoder().encodeToString(item.getWebp()));
		}
		return items;
	}

        /**
         * {@inheritDoc}
         */
	public List<WasserarmShopItem> getAll() {

		log.debug("entering getAll");

		return encodeItems(this.wasserarmShopItems.findAll());
	}

	/**
	 *  Genenrates a random name for the {@link Eater}
	 *
	 *  @param generateSurname wether or not a surname is included
	 *  @return Name
	 */
	private String generateRandomName(boolean generateSurname){

		Faker faker;

		log.debug("entering generateName");

		faker = new Faker();

		if (generateSurname) {
			return faker.name().firstName();
		}

		return faker.name().name();
	}

	/**
	 * Determinaes a Diet (Normal, Vegetarian, Vegean or Fruatarian) for the {@link Eater}.
	 *
	 * @return a diet
	 */
	private EaterDiet generateDiet() {

		EaterDiet diet;
		long unixtime;
		int val;
		Random rng;

		log.debug("entering generateDiet");

		unixtime = System.currentTimeMillis() / 1000L;
		rng = new Random(unixtime);
		val = rng.nextInt(101);

		log.debug("Eatergen rng-value: {}",val);

		// BUG: Change the spawn weights and this stops working, but idfk.
		if (val <= FRUTATRIAN_CHANCE) {
			diet = EaterDiet.FRUTARIAN;
		} else if (val <= VEGAN_CHANCE) {
			diet = EaterDiet.VEGAN;
		} else if (val <= VEGETARIAN_CHANCE) {
			diet = EaterDiet.VEGETARIAN;
		} else {
			diet = EaterDiet.NORMAL;
		}

		log.debug("eater-diet: {}", diet.toString());

		return diet;
	}

	/**
	 * Checks if a {@link WasserarmShopItem} applys for a Vegetarian diet.
	 *
	 * @param item The {@link WasserarmShopItem} to Check.
	 * @return {@code true} if the {@link WasserarmShopItem} applys, else return {@code false}.
	 */
	private boolean isVegetarian(WasserarmShopItem item){
		if (item.getType().equals(WasserarmShopItemType.MEAT_AND_POULTRY)) {
			return false;
		}

		if (item.getType().equals(WasserarmShopItemType.FISH_AND_SEAFOOD)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if a {@link WasserarmShopItem} applys for a Vegan diet.
	 *
	 * @param item The {@link WasserarmShopItem} to Check.
	 * @return {@code true} if the {@link WasserarmShopItem} applys, else return {@code false}.
	 */
	private boolean isVegan(WasserarmShopItem item) {

		// A Vegan is just a vagetarian with extra steps
		if (!isVegetarian(item)) {
			return false;
		}

		if (item.getType().equals(WasserarmShopItemType.DAIRY_FOODS)) {
			return false;
		}

		return true;
	}

	/**
	 * Find an Instance of {@link WasserarmShopItem}, based on an {@link Eaters} diet.
	 *
	 * @param diet The {@link EaterDiet} of the {@link Eater}.
	 * @return Array of prefered {@link WasserarmShopItem}s
	 */
	private WasserarmShopItem findWasserarmPrefItem(EaterDiet diet) {

		WasserarmShopItem item;
		List<WasserarmShopItem> items;
		long unixtime;
		Random rng;

		log.debug("entering GenerateEater");

		unixtime = System.currentTimeMillis() / 1000L;
		rng = new Random(unixtime);
		items = wasserarmShopItems.findAll();

		item = null;

		/*
		 * SMELL: This is some stupid ass code.
		 * One change to the EaterDiets enum and the whole thing breaks.
		 */
		switch (diet) {
			default:
			case EaterDiet.NORMAL:
				return items.get(rng.nextInt(items.size()));

			case EaterDiet.VEGETARIAN:
				while (true) {
					item = items.get(rng.nextInt(items.size()));
					if (!isVegetarian(item)) {
						continue; // YEET
					}
					return item;
				}

			case EaterDiet.VEGAN:
				while (true) {
					item = items.get(rng.nextInt(items.size()));
					if (!isVegan(item)) {
						continue; // YEET
					}
					return item;
				}

			case EaterDiet.FRUTARIAN:
				// In this case the eater should not have preferernces.
				return null;
		}
	}

	/**
	 * Generates an Array of {@link WasserarmShopItem}s that the {@link Eater} prefers, based on diet.
	 *
	 * @param diet The {@link EaterDiet} of the {@link Eater}.
	 * @return Array of prefered {@link WasserarmShopItem}s
	 */
	private WasserarmShopItem[] generatePreferences(EaterDiet diet) {

		WasserarmShopItem[] prefs;

		log.debug("entering GenerateEater");

		prefs = new WasserarmShopItem[PREFS_SIZE];

		for(int i = 0; i <= PREFS_SIZE - 1; i++) {
			prefs[i] = findWasserarmPrefItem(diet);
		}

		return prefs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public Eater generateEater() {

		Eater eater;
		EaterDiet diet;

		log.debug("entering GenerateEater");

		eater = new Eater();
		eater.setId(eaters.countAll());
		eater.setName(generateRandomName(false));
		diet = generateDiet();
		eater.setDiet(diet);
		eater.setPreferernces(generatePreferences(diet));

		log.info("storing Eater");
		this.eaters.save(eater);

		return eater;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Eater getEater(long id) {

		Eater eater;

		log.debug("entering getEater");

		try {
			eater = eaters.findById(id);
			return eater;
		} catch (NoSuchEaterException e) {
			log.error("No id with eater: {}", id);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWater(long id) {

		User user;

		log.debug("entering getEater");

		user = this.users.findById(id).get();

		return user.getWater();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addWater(long id, int amount) {

		User user;

		log.debug("entering getEater");

		user = this.users.findById(id).get();

		user.setWater(user.getWater() + amount);

		this.users.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCoins(long id) {

		User user;

		log.debug("entering getCoins");

		user = this.users.findById(id).get();

		return user.getWater();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getScore(long eaterId, WasserarmShopItem[] items, int playerCoins, int playerWater) throws InvalidGameException {

		int score, variety, matchedPrefs, totalPrice;
		Eater eater;
		WasserarmShopItem[] eaterPrefs;

		log.debug("entering getScore");

		// NOTE: Playerwater and coins are not guarded, as they could be 0
		if (eaterId < 0) {
			throw new InvalidGameException();
		}

		if (items == null || items.length == 0) {
			throw new InvalidGameException();
		}

		try {
			eater = this.eaters.findById(eaterId);
		} catch (NoSuchEaterException e) {
			throw new InvalidGameException();
		}

		variety = items.length;
		eaterPrefs = eater.getPreferernces();
		matchedPrefs = Counter.countDupliactes(items, eaterPrefs);

		totalPrice = 0;
		for (WasserarmShopItem item : items) {
			totalPrice += item.getPrice();
		}

		double epsilon = 1e-6; // HACK: Tiny constant to prevent division by zero
		double calculation = (variety / 5.0) * (playerWater / (matchedPrefs + epsilon)) * Math.log((playerCoins - totalPrice) + 1);
		score = (int) Math.round(calculation);

		return score;
	}
}
