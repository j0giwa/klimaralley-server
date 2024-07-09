package de.thowl.klimaralley.server.core.services.score;

import de.thowl.klimaralley.server.storage.entities.score.Game;

public interface ScoreboardService {

	public void addEntry(long userId, int score, Game game);

}
