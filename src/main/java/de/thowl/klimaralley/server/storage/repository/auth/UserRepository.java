package de.thowl.klimaralley.server.storage.repository.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.auth.User;

/**
 * Repository for {@link User}'s.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Retrieves a {@link User} by their username.
	 *
	 * @param username the {@link User}'s username to search for.
	 * @return {@link User} with the given usrname or {@code null} if there are none.
	 */
	public User findByUsername(String username);

	/**
	 * Retrieves a {@link User} by their email.
	 *
	 * @param email the {@link User}'s mail to search for.
	 * @return the {@link User} with the given email or {@code null} if there are none.
	 */
	public User findByEmail(String email);
}
