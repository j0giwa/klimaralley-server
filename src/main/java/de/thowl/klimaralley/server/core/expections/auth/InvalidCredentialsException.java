package de.thowl.klimaralley.server.core.expections.auth;

import lombok.experimental.StandardException;

/**
 * Gets thrown when Security relavant data is invalid or incorrect.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@StandardException
public class InvalidCredentialsException extends Exception {

}
