package model;
public class Coordinates {

	//Fields
	
    private double xPos;
    private double yPos;

    //Constructor
    
    /**
     * Constructor for Coordinates
     * @author - Team 8
     * @param x
     * @param y
     */
    public Coordinates(double x, double y) {
        this.xPos = x;
        this.yPos = y;
    }

    // Getters and Setters
    
    /**
     * @author - Team 8
     * @return - The current value for x in the Coordinates
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * @author - Team 8
     * @return - The current value for y in the Coordinates
     */
    public double getyPos() {
        return yPos;
    }

}
