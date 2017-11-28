
package model;
import java.awt.*;

public class Player {

	//Fields
	
    private double directionAngle;
    
    private Coordinates coordinates;

	private int playerDiameter;

    // Constructor
    
    /**
     * Constructor for player
     * @author - Team 8
     * @param coordinates - the current coordinates for this player
     */
    public Player(Coordinates coordinates, int imageDiameter) {
        this.coordinates = coordinates;
        playerDiameter = imageDiameter;

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
        double xPos = coordinates.getxPos() + movingVector.getX();
        double yPos = coordinates.getyPos() + movingVector.getY();

        coordinates = new Coordinates(xPos, yPos);
    }
}
