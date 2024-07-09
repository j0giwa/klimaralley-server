package de.thowl.klimaralley.server.storage.entities.score;

import de.thowl.klimaralley.server.storage.entities.auth.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A Representation of a User (player).
 *
 * @author Jonas Schwind
 * @version 1.0.0
 */
@Data
@Entity
@Table(name = "Scoreboard")
@NoArgsConstructor
@RequiredArgsConstructor
public class ScoreBoardEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NonNull
	private User user;

	@NotNull
	@NonNull
	private int score;

	@NotNull
	@NonNull
	private Game game;
}
