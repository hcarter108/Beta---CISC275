package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import model.Enemy;
import model.Game;
import model.HealthBar;
import model.Player;
import model.Game.DifficultyLVL;
import model.GameTimer;
import view.ScreenPanel.Screens;

public class LevelScreen extends ScreenPanel {

	//Fields
	private Screens currentScreen;
	private DifficultyLVL difficulty;
	
	private JFrame parent;
	private LevelScreen thisLevel;
	private ArrayList<JButton> arrayOfAnswerButtons = new ArrayList<JButton>();
	
	private int board_Width;
	private int board_Height;
	private int numLinesPreLevel = 8;
	private int numQs = 4;
	private int numQsRemaining=4;
	private int numCorrectAns = 4;
	private int numIncorrectAnsPerQ = 3;
	private double scaleFactor = 1;
	
	private ArrayList<String> listQuestions = new ArrayList<String>();
	private ArrayList<String> listCorrectAns = new ArrayList<String>();
	private ArrayList<String> listIncorrectAns = new ArrayList<String>();
	private ArrayList<Integer> possibleIndices = new ArrayList<Integer>();
	private boolean isStarted = false;
	
	private Game currentGame;
	private Controller currentGameController;
	private Image bgImage;
	private Image playerIMG;
	private Image invasiveIMG;
	private Image pollutionIMG;

