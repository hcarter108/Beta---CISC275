package tests;

import org.junit.Before;
import org.junit.Test;

import model.GameTimer;
import model.HealthBar;

public class TestHealthBar {

	private HealthBar hb;
	
	@Before
	public void setUpTimer(){
		hb = new HealthBar(200);
	}
	
	@Test
	public void testMaxTime(){
		assert(200==hb.getMaxPoints());	
		}
	
	@Test
	public void testChangeTime(){
		assert(hb.getCurrentPoints()==200);
		hb.setCurrentPoints(100);
		assert(hb.getCurrentPoints()==100);
	}
}
