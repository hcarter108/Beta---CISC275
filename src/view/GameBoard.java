package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game.DifficultyLVL;
import view.ScreenPanel.Screens;

public class GameBoard extends JFrame implements ActionListener {
	
	//Fields
	
	private DifficultyLVL difficulty;
	
	private JPanel currentScreen;
	private static JMenuBar customJMenuBar;
	
	private boolean isGame = false;
	private boolean isPaused = false;
	
	//Constructor

	/**
	 * A constructor for GameBoard. GameBoard is a child of JFrame that contains all the levels and contains useful view information.
	 * It is the top-level container for all the view.
	 * @author - Team 8
	 */
	public GameBoard()
	{
		super("Estuary Survival");
	}
	
	//Getters and Setters
	
	/**
	 * Setter for whether or not a game is running
	 * @author - Team 8
	 * @param b - the boolean value of whether or not a game is running
	 */
	public void setIsGame(boolean b){
		isGame = b;
	}
	
	/**
	 * Getter for whether or not a game is running
	 * @author - Team 8
	 * @return - the boolean value of whether or not a game is running
	 */
	public boolean getIsGame(){
		return isGame;
	}
	
	/**
	 * Getter for the difficulty of the current game, from enum DifficultyLVL
	 * @author - Team 8
	 * @return - the difficulty of the current game
	 */
	public DifficultyLVL getDifficulty() {
		return difficulty;
	}

