package de.thowl.klimaralley.server.storage.wasserarm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

	/**
	 * Test if storing an {@link Eater} works
	 * 
	 * TODO: this test test 2 things, fix
	 */
	@Test
	void testStore() {
		Eater eater, repositoryEater;
		WasserarmShopItem prefItem;

		log.debug("entering testStore");

		final int ID = 0;

		prefItem = new WasserarmShopItem();
		prefItem.setName("Apple");
		prefItem.setType(WasserarmShopItemType.FRUIT);
		prefItem.setWater(2);
		prefItem.setPrice(2);

		// Stage 1 - store
		eater = new Eater(ID, "Heimdall", EaterDiet.NORMAL, new WasserarmShopItem[]{prefItem});
		assertTrue(eaters.save(eater), "Eater was not stored");

		// Stage 2 - assert integritty
		try {
			repositoryEater = this.eaters.findById(ID);
			assertTrue(repositoryEater.getId() == ID, "wrong ID");
			assertTrue(repositoryEater.getName().equals("Heimdall"), "Name is wrong");
			assertEquals(EaterDiet.NORMAL ,repositoryEater.getDiet(), "Diet is wrong");
		} catch (NoSuchEaterException e) {
			fail("Eater was not stored");
		}
	}

}

