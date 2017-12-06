package model;

import java.awt.Color;
import java.awt.Graphics;

import model.MoveableObject.MoveableType;
import view.ScreenPanel.Screens;

public class Enemy extends MoveableObject{

	//Fields
	MoveableType enemyType;
	
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
     * @param s - The screen that the enemy is on
     * @param type - Denotes the type of enemy, pollution or invasive
     */
    public Enemy(Coordinates coordinates, MovingVector movingVector, Screens s, MoveableType type) {
        this.coordinates = coordinates;
        this.movingVector = movingVector;
        enemyType = type;
        enemyDiameter = calculateMinDiameter(getMoveableDimensions(s, type));
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
     * Getter for enemy type, from enum MoveableType
     * @author - Team 8
     * @return this enemy's type
     */
    public MoveableType getEnemyType(){
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