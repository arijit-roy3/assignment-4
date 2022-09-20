package com.test;

public class Player {
	private int id;
	private String name;
	private int matches;
	private int score;
	private int ducks;
	private int wickets;
	private String type;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDucks() {
		return ducks;
	}
	public void setDucks(int ducks) {
		this.ducks = ducks;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Player(int id, String name, int matches, int score, int ducks, int wickets, String type) {
		super();
		this.id = id;
		this.name = name;
		this.matches = matches;
		this.score = score;
		this.ducks = ducks;
		this.wickets = wickets;
		this.type = type;
	}
	public Player() {
		super();
	}	
	
	

}
