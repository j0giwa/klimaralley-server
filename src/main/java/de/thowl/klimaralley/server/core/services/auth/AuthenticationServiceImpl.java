package de.thowl.klimaralley.server.core.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.core.expections.auth.DuplicateUserException;
import de.thowl.klimaralley.server.core.expections.auth.InvalidCredentialsException;
import de.thowl.klimaralley.server.core.expections.auth.NoSuchUserException;
import de.thowl.klimaralley.server.core.utils.auth.JWTtokenizer;
import de.thowl.klimaralley.server.storage.repository.auth.UserRepository;
import de.thowl.klimaralley.server.storage.entities.auth.User;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Implementaion of the {@link AuthenticationService} interface
 * {@inheritDoc}
 */
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final int BCRYPT_COST = 15;
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository users;
	
	public AuthenticationServiceImpl() {
		this.encoder = new BCryptPasswordEncoder(BCRYPT_COST);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validateEmail(String email) {

		String regex;
		boolean result;

		log.debug("entering validateEmail");

		if (null == email)
			return false;

		// Source https://ihateregex.io/expr/email/
		regex = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";

		result = email.matches(regex);

		log.debug("validateEmail(email: {}) returned: {}", email, result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validatePassword(String password) {

		String regex;
		boolean result;

		log.debug("entering validatePassword");

		if (null == password)
			return false;

		// Source = "https://ihateregex.io/expr/password/"
		regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";

		result = password.matches(regex);

		log.debug("validatePassword(password: {}) returned: {}", password, result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws DuplicateUserException
	 */
	@Override
	public void register(String firstname, String lastname, String username,
			String email, String password) throws DuplicateUserException {

		User usr;

		log.debug("entering register");

		if (this.users.findByEmail(email) != null)
			throw new DuplicateUserException("A User with this Email already exists");

		if (this.users.findByUsername(username) != null)
			throw new DuplicateUserException("A User with this Username already exists");

		usr = new User(firstname, lastname, username, email, encoder.encode(password));

		log.info("registering user {} with {}", username, email);
		this.users.save(usr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(long id, String firstname, String lastname, String username,
			String email, String password) throws NoSuchUserException {

		User user;

		log.debug("entering updateUser");

		user = users.findById(id).orElseThrow(
				() -> new NoSuchUserException("User not found"));

		// update the user object in database
		user.setUsername(username);
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(encoder.encode(password));

		this.users.save(user);
		log.info("udated userdata of user id: {}", id);
	}

	/**
	 * Checks if the input password matches the {@link User}s password stored in the
	 * Database.
	 *
	 * @param user     The {@link User} whose Password should be checked against
	 * @param password The input Password that should be checked
	 *
	 * @return {@code true} if the Password matched, {@code false} if the Password
	 *         did not match
	 */
	private boolean checkPassword(User user, String password) {

		String bHash;
		boolean result;

		log.debug("entering checkPassword");

		if (null == password || password.isBlank())
			return false;

		bHash = user.getPassword();
		log.debug("Comparing Form-password with BCrypt-hash");
		result = encoder.matches(password, bHash);

		log.debug("checkPassword(user: {}, password: {}) returned: {}", user.toString(), password, result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String login(String email, String password) throws InvalidCredentialsException {

		User user;

		log.debug("entering login");

		if (email == null || password == null) {
			log.error("One or more params were left empty");
			throw new InvalidCredentialsException("Params cannot be null");
		}

		user = this.users.findByEmail(email);

		if (user == null) {
			log.error("E-Mail '{}' does not exist", email);
			throw new InvalidCredentialsException("User not found");
		}

		log.info("login attempt for user with email: {}", email);
		if (checkPassword(user, password)) {
			log.info("Password matched, creating user session");
			return JWTtokenizer.generateToken(user);
		}

		throw new InvalidCredentialsException("Wrong Password");
	}

}
