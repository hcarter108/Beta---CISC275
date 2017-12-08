package helper;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class HelperFunctions {
	
	/**
	 * Reads a .txt file contained in the package, and creates an ArrayList containing its strings, line-by-line.
	 * @author - Team 8
	 * @param s- the string to be appended to the file path
	 * @return- the ArrayList of string values from the .txt file, line-by-line
	 */
	public ArrayList<String> readTXTFile(String s){
	    String fileName = "/text/"+s+"INFO.txt";
	    String line = null;
	    ArrayList<String> parsedFile = new ArrayList<String>();

	    try {
	        InputStreamReader fileReader = new InputStreamReader(getClass().getResourceAsStream (fileName));
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        while((line = bufferedReader.readLine()) != null) {
	            parsedFile.add(line);
	        }   
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        ex.printStackTrace();            
	    }
	    catch(IOException ex) {
	        ex.printStackTrace();                
	    }
	    
	    return parsedFile;
	}

	/**
	 * Uploads an image from the project by concatenating a string to a file path.
	 * @author - Team 8
	 * @param s- a string value
	 * @return - the image associated with the string parameter
	 */
	public Image uploadImage(String s){
		Image img = null;
		try {
			img = ImageIO.read(this.getClass().getResource("/images/"+s+".png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	// We were going to do more with this, but decided against it since we couldn't easily make our own sounds.
	// However, the functionality has been checked and is left in case anyone ever wanted to use it\
	// All this function assumes is that the sound files are put in the sounds package within this project
	/**
	 * Uploads an audio file and plays the sound.
	 * @author - Team 8
	 * @param soundFile- the string representing the file name, e.g example.wav
	 */
	public void uploadAndPlaySound(String soundFile) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("/sounds/"+soundFile));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}  

    }
	


	}

