package model;

public class GameTimer {

	//Fields
	
	private int timeRemaining;
	private int maxTime;

	//Constructor
	
	/**
	 * Constructor for GameTimer
	 * @author - Team 8
	 * @param totalTime - the total time that it takes to win the game
	 */
	public GameTimer(int totalTime) {
		timeRemaining = maxTime = totalTime;
	}
	
	// Getters and Setters

	/**
	 * Getter for the maximum time, or the time that it takes to win the game
	 * @author - Team 8
	 * @return - the time that it takes to win the game
	 */
	public int getMaxTime() {
		return maxTime;
	}

/**
 * Setter for the time it takes to win the game
 * @author - Team 8
 * @param maxTime - the time it takes to win the game
 */
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * Gets the time remaining until the game is won
	 * @author - Team 8
	 * @return - the time remaining until the game is won
	 */
	public int getTimeRemaining() {
		if (timeRemaining < 0)
			return 0;
		else
			return timeRemaining;
	}

	/**
	 * Sets the time remaining until the game is won
	 * @author - Team 8
	 * @param timeRemaining - the time remaining until the game is won
	 */
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

}
