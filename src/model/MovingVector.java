package model;
public class MovingVector {

	//Fields
	
    private double x;
    private double y;

    //Constructor
    
    /**
     * Constructor for MovingVector
     * @author - Team 8
     * @param x - the change in x
     * @param y - the change in y
     */
    public MovingVector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters and setters

    /**
     * Gets the change in x for this vector
     * @author - Team 8
     * @return - the change in x for this vector
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the change in y for this vector
     * @author - Team 8
     * @return - the change in y for this vector
     */
    public double getY() {
        return y;
    }

}
