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

    @Override
    public ArrayList<Building> getBuildings() {
        log.debug("entering getBuildings");
        return new ArrayList<>(this.buildings.findBuildings());
    }

    @Override
    public Building getBuildingbyId(Building id) {
        throw new UnsupportedOperationException("Unimplemented method 'getBuildingbyId'");
    }

    @Override
    public Integer getFlutMaxLevelByUserId(Long userId) {
        Optional<FlutMaxLevel> flutLevel = flutRepository.findByUserId(userId);
        return flutLevel.map(FlutMaxLevel::getValue).orElse(null);
    }

    @Override
    public FlutMaxLevel saveFlutMaxLevel(Long userId, Integer value) {
        FlutMaxLevel flutLevel = flutRepository.findByUserId(userId).orElse(new FlutMaxLevel());
        flutLevel.setUserId(userId); // Annahme: userId wird in der FlutMaxLevel-Entität gespeichert
        flutLevel.setValue(value);
        return flutRepository.save(flutLevel);
    }
}
