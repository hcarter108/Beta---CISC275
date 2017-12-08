package tests;

import org.junit.Test;

import model.Coordinates;

public class TestCoordinates {

	@Test
	public void testGetters(){
		Coordinates c = new Coordinates(1.0,1.0);
		assert (c.getxPos()==1.0);
		assert (c.getyPos()==1.0);
	}
}
