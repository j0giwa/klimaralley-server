package de.thowl.klimaralley.server.core.services.score;

import java.util.List;

import de.thowl.klimaralley.server.storage.entities.score.Game;
import de.thowl.klimaralley.server.storage.entities.score.ScoreBoardEntry;

public interface ScoreboardService {

	public void addEntry(long userId, int score, Game game);
	public List<ScoreBoardEntry> getAllByGame(Game game);

}
