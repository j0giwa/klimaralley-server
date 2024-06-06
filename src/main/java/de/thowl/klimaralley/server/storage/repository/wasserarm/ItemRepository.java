package de.thowl.klimaralley.server.storage.repository.wasserarm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;

/**
 * Repository for {@link WasserarmShopItem}'s.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@Repository
public interface ItemRepository extends CrudRepository<WasserarmShopItem, Long> {

	/**
	 * Gets all {@link WasserarmShopItem}s
	 * 
	 * @return all {@link WasserarmShopItem}s
	 */
	public List<WasserarmShopItem> findAll();
}
