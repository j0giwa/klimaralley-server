package de.thowl.klimaralley.server.storage.repository.wasserarm;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	public List<Item> findAll();
}
