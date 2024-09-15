package de.thowl.klimaralley.server.storage.entities.auth;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A Representation of a User (player).
 *
 * @author Jonas Schwind (Main Author)
 * @author Jeffrey Böttcher (Minor Author)
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = "Users")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NonNull
	private String username;

	@NotNull
	@NonNull
	private String email;

	@NotNull
	@NonNull
	private String password;

	@NotNull
	private int water; // For wasserarm-satt

	@NotNull
	private int waterCoins; // For wasserarm-satt

	@OneToMany(mappedBy = "player") 
    @JsonManagedReference
    private Set<PlayerGame> playerGames; // for recycling


}