package de.thowl.klimaralley.server.core.services.auth;

import de.thowl.klimaralley.server.core.expections.auth.DuplicateUserException;
import de.thowl.klimaralley.server.core.expections.auth.InvalidCredentialsException;
import de.thowl.klimaralley.server.core.expections.auth.NullUserException;
import de.thowl.klimaralley.server.storage.entities.auth.AccessToken;
import de.thowl.klimaralley.server.storage.entities.auth.Session;
import de.thowl.klimaralley.server.storage.entities.auth.User;

/**
 * Provides User Authentifaciton/Management funtionalitys
 */
public interface AuthenticationService {

	/**
	 * Checks if the input matches the format of an E-Mail address.
	 *
	 * @param user     The {@link User} whose Password should be checked against
	 * @param password The input Password that should be checked
	 *
	 * @return {@code true} if the Password matched, {@code false} if the Password
	 *         did not match
	 */
	public boolean validateEmail(String email);

	/**
	 * Validates that the chosen password is somewhat secure.
	 * This is to protect the users from their own stupidity
	 *
	 * <p>
	 *
	 * Password requirements:
	 * <ul>
	 * <li>Minimum eight characters</li>
	 * <li>at least one upper case English letter</li>
	 * <li>at least one lower case English letter</li>
	 * <li>at least one number</li>
	 * <li>at least ne special character</li>
	 * </ul>
	 *
	 * @param password Password to validate
	 *
	 * @return {@code true} if the conditions match,
	 *         {@code false} if something does not match
	 */
	public boolean validatePassword(String password);

	/**
	 * Validates the Users Session
	 * <p>
	 * Checks if the given {@link AccessToken} belongs to a session with this
	 * {@link AccessToken}.
	 *
	 * @param token    The {@link AccessToken} to check.
	 * @param username The name of the {@link User} to check.
	 *
	 * @return {@code true} if the conditions match,
	 *         {@code false} if something does not match
	 */
	public boolean validateSession(AccessToken token, String username);

	/**
	 * Resets the session expiry time.
	 * 
	 * A valid Session expires after 30 Minutes.
	 * Running this resets the timer.
	 * 
	 * @param session The session to refresh
	 */
	public void refreshSession(AccessToken token);

	/**
	 * Gets an actice {@link User} by his {@link AccessToken}
	 * 
	 * @param token The {@link AccessToken}.
	 * @return The {@link User} having the {@link AccessToken}.
	 */
	public User getUserbySession(AccessToken token);

	/**
	 * Registers a new user
	 * 
	 * @param firstname The First Name of the user
	 * @param lastname  The Last Name of the user
	 * @param username  The username of the user
	 * @param email     The E-Mail address of the user
	 * @param password  The password of the user
	 */
	public void register(String firstname, String lastname, String username, String email, String password)
			throws DuplicateUserException;

	/**
	 * Update the userinformation {@link User} in the Database.
	 * 
	 * @param id        The id of the {@link User} to edit.
	 * @param firstname The new first name of the {@link User}.
	 * @param lastname  The new last name of the {@link User}.
	 * @param username  The new username of the {@link User}.
	 * @param email     The new E-Mail address of the {@link User}.
	 * @param password  The new password of the {@link User}.
	 * 
	 * @throws NullUserException when the given id does not belong to an existing
	 *                           {@link User}.
	 */
	public void updateUser(long id, String firstname, String lastname, String username, String email, String password)
			throws NullUserException;

	/**
	 * Performs a login action and stores an active {@link Session} in the Database.
	 * <p>
	 * The Session expires 30 Minutes after its creation / Validation.
	 * 
	 * @param email    The E-Mail address of the user
	 * @param password The password of the user
	 *
	 * @return permit A38
	 *
	 * @throws {@link InvalidCredentialsException} when credentials are invalid.
	 */
	public AccessToken login(String email, String password) throws InvalidCredentialsException;

	/**
	 * Removes an active {@link Session} from the Database.
	 * 
	 * @param token The token of the session to remove.
	 */
	public void logout(String token);

}
