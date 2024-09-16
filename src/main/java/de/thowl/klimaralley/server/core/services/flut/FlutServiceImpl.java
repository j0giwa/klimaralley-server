// FlutServiceImpl.java
package de.thowl.klimaralley.server.core.services.flut;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.thowl.klimaralley.server.storage.entities.flut.FlutMaxLevel;
import de.thowl.klimaralley.server.storage.entities.flut.Building;
import de.thowl.klimaralley.server.storage.repository.flut.BuildingRepository;
import de.thowl.klimaralley.server.storage.repository.flut.FlutRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlutServiceImpl implements FlutService {

    @Autowired
    private FlutRepository flutRepository;

    @Autowired
    private BuildingRepository buildings;

    /**
     * Retrieves a list of all buildings available in the database.
     * 
     * @return an ArrayList containing all the Building entities found.
     */
    @Override
    public ArrayList<Building> getBuildings() {
        log.debug("entering getBuildings");
        return new ArrayList<>(this.buildings.findBuildings());
    }

    /**
     * Placeholder method for retrieving a building by its ID.
     * Currently unimplemented and will throw an exception when called.
     * 
     * @param id the building's ID
     * @return nothing, as the method is not yet implemented
     * @throws UnsupportedOperationException indicating the method is not implemented
     */
    @Override
    public Building getBuildingbyId(Building id) {
        throw new UnsupportedOperationException("Unimplemented method 'getBuildingbyId'");
    }

    /**
     * Retrieves the maximum flood level for a specific user based on their user ID.
     * 
     * @param userId the ID of the user whose flood max level is being retrieved
     * @return the flood max level for the user, or null if no value is found
     */
    @Override
    public Integer getFlutMaxLevelByUserId(Long userId) {
        Optional<FlutMaxLevel> flutLevel = flutRepository.findByUserId(userId);
        return flutLevel.map(FlutMaxLevel::getValue).orElse(null);
    }

    /**
     * Saves the maximum flood level for a user. If a record already exists for the
     * user, it will be updated; otherwise, a new record will be created.
     * 
     * @param userId the ID of the user
     * @param value the flood max level value to be saved
     * @return the saved FlutMaxLevel entity
     */
    @Override
    public FlutMaxLevel saveFlutMaxLevel(Long userId, Integer value) {
        FlutMaxLevel flutLevel = flutRepository.findByUserId(userId).orElse(new FlutMaxLevel());
        flutLevel.setUserId(userId); 
        flutLevel.setValue(value);
        return flutRepository.save(flutLevel);
    }
}