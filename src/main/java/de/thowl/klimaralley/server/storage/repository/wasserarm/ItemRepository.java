package de.thowl.klimaralley.server.storage.repository.wasserarm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;

@Repository
public interface ItemRepository extends CrudRepository<WasserarmShopItem, Long> {
	
	public List<WasserarmShopItem> findAll();
}
