package de.thowl.klimaralley.server.core.services.flut;

import java.util.ArrayList;

import de.thowl.klimaralley.server.storage.entities.flut.Building;

// import com.thowl.Flut.storage.entities.Building;
// import com.thowl.Flut.storage.entities.Map;

public interface FlutService {
    public Building getBuildingbyId(Building id); //Wenn das Objekt aufgelevelt wird dann einfach nächste Id übergebens
    public ArrayList<Building> getBuildings();

}
