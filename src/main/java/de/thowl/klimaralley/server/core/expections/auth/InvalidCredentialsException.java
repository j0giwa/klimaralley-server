package de.thowl.klimaralley.server.core.expections.auth;

import lombok.experimental.StandardException;

/**
 * Gets thrown when Security relavant data is invalid, or incorrect.
 */
@StandardException
public class InvalidCredentialsException extends Exception {

}
