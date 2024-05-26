package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.storage.repository.wasserarm.EaterRepsoitory;
import de.thowl.klimaralley.server.storage.repository.wasserarm.ItemRepository;
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

	@Autowired
	private EaterRepsoitory eaters;

	@Autowired
	private ItemRepository wasserarmShopItems;

	public List<WasserarmShopItem> getAll() {
		return wasserarmShopItems.findAll();
	}

	private String generateRandomName(boolean generateSurname){

		Faker faker;

		log.debug("entering generateName");

		faker = new Faker();
		
		if (generateSurname) {
			return faker.name().firstName();
		} 

		return faker.name().name();
	}

        public Eater generateEater() {

		Eater eater;

		log.debug("entering GenerateEater");
		
		eater = new Eater();
		eater.setId(eaters.countAll());
		eater.setName(generateRandomName(false));
		eater.setDiet(EaterDiet.NORMAL);
		eater.setPreferernces(null);

		log.info("storing Eater");
		this.eaters.save(eater);

		return eater;

	}

	@Override
	public Eater getEater(long id) {
		// TODO: Implement
		throw new UnsupportedOperationException("Unimplemented method 'getEater'");
	}


}
