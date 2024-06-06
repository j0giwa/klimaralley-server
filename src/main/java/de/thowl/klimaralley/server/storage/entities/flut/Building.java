package de.thowl.klimaralley.server.storage.entities.flut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
