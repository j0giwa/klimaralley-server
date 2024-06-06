package de.thowl.klimaralley.server.storage.repository.flut;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.core.expections.flut.NoSuchBuildingException;
import de.thowl.klimaralley.server.storage.entities.flut.Building;
// import com.thowl.Flut.expections.NoSuchBuildingException;
// import com.thowl.Flut.storage.entities.Building;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BuildingRepsositoryImpl implements BuildingRepository {

    private ArrayList<Building> buildings;

    public BuildingRepsositoryImpl(){
        //log.debug("entering Building Repository")
        this.buildings = new ArrayList<>();
    }

    public Building findbyId(int id) throws NoSuchBuildingException{
        //log.debug("entering findById");
                for (Building b: this.buildings) {
                        if (b.getId() == id){
                                return b;
                        }
                }
		throw new NoSuchBuildingException("this Building with this id does not exist");
    }

    public ArrayList<Building> findBuildings(){
        return buildings;
    }
}
