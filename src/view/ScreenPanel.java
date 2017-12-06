package view;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import helper.HelperFunctions;



public class ScreenPanel extends JPanel
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public enum Screens {TITLE, MAIN, L1Pre, L2Pre, L3Pre, L4Pre, L1, L2, L3, L4, TUTORIAL};   //used for cases and file names

private HelperFunctions helperObject = new HelperFunctions();

// functions used by subclasses

/**
 * Reads a .txt file contained in the package, and creates an ArrayList containing its strings, line-by-line.
 * @author - Team 8
 * @param s- the string to be appended to the file path, is really a value from enum Screens with .name() method
 * @return- the ArrayList of string values from the .txt file, line-by-line
 */
public ArrayList<String> readTXTFile(String s){
    return helperObject.readTXTFile(s);
}

/**
 * Uploads an image from the project by appending a string to a file path. In particular
 * the string is a value from enum Screens using .name() method.
 * @author - Team 8
 * @param s- a string value, from a value of enum Screens using .name() method
 * @return - the image associated with the string parameter
 */
public Image uploadImage(String s){
	return helperObject.uploadImage(s);
}

public void uploadAndPlaySound(String s){
	helperObject.uploadAndPlaySound(s);
}

public Image scale(Image img, double scaleFactor){
	return img.getScaledInstance((int) (img.getWidth(null)*scaleFactor), (int) (img.getHeight(null)*scaleFactor), Image.SCALE_DEFAULT);
}

public void receiveBoardInfo(int boardWidth, int boardHeight, double scaleFactor){
}

public Screens getScreenType(){
	return null;
}

}