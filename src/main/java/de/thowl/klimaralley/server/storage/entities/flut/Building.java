package de.thowl.klimaralley.server.storage.entities.flut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*****
 * A class to save Buildings for flut game
 * 
 * @author Cedric Bourgeois
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    int id;
    int hp;
    int maxWaterLevel;
    int cost;
    int level;
    String name;
    
}
