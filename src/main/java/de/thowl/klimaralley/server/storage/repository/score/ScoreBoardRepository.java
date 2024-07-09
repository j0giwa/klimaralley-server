package de.thowl.klimaralley.server.storage.repository.score;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.thowl.klimaralley.server.storage.entities.score.Game;
import de.thowl.klimaralley.server.storage.entities.score.ScoreBoardEntry;

public interface ScoreBoardRepository extends CrudRepository<ScoreBoardEntry, Long> {

	public List<ScoreBoardEntry> findAllByGame(Game game);
	
}
