package tests;

import org.junit.Before;
import org.junit.Test;

import model.GameTimer;

public class TestGameTimer {

	private GameTimer gt;
	
	@Before
	public void setUpTimer(){
		gt = new GameTimer(100);
	}
	
	@Test
	public void testMaxTime(){
		assert(100==gt.getMaxTime());	
		}
	
	@Test
	public void testChangeTime(){
		assert(gt.getTimeRemaining()==100);
		gt.setTimeRemaining(50);
		assert(gt.getTimeRemaining()==50);
	}
}