	@Override
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}
	
	//Constructor

	/**
	 * Constructor for LevelScreen
	 * @author - Team 8
	 * @param s - the screen type for this instance, from enum Screens
	 * @param parent - the container for this instance, in this case a GameBoard
	 */
	public LevelScreen(Screens s, JFrame parent) {
		super();
		this.setLayout(new BorderLayout());
		this.validate();
		this.setOpaque(true);
		this.parent = parent;
		currentScreen = s;
		board_Width = parent.getWidth();
		board_Height = parent.getHeight();
		scaleFactor = ((GameBoard) parent).getScaleFactor();
		this.setPreferredSize(new Dimension(board_Width, board_Height));
		this.setMinimumSize(new Dimension(board_Width, board_Height));
		if(currentScreen!=Screens.TUTORIAL){
			difficulty = ((GameBoard) parent).getDifficulty();
			initIndexingSets();
			cnfgFieldsFromTXT(s);
		}
		else
			difficulty = DifficultyLVL.TUTORIAL;
		thisLevel = this;
		initImageIcons();
		repaint();
		this.setVisible(true);
		initGame(difficulty);
	}

	//Getters and Setters
	
	/**
	 * Getter for the Game object associated with this instance
	 * @author - Team 8
	 * @return - the Game object associated with this instance
	 */
	public Game getGame() {
		return currentGame;
	}
	
	/**
	 * Getter for the player diameter, calculates the diameter in terms of the Player's image
	 * dimensions.
	 * @author - Team 8
	 * @return - the diameter of the player
	 */
	public int getPlayerDiameter(){
		return Math.min(playerIMG.getHeight(null), playerIMG.getWidth(null));
	}
	
	/**
	 * Getter for the enemy diameter, calculates the diameter in terms of the Enemy's image
	 * @author - Team 8
	 * @return - the enemy diameter
	 */
	public int getEnemyDiameter(){
		return Math.min(Math.min(invasiveIMG.getHeight(null), invasiveIMG.getWidth(null))
				,Math.min(pollutionIMG.getHeight(null), pollutionIMG.getWidth(null)));
	}
	
	/**
	 * Getter for the number of questions remaining in an instance of Game.
	 * @author - Team 8
	 * @return - the number of questions remaining in the associated instance of game
	 */
	public int getNumQsRemaining(){
		return numQsRemaining;
	}
	
	/**
	 * Getter for this instance's ScreenType, from the enum ScreenType
	 * @author - Team 8
	 * @return - this instance of LevelScreen's ScreenType, from enum ScreenType
	 */
	@Override
	public Screens getScreenType(){
		return currentScreen;
	}
	
	// Functions for initialization of variables and the view
	
	/**
	 * Initializes a Game instance using this instance's fields as parameters
	 * @author - Team 8
	 * @param d - the difficulty level of the game, from enum DifficultyLVL
	 */
	public void initGame(DifficultyLVL d){
		currentGame = new Game(currentScreen,d);
		currentGame.setPanelForCommunication(this);
		currentGame.setBoardInfo(board_Width, board_Height, scaleFactor);
	}

	/**
	 * Used to start the Game logic and the associated Controller, which then ticks and runs the game
	 * @author - Team 8
	 */
	void startGame() {
		isStarted = true;
		((GameBoard) parent).setIsGame(true);
		initLayoutWithPause();
		currentGameController = new Controller(40, this);
		currentGameController.tick();
		pause();
		JOptionPane.showMessageDialog(parent, "Click ok to start");
		unpause();
	}
	
	public void receiveBoardInfo(int boardWidth, int boardHeight, double scaleFactor){
		board_Width = boardWidth;
		board_Height = boardHeight;
		this.scaleFactor = scaleFactor;
		currentGame.setBoardInfo(boardWidth, boardHeight, scaleFactor);
	}
	
	/**
	 * Initializes a layout with the pause button, sets the LayoutManager for this LevelScreen
	 * and then adds a pause button at the desired location
	 * @author - Team 8
	 */
	public void initLayoutWithPause(){
		JButton pause = new JButton("Pause");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(((GameBoard) parent).isPaused()){
					unpause();
					pause.setText("Pause");
				}
				else{
					pause();
					pause.setText("Unpause");
				}
				
			}
		});
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(10, board_Height-100)));
		add(pause);
		pause.setVisible(true);
}
	/**
	 * Initializes the indexing sets required for randomizing questions for this game
	 * @author - Team 8
	 */
	private void initIndexingSets(){
		for (int i=0; i<numQs; i++)
			possibleIndices.add(i);
	}
	
	/**
	 * Initializes the images for the background, player, and enemy objects
	 * @author - Team 8
	 */
	public void initImageIcons() {
		bgImage = uploadImage(currentScreen.name()).getScaledInstance(board_Width, board_Height, Image.SCALE_DEFAULT);
		playerIMG = scale(uploadImage(currentScreen.name() + "PLAYER"), scaleFactor);
		invasiveIMG = scale(uploadImage(currentScreen.name() + "INVASIVE"), scaleFactor);
		pollutionIMG = scale(uploadImage(currentScreen.name() + "POLLUTION"), scaleFactor);
	}
	
	/**
	 * Configures this LevelScreens fields from .txt files contained in the project.
	 * @author - Team 8
	 * @param currentScreen - the current ScreenType from enum Screens used for figuring out which .txt files to load
	 */
	private void cnfgFieldsFromTXT(Screens currentScreen) {
		ArrayList<String> allText = readTXTFile(currentScreen.name());  
		for (int i = 0; i < numQs; i++)
			listQuestions.add(allText.get(numLinesPreLevel + i));
		int numLinesPrior = numLinesPreLevel+numQs;
		for (int i = 0; i < numCorrectAns; i++)
			listCorrectAns.add(allText.get(numLinesPrior + i));
		numLinesPrior+=numCorrectAns;
		int numIncorrect = numIncorrectAnsPerQ*numQs;
		for (int i = 0; i<numIncorrect; i++)
			listIncorrectAns.add(allText.get(numLinesPrior+i));
		
	}
	
	// Code specific to an instance of the Tutorial Screen
	
	/**
	 * This will only run if this LevelScreen is an instance of TUTORIAL from enum Screens. It adds
	 * additional control for the Controller and displays messages at certain intervals.
	 * @author - Team 8
	 * @param seconds - the seconds that the Controller has counted since its start, used to run messages.
	 */
	public void runTutorialMessage(int seconds) {
		if(seconds==1){
			pause();
        	JOptionPane.showMessageDialog(parent, "Slide the mouse to move the player");
			unpause();
		}
		if(seconds==1){
			pause();
        	JOptionPane.showMessageDialog(parent, "<html><center>Try to avoid the invasive species and pollution objects (the other objects on the screen), each collision decreases your health. <br>The health bar is in the upper left corner</center></html>");
        	unpause();
		}
		if(seconds==4){
			pause();
        	JOptionPane.showMessageDialog(parent, "<html><center>In order to win the level, you must survive until the time reaches 0. <br>The time bar is the green rectangle in the lower right corner</center></html>");
        	unpause();
		}
		if(seconds==8){
        	pause();
        	currentGame.getHealthBar().setCurrentPoints(0);
        	refreshView();
        	JOptionPane.showMessageDialog(parent, "<html><center>If your health goes to zero, you can answer a question correctly <br>to get a new life, but there are only 4 questions per level</center></html>");
    	    
        	JLabel instructionsLabel = new JLabel("How do you move your player?");
        	JButton ans1 = new JButton("By sliding the mouse towards where you want to go");
    	    JButton ans2 = new JButton("By clicking random buttons");
    	    JButton ans3 = new JButton("By using arrow keys");
    	    JButton ans4 = new JButton("By doing nothing");
        	
        	JFrame window = new JFrame("A sample question");
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            c.gridx = 0;
            c.gridy = 0;
            c.weighty = 0.1;
            panel.add(instructionsLabel, c);
            c.gridy = 1;
        	panel.add(ans1, c);
            c.gridy = 2;
        	panel.add(ans2, c);
            c.gridy = 3;
        	panel.add(ans3, c);
            c.gridy = 4;
        	panel.add(ans4, c);;
            window.add(panel);
            window.setSize(350, 270);
            window.setResizable(false);
        	window.setLocationRelativeTo(null);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            
    		window.addWindowListener(new java.awt.event.WindowAdapter() {
    		    @Override
    		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                	currentGame.getHealthBar().setCurrentPoints(1000);
                	refreshView();
            		unpause();
    		        }
    		    
    		});

            ans1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	JOptionPane.showMessageDialog(parent, "That is correct! Goob job");
                	currentGame.getHealthBar().setCurrentPoints(1000);
                	refreshView();
                	window.setVisible(false);
                	unpause();
                }
            });
            
            ans2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	JOptionPane.showMessageDialog(parent, "That is incorrect! But this is only a tutorial so press ok to continue");
                	int choice = JOptionPane.showConfirmDialog(thisLevel, "Would you like to continue this tutorial? Yes will continue the tutorial and no will take you to the menu", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
                	if (choice==JOptionPane.YES_OPTION)
                	{
                    	currentGame.getHealthBar().setCurrentPoints(1000);
                    	refreshView();
                		unpause();
                	}
                	else{
                		JOptionPane.showMessageDialog(parent, "Press OK to exit to the main menu");
                		((GameBoard) parent).changeScreenTo(Screens.MAIN);
                	}
                	window.setVisible(false);
                }
            });
            
            ans3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	JOptionPane.showMessageDialog(parent, "That is incorrect! But this is only a tutorial so press ok to continue");
                	int choice = JOptionPane.showConfirmDialog(thisLevel, "Would you like to continue this tutorial? Yes will continue the tutorial and no will take you to the menu", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
                	if (choice==JOptionPane.YES_OPTION)
                	{
                    	currentGame.getHealthBar().setCurrentPoints(1000);
                    	refreshView();
                		unpause();
                	}
                	else{
                		JOptionPane.showMessageDialog(parent, "Press OK to exit to the main menu");
                		((GameBoard) parent).changeScreenTo(Screens.MAIN);
                	}
                	window.setVisible(false);
                }
            });
            
            ans4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	JOptionPane.showMessageDialog(parent, "That is incorrect! But this is only a tutorial so press ok for options");
                	int choice = JOptionPane.showConfirmDialog(thisLevel, "Would you like to continue this tutorial? Yes will continue the tutorial and no will take you to the menu", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
                	if (choice==JOptionPane.YES_OPTION)
                	{
                    	currentGame.getHealthBar().setCurrentPoints(1000);
                    	refreshView();
                		unpause();
                	}
                	else{
                		JOptionPane.showMessageDialog(parent, "Press OK to exit to the main menu");
                		((GameBoard) parent).changeScreenTo(Screens.MAIN);
                	}
                	window.setVisible(false);
                }
            });
           
    	}
		if(seconds==11){
			pause();
			JOptionPane.showMessageDialog(parent, "Other than that, you can choose campaign to play each level or just choose a particular level");
			unpause();
		}
		if(seconds==15){
			pause();
			JOptionPane.showMessageDialog(parent, "That's pretty much all there is to it, click OK to go to the main menu");
    		((GameBoard) parent).changeScreenTo(Screens.MAIN);
		}
			
		
	}
	
	//Other functions for the view
	
	/**
	 * Paints the HealthBar on the screen.
	 * @author - Team 8
	 * @param g - an instance of Graphics used internally by Java
	 */
	private void paintHealthBar(Graphics g) {
		new HealthBarView(currentGame.getHealthBar()).draw(g);
	}
	
	/**
	 * Paints the GameTimer on the screen.
	 * @author - Team 8
	 * @param g - an instance of Graphics used internally by Java
	 */
	private void paintGameTimer(Graphics g){
		GameTimerView gtv = new GameTimerView(currentGame.getGameTimer(), board_Width, board_Height);
		gtv.drawBar(g);
	}

	/**
	 * Paints the player on the screen.
	 * @author - Team 8
	 * @param g - an instance of Graphics used internally by Java
	 */
	private void paintPlayer(Graphics g) {
		new PlayerView(currentGame.getPlayer(), playerIMG).draw(g);
	}

	/**
	 * Paints the enemy objects on the screen.
	 * @author - Team 8
	 * @param g - an instance of Graphics used internally by Java
	 */
	private void paintBalls(Graphics g) {
		Enemy[] listEnemies = currentGame.getListEnemies();

		for (int i = 0; i < currentGame.getNumInvasive(); i++) 
			new EnemyView(listEnemies[i], invasiveIMG).draw(g);
		
		for (int i = 0; i<currentGame.getNumPollution(); i++)
			new EnemyView(listEnemies[currentGame.getNumInvasive()+i], pollutionIMG).draw(g);
	}
	
	/**
	 * Paints every aspect of the model that needs to be painted on the screen. Overrides the paintComponent()
	 * method from JPanel.
	 * @author - Team 8
	 */
	@Override
	public void paintComponent(Graphics g) {
		
	    super.paintComponent(g);
	    if (bgImage != null)
	    {
	        g.drawImage(bgImage,0,0,this);
	    }

		if (isStarted) {

			paintPlayer(g);

			if (currentGame.getListEnemies() != null) {
				paintBalls(g);
			}

			paintHealthBar(g);
			paintGameTimer(g);
		}
	}
	
	/**
	 * Calls repaint from JPanel, now extended to LevelScreen.
	 * @author - Team 8
	 */
	public void refreshView() {
		repaint();
	}
	
	// Important program control functions

	/**
	 * Unpauses the Controller, and hence the current game.
	 * @author - Team 8
	 */
	public void unpause(){
		currentGameController.tick();
		((GameBoard) parent).setIsPaused(false);
	}
	
	/**
	 * Pauses the Controller, and hence the current game.
	 * @author - Team 8
	 */
	public void pause(){
		currentGameController.pause();
		((GameBoard) parent).setIsPaused(true);;
	}
	
	/**
	 * Runs the code associated with in-game death, in particular it generates a random question
	 * about the content of the game.
	 * @author - Team 8
	 */
	public void runDeathScenario(){
		generateRandomQ();
	}
	
	/**
	 * Runs the code associated with the end-game state, in particular this is called if you run out of questions to get a new life.
	 * @author - Team 8
	 */
	public void endGame(){
    	int choice = JOptionPane.showConfirmDialog(thisLevel, "You are out of lives, select yes to restart the level or no to exit to the Main Menu", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
    	if (choice==JOptionPane.YES_OPTION)
    		((GameBoard) parent).changeScreenTo(currentScreen);
    	else
    		((GameBoard) parent).changeScreenTo(Screens.MAIN);
	}
	
	// Functions that handle generating random questions
	
	/**
	 * Generates a random number in the range 0 to numQs, or number of questions.
	 * @author - Team 8
	 * @return - returns a random number in the range 0 to numQs
	 */
	public int generateRandomRangeQsRemaining(){
		return (int) Math.floor(Math.random()*numQsRemaining);
	}
	
	/**
	 * Generates a random question. This method both randomizes the order in which questions are asked
	 * and the order in which the answers appear so as to not create a pattern for correct answers.
	 * @author - Team 8
	 */
	public void generateRandomQ(){
		
		if(currentScreen!=Screens.TUTORIAL){
		int rand = generateRandomRangeQsRemaining();

		int unusedIndex = possibleIndices.get(rand);
		possibleIndices.remove(rand);
		int incorrectStartInd = numIncorrectAnsPerQ*unusedIndex;
		numQsRemaining--;
		
		JLabel instructionsLabel = new JLabel(listQuestions.get(unusedIndex));
		JButton ans1 = new JButton(listCorrectAns.get(unusedIndex));
	    JButton ans2 = new JButton(listIncorrectAns.get(incorrectStartInd));
	    JButton ans3 = new JButton(listIncorrectAns.get(incorrectStartInd+1));
	    JButton ans4 = new JButton(listIncorrectAns.get(incorrectStartInd+2));
	    arrayOfAnswerButtons.add(ans1);
	    arrayOfAnswerButtons.add(ans2);
	    arrayOfAnswerButtons.add(ans3);
	    arrayOfAnswerButtons.add(ans4);
	    Collections.shuffle(arrayOfAnswerButtons);
    	
    	JFrame window = new JFrame("A chance at a new life");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.1;
        panel.add(instructionsLabel, c);
        
        for(int i=0; i<arrayOfAnswerButtons.size(); i++){
        	c.gridy = i+1;
        	panel.add(arrayOfAnswerButtons.get(i), c);
        }
        
        arrayOfAnswerButtons.clear();
        window.add(panel);
        window.setSize(250, 270);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ans1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(parent, "That is correct! Press ok to resume");
            	currentGame.getHealthBar().setCurrentPoints(200);
            	refreshView();
            	window.setVisible(false);
            	currentGame.setNotQ(true);
            	unpause();
            }
        });
        
        ans2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(parent, "That is incorrect!");
            	int choice = JOptionPane.showConfirmDialog(thisLevel, "Would you like to restart this level?", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            	if (choice==JOptionPane.YES_OPTION)
            		((GameBoard) parent).changeScreenTo(currentScreen);
            	else
            		((GameBoard) parent).changeScreenTo(Screens.MAIN);
            	window.setVisible(false);
            }
        });
        
        ans3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(parent, "That is incorrect!");
            	int choice = JOptionPane.showConfirmDialog(thisLevel, "Would you like to restart this level?", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            	if (choice==JOptionPane.YES_OPTION)
            		((GameBoard) parent).changeScreenTo(currentScreen);
            	else
            		((GameBoard) parent).changeScreenTo(Screens.MAIN);
            	window.setVisible(false);
            }
        });
        
        ans4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(parent, "That is incorrect!");
            	int choice = JOptionPane.showConfirmDialog(thisLevel, "Would you like to restart this level?", "End game", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
            	if (choice==JOptionPane.YES_OPTION)
            		((GameBoard) parent).changeScreenTo(currentScreen);
            	else
            		((GameBoard) parent).changeScreenTo(Screens.MAIN);
            	window.setVisible(false);
            }
        });
        
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	JOptionPane.showMessageDialog(parent, "You will now be exited to the main menu, click OK to continue");
		    	((GameBoard) parent).changeScreenTo(Screens.MAIN);
		        }
		    
		});
	}
	}
	
	/**
	 * This handles what happens when the player wins a level. It switches to the
	 * correct next level screen or to the main menu, depending on what the user chooses
	 * and which level they just won.
	 * @author - Team 8
	 */
	public void hasWon(){
		pause();
		if(!currentScreen.equals(Screens.L4))
		{
		int choice = JOptionPane.showConfirmDialog(this, "You've survived the level! Would you like to continue to the next level?", 
		"You've won!", JOptionPane.YES_NO_OPTION);
		if(choice==JOptionPane.YES_OPTION){
			switch(currentScreen){
			case L1:{
				((GameBoard) parent).changeScreenTo(Screens.L2Pre);
				break;
			}
			case L2:{
				((GameBoard) parent).changeScreenTo(Screens.L3Pre);
				break;
			}
			case L3:{
				((GameBoard) parent).changeScreenTo(Screens.L4Pre);
				break;
			}
			default:
				break;
			}
		}
		else
			((GameBoard) parent).changeScreenTo(Screens.MAIN);
		}
		else{
			JOptionPane.showMessageDialog(this, "You've completed the last level, "
					+ "press ok to continue to the main menu!");
			((GameBoard) parent).changeScreenTo(Screens.MAIN);
		}
	}
	
}