package de.thowl.klimaralley.server.core.expections.auth;

import lombok.experimental.StandardException;

/**
 * Gets thrown when trying to access a {@link User} that does not exist.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@StandardException
public class NoSuchUserException extends Exception {

}
