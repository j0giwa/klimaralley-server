package de.thowl.klimaralley.server.core.expections.auth;

import lombok.experimental.StandardException;

/**
 * Gets thrown when trying to create a {@link User} that aleady exist.
 */
@StandardException
public class DuplicateUserException extends Exception {

}
