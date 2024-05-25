package de.thowl.klimaralley.server.storage.repository.auth;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.auth.AccessToken;
import de.thowl.klimaralley.server.storage.entities.auth.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

	/**
	 * Finds a {@link Session} by its ID.
	 *
	 * @param id The ID of the {@link Session} to find.
	 * @return The {@link Session} with the specified ID, or {@code null} if not
	 *         found.
	 */
	public Session findById(long id);

	/**
	 * Finds a {@link Session} by its {@link AccessToken}.
	 *
	 * @param authToken The {@link AccessToken} of the session to find.
	 * @return The {@link Session} with the specified authentication token, or
	 *         {@code null} if not found.
	 */
	public Session findByAuthToken(String authToken);

	/**
	 * Finds a {@link Session} by the {@link User}'s ID it belongs to.
	 *
	 * @param userId The ID of the {@link User} to whom the {@link Session} belongs.
	 * @return The {@link Session} associated with the specified user ID, or
	 *         {@code null} if not found.
	 */
	public Session findByUserId(long userId);

	/**
	 * Finds {@link Session} whose expiration time is before the given current time.
	 *
	 * @param currentTime The current time used for comparison.
	 * @return A list of {@link Session} whose expiration time is before the given
	 *         current
	 *         time, or {@code null} if not found.
	 */
	List<Session> findByExpiresAtBefore(Date currentTime);
}
