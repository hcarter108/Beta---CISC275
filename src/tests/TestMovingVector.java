package tests;

import org.junit.Test;

import model.MovingVector;

public class TestMovingVector {

	@Test
	public void testGetterAndSetter(){
		MovingVector mv =  new MovingVector(1,1);
		assert(mv.getX()==1);
		assert(mv.getY()==1);
	}
}
