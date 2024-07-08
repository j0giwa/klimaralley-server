package de.thowl.klimaralley.server.storage.entities.recycling;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RecyclingGame {

    public RecyclingGame() {
		
	}
	
	public RecyclingGame(int id, String username, String description, boolean done, int points, boolean success) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.done = done;
		this.points = points;
		this.success = success;
	}
	
    @Id
	@GeneratedValue
	private int id;

	private String username;
	private String description;
	private boolean done;
	private int points;
	private boolean success;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}



	@Override
	public String toString() {
		return "Game [id=" + id + ", username=" + username + ", description=" + description + ", done=" + done + "]";
	}
    
}
