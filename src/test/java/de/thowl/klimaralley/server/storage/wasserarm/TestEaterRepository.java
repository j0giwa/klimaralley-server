package de.thowl.klimaralley.server.storage.wasserarm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import de.thowl.klimaralley.server.core.expections.wasserarm.NoSuchEaterException;
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

	@BeforeEach
    	void setUp() {
		
		Eater eater;
		WasserarmShopItem prefItem;

		log.debug("Setting up an EaterRepsoitory test");

        	prefItem = new WasserarmShopItem();
        	prefItem.setName("Melon");
        	prefItem.setType(WasserarmShopItemType.FRUIT);
        	prefItem.setWater(2);
        	prefItem.setPrice(2);

        	eater = new Eater(1, "Heimdall", EaterDiet.NORMAL, new WasserarmShopItem[]{prefItem});
        	eaters.save(eater);
    	}

	/**
	 * Test if storing an {@link Eater} works
	 */
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

		eater = new Eater(2, "Igor", EaterDiet.NORMAL, new WasserarmShopItem[] { prefItem });
		assertTrue(eaters.save(eater), "Eater was not stored");;
	}

	@Test
	void testFindById() {

		final int ID = 1;
		Eater eater;
		WasserarmShopItem prefItem;

		log.debug("entering testFindById");

		prefItem = new WasserarmShopItem();
		prefItem.setName("Melon");
		prefItem.setType(WasserarmShopItemType.FRUIT);
		prefItem.setWater(2);
		prefItem.setPrice(2);

		try {
			eater = eaters.findById(ID);
			assertEquals(ID, eater.getId(), "wrong ID");
			assertEquals("Heimdall", eater.getName(), "Name is wrong");
			assertEquals(EaterDiet.NORMAL, eater.getDiet(), "Diet is wrong");
		} catch (NoSuchEaterException e) {
			fail("Eater was not found");
		}

	}

	@AfterEach
	public void tearDown() {
		log.debug("Cleaning up an EaterRepsoitory test");
		eaters.clear();
    	}
}