	/**
	 * Setter for the difficulty of the current game, from enum DifficultyLVL
	 * @author - Team 8
	 * @param difficulty - the difficulty of the current game
	 */
	public void setDifficulty(DifficultyLVL difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * Getter for whether or not a game is running currently
	 * @author - Team 8
	 * @return - the boolean value of whether or not a game is running
	 */
	public boolean isGame(){
		return isGame;
	}
	
	/**
	 * Gets whether or not the game is currently paused
	 * @author - Team 8
	 * @return the boolean value of whether or not the game is paused
	 */
	public boolean isPaused(){
		return isPaused;
	}
	
	/**
	 * Sets whether or not the game is currently paused, is also set to false when
	 * a Game object isn't currently instantiated
	 * @author - Team 8
	 * @param b - the boolean value of whether or not the game is paused
	 */
	public void setIsPaused(boolean b){
		isPaused=b;
	}
	
	// Initialization and other display functions
	
	/**
	 * This is called after the GameBoard is instantiated and intializes further aspects of the GameBoard
	 * as well as uploading the title screen. This will essentially start the program.
	 * @author - Team 8
	 */
	public void start()
	{
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initMenuBar();
		setJMenuBar(customJMenuBar);
		currentScreen = new TitleScreen(Screens.TITLE, this);
		add(currentScreen);
		setVisible(true);
	}
	
	/**
	 * Changes the screen to a new screen, takes a type from enum Screens and creates
	 * the relevant child of ScreenPanel.
	 * @author - Team 8
	 * @param s - the type from Screens which will be used to create a child of ScreenPanel
	 */
	public void changeScreenTo(Screens s){
		currentScreen.setVisible(false);
		this.remove(currentScreen);
		switch(s){
		case MAIN:{
			currentScreen = new MainMenuScreen(Screens.MAIN, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			isGame = false;
			break;
		}
		case L1Pre:{
			currentScreen = new PreLevelScreen(Screens.L1Pre, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			isGame = false;
			break;
		}
		case L2Pre:{
			currentScreen = new PreLevelScreen(Screens.L2Pre, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			isGame = false;
			break;
		}
		case L3Pre:{
			currentScreen = new PreLevelScreen(Screens.L3Pre, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			isGame = false;
			break;
		}
		case L4Pre:{
			currentScreen = new PreLevelScreen(Screens.L4Pre, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			isGame = false;
			break;
		}
		case L1:{
			currentScreen = new LevelScreen(Screens.L1, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			((LevelScreen) currentScreen).startGame();
			isGame = true;
			break;
		}
		case L2:{
			currentScreen = new LevelScreen(Screens.L2, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			((LevelScreen) currentScreen).startGame();
			isGame = true;
			break;
		}
		case L3:{
			currentScreen = new LevelScreen(Screens.L3, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			((LevelScreen) currentScreen).startGame();
			isGame = true;
			break;
		}
		case L4:{
			currentScreen = new LevelScreen(Screens.L4, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			((LevelScreen) currentScreen).startGame();
			isGame = true;
			break;
		}
		case TUTORIAL:{
			currentScreen = new LevelScreen(Screens.TUTORIAL, this);
			currentScreen.setVisible(true);
			add(currentScreen);
			((LevelScreen) currentScreen).startGame();
			isGame = true;
			break;
		}
		
		}
	}

	/**
	 * Initializes the menu bar of class JMenu for this GameBoard and all its JMenuItems
	 * @author - Team 8
	 */
	public void initMenuBar() {
		customJMenuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu options = new JMenu("Options");
		JMenu help = new JMenu("Help");
		JMenuItem quit = new JMenuItem("Quit game");
		quit.addActionListener(this);
		quit.setActionCommand("quit");
		JMenuItem exit = new JMenuItem("Exit to main menu");
		exit.addActionListener(this);
		exit.setActionCommand("exit");
		JMenuItem restart= new JMenuItem("Restart level");
		restart.addActionListener(this);
		restart.setActionCommand("restart");
		JMenuItem changeDiff = new JMenuItem("Change difficulty");
		changeDiff.addActionListener(this);
		changeDiff.setActionCommand("changeDiff");
		JMenuItem showCredits = new JMenuItem("Show Credits");
		showCredits.addActionListener(this);
		showCredits.setActionCommand("showCredits");
		JMenuItem exit2Tutorial = new JMenuItem("Play Tutorial");
		exit2Tutorial.addActionListener(this);
		exit2Tutorial.setActionCommand("exit2Tutorial");
		JMenuItem controls = new JMenuItem("Show controls");
		controls.addActionListener(this);
		controls.setActionCommand("controls");
		JMenuItem principle = new JMenuItem("Our Estuary Principle");
		principle.addActionListener(this);
		principle.setActionCommand("principle");
		file.add(quit);
		file.add(exit);
		options.add(restart);
		options.add(changeDiff);
		options.add(showCredits);
		help.add(exit2Tutorial);
		help.add(controls);
		help.add(principle);
		customJMenuBar.add(file);
		customJMenuBar.add(options);
		customJMenuBar.add(help);
	}

	// Implementation of ActionListener
	
	/**
	 * Implementation for ActionListener. This will handle click events on menu items.
	 * @author - Team 8
	 */
	public void actionPerformed(ActionEvent e) {
		if (isGame)
			((LevelScreen) currentScreen).pause();
		switch (e.getActionCommand()) {
		case ("quit"): {
			int choice = JOptionPane.showConfirmDialog(this,
					"This will close the program, are you sure you want to proceed?");
			if (choice == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
		}
		case ("exit"): {
			int choice = JOptionPane.showConfirmDialog(this,
					"This will take you to the Main Menu, are you sure you want to proceed?");
			if (choice == JOptionPane.YES_OPTION)
				changeScreenTo(Screens.MAIN);
			break;
		}
		case("restart"):{
			if(isGame){
			int choice = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to restart the level?");
			if (choice == JOptionPane.YES_OPTION)
				changeScreenTo(((LevelScreen) currentScreen).getScreenType());
			}
			else
				JOptionPane.showMessageDialog(this, "You are not playing a game currently");
			break;
		
		}
		case ("changeDiff"): {
			if(!isGame)
				JOptionPane.showMessageDialog(this, "You are not playing a game currently");
			else{
			int choice = JOptionPane.showConfirmDialog(this,
					"This will exit to the main menu, are you sure you want to proceed?");
			if (choice == JOptionPane.YES_OPTION)
				changeScreenTo(Screens.MAIN);}
			break;
		}
		case ("showCredits"): {
			StringBuilder string = new StringBuilder("<html><left>");
			ArrayList<String> listCredits = new ScreenPanel().readTXTFile("CREDITS");
			for (int i = 0; i < listCredits.size(); i++) {
				string.append(listCredits.get(i)+"<br>");
			}
			string.append("</left></html>");
			JOptionPane.showMessageDialog(this, string.toString());
			break;
		}
		case ("exit2Tutorial"): {
			int choice = JOptionPane.showConfirmDialog(this,
					"This will begin the tutorial, are you sure you want to proceed?");
			if (choice == JOptionPane.YES_OPTION)
				changeScreenTo(Screens.TUTORIAL);
			break;
		}
		case ("controls"): {
			JOptionPane.showMessageDialog(this, "<html><center>Slide the mouse in the direction you want to move to avoid enemies"
					+ "<br> Make sure you move the mouse only on the game screen.</center></html>");
			break;
		}
		case("principle"):{
			JOptionPane.showMessageDialog(this, "<html><center>Human activities can impact estuaries by degrading water "
					+ "quality or altering habitats; <br> therefore, we are responsible for making decisions <br>  "
					+ "to protect and maintain the health of estuaries.  </center></html>");
		}
		}
		if (isGame)
			((LevelScreen) currentScreen).unpause();
	}
	}


