package model;

import java.awt.Image;
import java.util.ArrayList;

import helper.HelperFunctions;
import view.ScreenPanel.Screens;

public class MoveableObject {

	enum MoveableType {PLAYER, INVASIVE, POLLUTION};
	private HelperFunctions helperObject = new HelperFunctions();
	
	//min because else collisions seemed to happen from far way
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
	
	public int calculateMinDiameter(ArrayList<Integer> moveableDimensions){
		return Math.min(moveableDimensions.get(0), moveableDimensions.get(1));
	}
	
	public double calculateAspectRatio(ArrayList<Integer> moveableDimensions){
		return (moveableDimensions.get(0)/moveableDimensions.get(1));
	}
}
