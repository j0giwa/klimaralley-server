package de.thowl.klimaralley.server.core.expections.wasserarm;

import lombok.experimental.StandardException;

/**
 * Gets thrown when trying to find a {@link Eater} that does not exist.
 */
@StandardException
public class NoSuchEaterException extends Exception {

}
