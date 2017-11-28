package model;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy{

	//Fields
	enum EnemyType {INVASIVE, POLLUTION};
	EnemyType enemyType;
	
    private int enemyDiameter;
    private double movingAngle;
    
    private Coordinates coordinates;
    private final MovingVector movingVector;

    //Constructor

    /**
     * A constructor for Enemy
     * @author - Team 8
     * @param coordinates - Current coordinates of the enemy
     * @param movingVector - The direction vector for this Enemy 
     * @param imageDiameter - The diameter of the image painted on the screen by EnemyView
     * @param b - a check on whether or not this is an invasive species instance, true means it is, false means it's a pollution enemy
     */
    public Enemy(Coordinates coordinates, MovingVector movingVector, int imageDiameter, EnemyType type) {
        this.coordinates = coordinates;
        this.movingVector = movingVector;
        enemyType = type;
        enemyDiameter = imageDiameter;
        initMovingAngle();
    }
    
    // Getters and setters
    
    /**
     * Getter for the angle of movement
     * @author - Team 8
     * @return - The angle with respect to positive x that the object is moving
     */
    public double getMovingAngle(){
    	return movingAngle;
    }

    /**
     * Getter for current coordinates
     * @author - Team 8
     * @return - The current coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    /**
     * Getter for the movement vector
     * @author - Team 8
     * @return - The movement vector
     */
    public MovingVector getMovingVector() {
		return movingVector;
	}
    
    /**
     * Getter for the enemy diameter
     * @author - Team 8
     * @return - The enemy diameter
     */
    public int getEnemyDiameter(){
    	return enemyDiameter;

	}
    
    /**
     * Checks the type of the enemy
     * @author - Team 8
     * @return - The boolean value of whether this is an invasive instance or not
     */
    public EnemyType getEnemyType(){
    	return enemyType;
    }
    
    // Movement functions and helpers

    /**
     * Changes the position of the enemy object
     * @author - Team 8
     */
    public void moveByVector() {
        double xPos = coordinates.getxPos() + movingVector.getX();
        double yPos = coordinates.getyPos() + movingVector.getY();
        
        coordinates = new Coordinates(xPos, yPos);
    }
    
    /**
     * Initializes the field movingAngle
     * @author - Team 8
     */
    public void initMovingAngle(){
    	switch(enemyType){
    	case INVASIVE:{
            movingAngle = (double) Math.toDegrees(Math.atan2(movingVector.getY(),
                    movingVector.getX()));

            if(movingAngle < 0)
                movingAngle += 360;
            break;
    	}
    	case POLLUTION:{
    		movingAngle = Math.random()*360;
            break;
    	}
    }
    
}
}