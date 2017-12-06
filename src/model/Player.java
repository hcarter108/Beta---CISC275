
package model;
import java.awt.*;
import java.util.ArrayList;

import view.ScreenPanel.Screens;

public class Player extends MoveableObject{

	//Fields
	
    private double directionAngle;
    
    private Coordinates coordinates;

	private int playerDiameter;
	
	private double scaleFactor=1;

    // Constructor
    
    /**
     * Constructor for player
     * @author - Team 8
     * @param coordinates - the current coordinates for this player
     * @param s - the screen that the player is in
     */
    public Player(Coordinates coordinates, Screens s) {
        this.coordinates = coordinates;
        playerDiameter = calculateMinDiameter(getMoveableDimensions(s, MoveableType.PLAYER));

    }
    
    // Getters and setters

    /**
     * Gets the direction of the player, an angle from the horizontal. This is used to rotate the image
     * @author - Team 8
     * @return - the angle for the player's direction with respect to the horizontal
     */
    public double getDirectionAngle() {
		return directionAngle;
	}

    /**
     * Sets the scale factor associated with the game that contains this player
     * @author - Team 8
     * @param factor- the scale factor associated with a game
     */
    public void setScaleFactor(double factor){
    	scaleFactor = factor;
    }
    /**
     * Sets the direction of the player, an angle from the horizontal
     * @author - Team 8
     * @param directionAngle - the angle for the player's direction with respect to the horizontal
     */
	public void setDirectionAngle(double directionAngle) {
		this.directionAngle = directionAngle;
	}

	/**
	 * Gets the coordinates of this player
	 * @author - Team 8
	 * @return - the coordinates of this player
	 */
	public Coordinates getCoordinates() {
        return coordinates;
    }
	
	/**
	 * Gets the diameter of the player, in terms of its image width. Important for model logic.
	 * @author - Team 8
	 * @return - the diameter of the player's image
	 */
	
	public int getPlayerDiameter(){
		return playerDiameter;
	}
	
	// Movement functions

	/**
	 * Moves the player using its MovingVector, updates position
	 * @author - Team 8
	 * @param movingVector - the displacement vector of this player
	 */
    public void moveByVector(MovingVector movingVector) {
        double xPos = coordinates.getxPos() + scaleFactor*movingVector.getX();
        double yPos = coordinates.getyPos() + scaleFactor*movingVector.getY();

        coordinates = new Coordinates(xPos, yPos);
    }
}
