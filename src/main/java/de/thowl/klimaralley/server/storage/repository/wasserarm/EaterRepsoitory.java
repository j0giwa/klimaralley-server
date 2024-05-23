package de.thowl.klimaralley.server.storage.repository.wasserarm;

import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.core.expections.wasserarm.NoEaterException;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;

@Repository
public interface EaterRepsoitory {

        public boolean store(Eater eater);
        public boolean delete(Eater eater);

        public Eater findById(long id) throws NoEaterException;
}
