// FlutService.java
package de.thowl.klimaralley.server.core.services.flut;

import de.thowl.klimaralley.server.storage.entities.flut.FlutMaxLevel;
import de.thowl.klimaralley.server.storage.entities.flut.Building;
import java.util.ArrayList;

/**
 * Enthält Funktionen zum speichern und abrufen von Objekten für das flut spiel
 *
 * @author Cedric Bourgeois
 * @version 1.0.0
 */
public interface FlutService {
    ArrayList<Building> getBuildings();  // Methode zum abrufen aller Buildings
    Building getBuildingbyId(Building id); // Methode zum abrufen eines Buildings
    Integer getFlutMaxLevelByUserId(Long userId); // Methode zum Abrufen des FlutMaxLevel basierend auf userId
    FlutMaxLevel saveFlutMaxLevel(Long userId, Integer value); // Methode zum Speichern des FlutMaxLevel basierend auf userId
}
