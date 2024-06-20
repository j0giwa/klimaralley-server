package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;
import java.util.Random;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.storage.repository.auth.UserRepository;
import de.thowl.klimaralley.server.storage.repository.wasserarm.EaterRepsoitory;
import de.thowl.klimaralley.server.storage.repository.wasserarm.ItemRepository;
import de.thowl.klimaralley.server.core.expections.wasserarm.NoSuchEaterException;
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

	final int PREFS_SIZE = 3;

	// Not real word values
	final int VEGETARIAN_CHANCE = 15;
	final int VEGAN_CHANCE = 10;
	final int FRUTATRIAN_CHANCE = 2;

	@Autowired
	private EaterRepsoitory eaters;

	@Autowired
	private ItemRepository wasserarmShopItems;

	@Autowired
	private UserRepository users;

	public List<WasserarmShopItem> getAll() {

		log.debug("entering getAll");

		return wasserarmShopItems.findAll();
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
	 * Generates an Array of {@link WasserarmShopItem}s that the {@link Eater} prefers.
	 *
	 * @return Array of prefered {@link WasserarmShopItem}s 
	 */
	private WasserarmShopItem[] generatePreferences() {

		List<WasserarmShopItem> items;
		WasserarmShopItem[] prefs;
		long unixtime;
		Random rng;

		log.debug("entering GenerateEater");
		
		unixtime = System.currentTimeMillis() / 1000L;
		rng = new Random(unixtime);
		items = wasserarmShopItems.findAll();
		prefs = new WasserarmShopItem[PREFS_SIZE];

		for(int i = 0; i <= PREFS_SIZE - 1; i++) {
			prefs[i] = items.get(rng.nextInt(items.size()));
		}

		return prefs;
	} 

	/**
	 * {@inheritDoc}
	 */ 
	@Override
        public Eater generateEater() {

		Eater eater;

		log.debug("entering GenerateEater");
		
		eater = new Eater();
		eater.setId(eaters.countAll());
		eater.setName(generateRandomName(false));
		eater.setDiet(generateDiet());
		eater.setPreferernces(generatePreferences());

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
	public int getScore(long eaterId, WasserarmShopItem[] items) {
	
		int variety, matchedPrefs, score;
		Eater eater;
		WasserarmShopItem[] eaterPrefs;

		log.debug("entering getScore");

		score = 0;

		variety = items.size;
		eater = this.eaters.findById(eaterId);
		eaterPrefs = eater.generatePreferences();

		// TODO: count matched prefs

		// TODO: calctate score
		score = 0; 
	
		return score;
	}

}
