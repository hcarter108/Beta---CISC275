package model;

import javax.swing.*;
import java.awt.*;

public class HealthBar {
	
	//Fields
	
	int currentPoints;
	int maxPoints;

	//Constructor
	
	/**
	 * Constructor for HealhBar
	 * @author - Team 8
	 * @param healthPoints - the maximum health points for this game
	 */
	public HealthBar(int healthPoints) {
		currentPoints = maxPoints = healthPoints;
	}
	
	//Getters and setters

	/**
	 * Sets the remaining health points
	 * @author - Team 8
	 * @param points - the remaining health points
	 */
	public void setCurrentPoints(int points) {
		currentPoints = points;
	}

	/**
	 * Gets the remaining health points
	 * @author - Team 8
	 * @return - the remaining health points
	 */
	public int getCurrentPoints() {
		if (currentPoints < 0)
			return 0;
		else
			return currentPoints;
	}

	/**
	 * Gets the max points for this game
	 * @author - Team 8
	 * @return the max health points for this game
	 */
	public int getMaxPoints() {
		return maxPoints;
	}

	/**
	 * Sets the max health points for this game
	 * @author - Team 8
	 * @param maxPoints - the max health points for this game
	 */
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

}
