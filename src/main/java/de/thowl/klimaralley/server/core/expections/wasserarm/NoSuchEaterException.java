package de.thowl.klimaralley.server.core.expections.wasserarm;

import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import lombok.experimental.StandardException;

/**
 * Gets thrown when trying to find a {@link Eater} that does not exist.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@StandardException
public class NoSuchEaterException extends Exception {

}
