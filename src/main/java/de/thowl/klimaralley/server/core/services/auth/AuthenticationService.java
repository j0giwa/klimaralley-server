package de.thowl.klimaralley.server.core.services.auth;

import de.thowl.klimaralley.server.core.expections.auth.DuplicateUserException;
import de.thowl.klimaralley.server.core.expections.auth.InvalidCredentialsException;
import de.thowl.klimaralley.server.core.expections.auth.NoSuchUserException;
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
	 * <li>at least one special character</li>
	 * </ul>
	 *
	 * @param password Password to validate
	 *
	 * @return {@code true} if the conditions match,
	 *         {@code false} if something does not match
	 */
	public boolean validatePassword(String password);

	/**
	 * Checks if the input password matches a BCrypt hash
	 *
	 * @param bcrypt   The BCrypt hash to check against
	 * @param password The input Password that should be checked
	 *
	 * @return {@code true} if the Password matched, {@code false} if the Password
	 *         did not match
	 */
	public boolean checkPassword(String bcrypt, String password);

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
	 * @throws NoSuchUserException when the given id does not belong to an existing
	 *                           {@link User}.
	 */
	public void updateUser(long id, String firstname, String lastname, String username, String email, String password)
			throws NoSuchUserException;

	/**
	 * Performs a login action and stores an active {@link Session} in the Database.
	 * <p>
	 * The Session expires 30 Minutes after its creation / Validation.
	 * 
	 * @param email    The E-Mail address of the user
	 * @param password The password of the user
	 *
	 * @return permit A38 (JWT token)
	 *
	 * @throws {@link InvalidCredentialsException} when credentials are invalid.
	 */
	public String login(String email, String password) throws InvalidCredentialsException;

}
