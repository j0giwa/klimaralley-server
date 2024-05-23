package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Item;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;

public interface WasserarmService {

	public List<Item> getAll();
        public Eater getEater();
}
