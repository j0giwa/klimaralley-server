package de.thowl.klimaralley.server.storage.repository.wasserarm;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.core.expections.wasserarm.NoSuchEaterException;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EaterRepsoitoryImpl implements EaterRepsoitory {

        private ArrayList<Eater> eaters;

        public EaterRepsoitoryImpl() {
                log.debug("entering EaterRepsoitory:ctor");
                this.eaters = new ArrayList<>();
        }

        @Override
        public boolean save(Eater eater) {
                log.debug("entering store");
                this.eaters.add(eater);
                return true;
        }

        @Override
        public boolean delete(Eater eater) {
                log.debug("entering store");
                this.eaters.remove(eater);
                return true;
        }

        @Override
        public Eater findById(long id) throws NoSuchEaterException {
                log.debug("entering findById");
                for (Eater e: this.eaters) {
                        if (e.getId() == id){
                                return e;
                        }
                }
		throw new NoSuchEaterException("this Eater with does not exist");
        }

        @Override
        public long countAll() {
                log.debug("entering countAll");
                return this.eaters.size();
        }

        @Override
        public boolean clear() {
                log.debug("entering clear");
                this.eaters.clear();
                return true;
        }

}
