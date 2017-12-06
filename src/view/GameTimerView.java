package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import model.GameTimer;
import model.HealthBar;

public class GameTimerView {
	
	//Fields
	
	final private int width = 100;
	final private int height = 20;
	private int maxTime;
	private int timeRemaining;
	final private int drawPosX;
	final private int drawPosY;
	
	private GameTimer gt;
	
	//Constructor
	
	/**
	 * Constructor for GameTimerView. This class is used to represent a GameTimer on-screen
	 * @author - Team 8
	 * @param gt - an instance of GameTimer
	 */
    public GameTimerView(GameTimer gt, int xPos, int yPos){
    	this.gt=gt;
    	drawPosX = xPos-width-100;
    	drawPosY = yPos-height-100;
    	maxTime = gt.getMaxTime();
    	timeRemaining = gt.getTimeRemaining();
    }
    
    // Getters and Setters
    
    /**
     * Gets the GameTimer object associated with this view
     * @author - Team 8
     * @return - the GameTimer object associated with this view
     */
    public GameTimer getGameTimer(){
    	return gt;
    }
    
    // A method for displaying
    
    /**
     * A method to draw the game timer for this game on-screen. It draws a green rectangle in 
     * the lower right corner.
     * @author - Team 8
     * @param g - an instance of Graphics handled internally by Java
     */
    public void drawBar(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(drawPosX, drawPosY, width, height);
        g.setColor(Color.GREEN);
        g.fillRect(drawPosX, drawPosY, (int)((double)timeRemaining/maxTime * width), height);
    	g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString(Integer.toString(timeRemaining), drawPosX+width+15, drawPosY+height/2+5);
    }
    
}
