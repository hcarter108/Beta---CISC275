package tests;

import org.junit.Before;
import org.junit.Test;

import model.Coordinates;
import model.MoveableObject;
import model.Player;
import model.MoveableObject.MoveableType;
import model.MovingVector;
import view.ScreenPanel.Screens;

public class TestPlayer {

	Player p;
	
	@Before
	public void setUpPlayer(){
		p= new Player(new Coordinates(100,100), Screens.L1);
	}
	
	@Test
	public void testDiameter(){
		assert(p.getPlayerDiameter()==new MoveableObject().calculateMinDiameter(new MoveableObject()
				.getMoveableDimensions(Screens.L1, MoveableType.PLAYER)));
	}
	
	@Test
	public void testMoveByVector(){
		assert(p.getCoordinates().getxPos()==100 && p.getCoordinates().getyPos()==100);
		p.moveByVector(new MovingVector(10,10));
		assert(p.getCoordinates().getxPos()==110 && p.getCoordinates().getyPos()==110);
	}
}
