package de.thowl.klimaralley.server.storage.wasserarm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import de.thowl.klimaralley.server.storage.entities.wasserarm.EaterDiet;
import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItemType;
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
		WasserarmShopItem prefItem;

		log.debug("entering testStore");

		prefItem = new WasserarmShopItem();
		prefItem.setName("Apple");
		prefItem.setType(WasserarmShopItemType.FRUIT);
		prefItem.setWater(2);
		prefItem.setPrice(2);

		eater = new Eater(2, "Heimdall", EaterDiet.NORMAL, new WasserarmShopItem[]{prefItem});
		assertTrue(eaters.save(eater), "Eater was not stored");
	}


	@Test
	void testFindById(){

		log.debug("entering testFindById");



	}


}

