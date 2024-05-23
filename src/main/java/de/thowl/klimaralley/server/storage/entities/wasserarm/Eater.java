package de.thowl.klimaralley.server.storage.entities.wasserarm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eater {
        private long id;

	private String name;

	private EaterDiet diet;

	private Item[] preferernces;

}
