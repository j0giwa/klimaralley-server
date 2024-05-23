package de.thowl.klimaralley.server.core.services.wasserarm;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Item;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WasserarmServiceImpl implements WasserarmService {

	private String getRandomName(){

		String[] names;
		String name;

		log.debug("entering generateName");

		return name;
	}

	public List<Item> getAll() {
		// TODO: Implement
		return null;	
	}

        public Eater generateEater() {

		Eater eater;

		log.debug("entering GenerateEater");
		
		eater = new Eater();
		eater.setName(getRandomName());

		return eater;

	}

        public Eater getEater() {
		// TODO: Implement
		return null;	
	}


}
