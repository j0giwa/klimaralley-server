package de.thowl.klimaralley.server.core.expections.auth;

import lombok.experimental.StandardException;

/**
 * Gets thrown when trying to create a {@link User} that aleady exist.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@StandardException
public class DuplicateUserException extends Exception {

}
