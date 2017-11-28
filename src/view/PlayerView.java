package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import model.Coordinates;
import model.Player;

public class PlayerView {
	
	//Fields
	
    private double movementAngle;
    
	private Image playerGraphic;
	private Player p;
    private Coordinates coordinates;
    private int playerDiameter;

    //Constructor
    
    /**
     * Paints an instance of Player on screen.
     * @author - Team 8
     * @param p- the Player instance to be painted
     * @param playerGraphic- the image associated with the Player to be painted
     */
    public PlayerView(Player p, Image playerGraphic){
    	this.playerGraphic = playerGraphic;
    	coordinates = p.getCoordinates();
    	movementAngle = p.getDirectionAngle()+90;
    	playerDiameter = p.getPlayerDiameter();
    }
    
    // A method for displaying
	
    /**
     * Handles drawing the Player on the screen, takes a position and rotates the image in accordance
     * with the direction it is moving.
     * @author - Team 8
     * @param g- an instance of Graphics handled by Java internally
     */
    public void draw(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(coordinates.getxPos() - playerDiameter/2,
        		coordinates.getyPos() - playerDiameter/2);
        at.rotate(Math.toRadians(movementAngle), playerDiameter/2, playerDiameter/2);
g2.drawImage(playerGraphic, at, null);
        
        // method below did not rotate, just in case we want the old way
//    	g.drawImage(playerGraphic, (int)coordinates.getxPos() - 5, (int)coordinates.getyPos()- 5, null);
    }

}
