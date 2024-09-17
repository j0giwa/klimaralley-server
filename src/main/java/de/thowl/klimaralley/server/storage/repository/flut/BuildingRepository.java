package de.thowl.klimaralley.server.storage.repository.flut;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.core.expections.flut.NoSuchBuildingException;
import de.thowl.klimaralley.server.storage.entities.flut.Building;

// import com.thowl.Flut.expections.NoSuchBuildingException;
// import com.thowl.Flut.storage.entities.Building;

/**
 * A local storage for Building Instances
 * 
 */
@Repository
public interface BuildingRepository {
    public Building findbyId(int id) throws NoSuchBuildingException;
    public ArrayList<Building> findBuildings();
}
