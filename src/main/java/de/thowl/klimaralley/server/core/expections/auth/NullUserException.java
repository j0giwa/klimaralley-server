package de.thowl.klimaralley.server.core.expections.auth;

import lombok.experimental.StandardException;

/**
 * Gets thrown when trying to access a {@link User} that does not exist.
 */
@StandardException
public class NullUserException extends Exception {

}
