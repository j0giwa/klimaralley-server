package de.thowl.klimaralley.server.core.services.recycling;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.recycling.RecyclingGame;

@Service
public class RecyclingGameService {

	private static List<RecyclingGame> games = new ArrayList<>();

	private static int gamesCount = 0;
	private static int gamePoints = 2;

	static {
		games.add(new RecyclingGame(++gamesCount, "Admin", "Müll Sortieren Spiel ghaga",
				false,gamePoints,false ));
		games.add(new RecyclingGame(++gamesCount, "Admin", "Multiple Choice Fragen Spiel",
				false, gamePoints, false));
		games.add(new RecyclingGame(++gamesCount, "Admin", "Komponenten trennen Spiel",
				false, gamePoints, false));
	}

	public List<RecyclingGame> findByUsername(String username) {
		Predicate<? super RecyclingGame> predicate = game -> game.getUsername().equalsIgnoreCase(username);
		return games.stream().filter(predicate).toList();
	}

	public RecyclingGame addGame(String username, String description, boolean done,int points, boolean success ) {
		RecyclingGame game = new RecyclingGame(++gamesCount, username, description, done, points , success);
		games.add(game);
		return game;
	}

	public void deleteById(int id) {
		Predicate<? super RecyclingGame> predicate = game -> game.getId() == id;
		games.removeIf(predicate);
	}

	public RecyclingGame findById(int id) {
		Predicate<? super RecyclingGame> predicate = game -> game.getId() == id;
		RecyclingGame game = games.stream().filter(predicate).findFirst().get();
		return game;
	}

	public void updateGame(RecyclingGame game) {
		deleteById(game.getId());
		games.add(game);
	}
}