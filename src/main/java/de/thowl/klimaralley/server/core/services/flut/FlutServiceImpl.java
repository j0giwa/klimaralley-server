package de.thowl.klimaralley.server.core.services.flut;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.flut.Building;
import de.thowl.klimaralley.server.storage.repository.flut.BuildingRepository;

// import com.thowl.Flut.storage.entities.Building;
// import com.thowl.Flut.storage.entities.repository.BuildingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlutServiceImpl implements FlutService {

    @Autowired
    private BuildingRepository buildings;

    @Override
    public ArrayList<Building> getBuildings(){
        log.debug("entering getBuildings");
        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings = this.buildings.findBuildings();
        
    	return buildings;
    }

    @Override
    public Building getBuildingbyId(Building id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBuildingbyId'");
    }
}
