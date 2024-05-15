package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Item;

public interface WasserarmService {

	public List<Item> getAll();
}
