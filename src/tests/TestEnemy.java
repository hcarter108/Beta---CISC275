package tests;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

import helper.HelperFunctions;
import model.Coordinates;
import model.Enemy;
import model.MovingVector;
import model.MoveableObject.MoveableType;
import view.ScreenPanel.Screens;

public class TestEnemy {

	private static Enemy e;
	
	// chose INVASIVE mostly since its moving angle case is more testable, whereas POLLUTION has random angles
	// also didn't destroy afterwards since java handles cleaning up memory
	@Before
	public void setUpEnemy(){
		e = new Enemy(new Coordinates(1.0, 1.0), new MovingVector(1.0, 1.0), Screens.L1, MoveableType.INVASIVE);
	}
	
	@Test
	public void testMovingAngle(){
		e.initMovingAngle();
		assert(e.getMovingAngle()==45.0);
	}
	
	@Test
	public void testMoveByVector(){
		e.moveByVector();
		assert(e.getCoordinates().getxPos()==2.0 && e.getCoordinates().getyPos()==2.0);
	}
	
	@Test 
	public void testEnemyDiameter(){
		Image img = new HelperFunctions().uploadImage("L1INVASIVE");
		assert (e.getEnemyDiameter()==Math.min(img.getWidth(null), img.getHeight(null)));
	}
}
