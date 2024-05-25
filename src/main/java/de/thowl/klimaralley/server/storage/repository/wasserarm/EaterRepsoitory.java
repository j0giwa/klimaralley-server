package de.thowl.klimaralley.server.storage.repository.wasserarm;

import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.core.expections.wasserarm.NoSuchEaterException;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;

/**
 * A local storage for {@link Eater} instances.
 * 
 * {@link Eater} instances are single use items (for one session only), 
 * there is no reason for long term storage.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@Repository
public interface EaterRepsoitory {

        /**
         * Save a instance of {@link Eater}
         * 
         * @param eater eater to store.
         * @return {@code true} if Eater was stored.
         */
        public boolean save(Eater eater);

        /**
         * Remove a instance of {@link Eater}
         * 
         * @param eater eater to remove.
         * @return {@code true} if Eater was removed.
         */
        public boolean delete(Eater eater);

        public Eater findById(long id) throws NoSuchEaterException;

        /**
         * Gets the number of stored eaters
         * 
         * @return amount of stored eaters
         */
        public long countAll();

        /**
         * 
         * @return {@code true} if everthying was deleted.
         */
        public boolean clear();
}
