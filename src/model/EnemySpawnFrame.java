package model;

public class EnemySpawnFrame {

	// Fields

	Coordinates startCoordinates;
	Coordinates endCoordinates;

	// Constructor

	/**
	 * Constructor for the enemy spawn frame
	 * 
	 * @author - Team 8
	 * @param startCoordinates
	 *            - The start coordinates of the frame
	 * @param endCoordinates
	 *            - The end coordinates of the frame
	 */
	public EnemySpawnFrame(Coordinates startCoordinates, Coordinates endCoordinates) {
		this.startCoordinates = startCoordinates;
		this.endCoordinates = endCoordinates;
	}

	// Getters and Setters

	/**
	 * Getter for the x position of the start coordinates
	 * 
	 * @author - Team 8
	 * @return - The x position of the start coordinates
	 */
	public double getXBeginning() {
		return startCoordinates.getxPos();
	}

	/**
	 * Getter for the x position of the end coordinate
	 * 
	 * @author - Team 8
	 * @return - The x position of the end coordinate
	 */
	public double getXEnding() {
		return endCoordinates.getxPos();
	}

	/**
	 * Getter for the y position of the start coordinate
	 * 
	 * @author - Team 8
	 * @return - The y position of the end coordinate
	 */
	public double getYBeginning() {
		return startCoordinates.getyPos();
	}
	
	/**
	 * Getter for the y position of the end coordinate
	 * 
	 * @author - Team 8
	 * @return - The y position of the end coordinate
	 */
	public double getYEnding() {
		return endCoordinates.getyPos();
	}
}