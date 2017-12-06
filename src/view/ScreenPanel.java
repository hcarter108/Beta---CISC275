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

/**
 * Uploads a sound file using a helper function
 * @author - Team 8
 * @param s- the string associated with the file name
 */
public void uploadAndPlaySound(String s){
	helperObject.uploadAndPlaySound(s);
}

/**
 * Used to scale an image
 * @author - Team 8
 * @param img- the image to be scaled
 * @param scaleFactor- the factor by which the image is scaled
 * @return- a scaled instance of the original image
 */
public Image scale(Image img, double scaleFactor){
	return img.getScaledInstance((int) (img.getWidth(null)*scaleFactor), (int) (img.getHeight(null)*scaleFactor), Image.SCALE_DEFAULT);
}

/**
 * Meant to be used by child classes to receive updates from the GameBoard after a resize event
 * @author - Team 8
 * @param boardWidth- the new board width
 * @param boardHeight- the new board height
 * @param scaleFactor- the new scale factor
 */
public void receiveBoardInfo(int boardWidth, int boardHeight, double scaleFactor){
}

/**
 * Meant to be used by child classes, returns the type from enum Screens
 * @author - Team 8
 * @return the type from enum Screens
 */
public Screens getScreenType(){
	return null;
}

}