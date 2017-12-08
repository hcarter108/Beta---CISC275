package model;

import java.awt.Image;
import java.util.ArrayList;

import helper.HelperFunctions;
import view.ScreenPanel.Screens;

public class MoveableObject {

	public enum MoveableType {PLAYER, INVASIVE, POLLUTION};
	private HelperFunctions helperObject = new HelperFunctions();
	
	/**
	 * Gets the width and height of MoveableObjects, based on their corresponding images
	 * @author - Team 8
	 * @param level- the current level, from enum Screens
	 * @param type- the type of the MoveableObject, from enum MoveableType
	 * @return- an ArrayList of Integer containing this MoveableObjects width and height
	 */
	public ArrayList<Integer> getMoveableDimensions(Screens level, MoveableType type){
		String enumString = null;
		switch(type){
		case PLAYER:{
			enumString = MoveableType.PLAYER.name();
			break;}
		case INVASIVE:{
			enumString = MoveableType.PLAYER.name();
			break;}
		case POLLUTION:{
			enumString = MoveableType.PLAYER.name();
			break;}
		}
		Image moveableImg = helperObject.uploadImage(level.name()+enumString);
		
		ArrayList<Integer> moveableDimensions = new ArrayList<Integer>();
		moveableDimensions.add(moveableImg.getWidth(null));
		moveableDimensions.add(moveableImg.getHeight(null));
		return moveableDimensions;
	}
	
	/**
	 * Calculates the minimum of the width and height of the object, to be used in collision detection.
	 * @author - Team 8
	 * @param moveableDimensions - the ArrayList associated with this MoveableObject that contains its width and height
	 * @return- the minimum of this MoveableObjects width and height
	 */
	public int calculateMinDiameter(ArrayList<Integer> moveableDimensions){
		return Math.min(moveableDimensions.get(0), moveableDimensions.get(1));
	}
	
}
