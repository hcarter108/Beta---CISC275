package tests;

import java.awt.Image;
import java.util.ArrayList;

import org.junit.Test;

import helper.HelperFunctions;
import model.MoveableObject;
import model.MoveableObject.MoveableType;
import view.ScreenPanel.Screens;

public class TestMoveableObject {
	
	private ArrayList<Integer> dimensions = new MoveableObject().getMoveableDimensions(Screens.L1, MoveableType.INVASIVE);
	private Image L1INVASIVE = new HelperFunctions().uploadImage("L1INVASIVE");

	@Test
	public void testGetMoveableDimensions(){
		assert(dimensions.get(0)==L1INVASIVE.getWidth(null));
		assert(dimensions.get(1)==L1INVASIVE.getHeight(null));
	}
	
	@Test
	public void testMinDiameter(){
		MoveableObject MO = new MoveableObject();
		assert(MO.calculateMinDiameter(dimensions)==Math.min(L1INVASIVE.getHeight(null), L1INVASIVE.getWidth(null)));
		
	}
}
