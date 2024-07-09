package de.thowl.klimaralley.server.core.services.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.entities.score.Game;
import de.thowl.klimaralley.server.storage.entities.score.ScoreBoardEntry;
import de.thowl.klimaralley.server.storage.repository.auth.UserRepository;
import de.thowl.klimaralley.server.storage.repository.score.ScoreBoardRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementaion of the {@link ScoreboardService} interface
 * 
 * @author Jonas Schwind
 * @version 1.2.0
 */
@Slf4j
@Service
public class ScoreboardServiceImpl implements ScoreboardService {

	@Autowired
	private ScoreBoardRepository scoreboard;

	@Autowired
	private UserRepository users;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addEntry(long userId, int score, Game game) {

		User user;
		ScoreBoardEntry entry;

		log.debug("entering ScoreboardService.addEntry");

		user = this.users.findById(userId).get();
		entry = new ScoreBoardEntry(user, score, game);

		this.scoreboard.save(entry);
	}

}
