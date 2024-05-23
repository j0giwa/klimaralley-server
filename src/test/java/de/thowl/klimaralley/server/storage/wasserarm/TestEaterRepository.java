package de.thowl.klimaralley.server.storage.wasserarm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import de.thowl.klimaralley.server.storage.entities.wasserarm.EaterDiet;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Item;
import de.thowl.klimaralley.server.storage.entities.wasserarm.ItemType;
import de.thowl.klimaralley.server.storage.repository.wasserarm.EaterRepsoitory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class TestEaterRepository {

	@Autowired
	private EaterRepsoitory eaters;

	@Test
	void testStore() {
		Eater eater;
		Item prefItem;

		log.debug("entering testStore");

		prefItem = new Item();
		prefItem.setName("Apple");
		prefItem.setType(ItemType.FRUIT);
		prefItem.setWater(2);
		prefItem.setPrice(2);

		eater = new Eater(2, "Heimdall", EaterDiet.NORMAL, new Item[]{prefItem});
		assertTrue(eaters.store(eater), "Eater was not stored");
	}


	@Test
	void testFindById(){

		log.debug("entering testFindById");



	}


}

