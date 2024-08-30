// FlutService.java
package de.thowl.klimaralley.server.core.services.flut;

import de.thowl.klimaralley.server.storage.entities.flut.FlutMaxLevel;
import de.thowl.klimaralley.server.storage.entities.flut.Building;
import java.util.ArrayList;

public interface FlutService {
    ArrayList<Building> getBuildings();
    Building getBuildingbyId(Building id);
    Integer getFlutMaxLevelByUserId(Long userId); // NEU: Methode zum Abrufen des FlutMaxLevel basierend auf userId
    FlutMaxLevel saveFlutMaxLevel(Long userId, Integer value); // NEU: Methode zum Speichern des FlutMaxLevel basierend auf userId
}
