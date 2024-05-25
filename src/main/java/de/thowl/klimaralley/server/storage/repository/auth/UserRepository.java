package de.thowl.klimaralley.server.storage.repository.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.auth.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);

	public User findByEmail(String email);

	public User findByApiToken(String apiToken);
}
