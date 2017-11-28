package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import model.HealthBar;

public class HealthBarView {
	
	//Fields
	
    int width = 180;
	int height = 20;
    int currentPoints;
    int maxPoints;
    
    private HealthBar hb;
    
    //Constructor
    
    /**
     * Constructor for HealthBarView
     * @author - Team 8
     * @param hb - the HealthBar object associated with this view
     */
    public HealthBarView(HealthBar hb){
    	this.hb=hb;
    	currentPoints = hb.getCurrentPoints();
    	maxPoints = hb.getMaxPoints();
    }
    
    //Getters and setters
    
    /**
     * Getter for the HealthBar object associated with this view
     * @author - Team 8
     * @return - the HealthBar object associated with this view
     */
    public HealthBar getHealthBar(){
    	return hb;
    }
    
    // A method for displaying
    
    /**
     * A method that visually displays the HealthBar object associated with this view;
     * it appears as a red rectangle in the upper left corner
     * @author - Team 8
     * @param g - an instance of Graphics handled internally by Java
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, width, height);
        g.setColor(Color.RED);
        g.fillRect(5, 5, (int)(((double)currentPoints/maxPoints) * width), height);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(currentPoints), width + 15, height);
    }
    

}
