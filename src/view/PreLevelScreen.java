package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Game.DifficultyLVL;
import view.ScreenPanel.Screens;

public class PreLevelScreen extends ScreenPanel implements ActionListener{
	
	//Fields
	
	private DifficultyLVL difficulty;
	
	private JFrame parentBoard;
	
	private Screens currentScreen;
	private Screens associatedLevel;
	
	private int DEFAULT_WIDTH = 800;
	private int DEFAULT_HEIGHT = 600;
	private int board_Width;
	private int board_Height;
	private double scaleFactor;
	private String levelSetting;
	private String settingINFO;
	private String playerINFO;
	private String invasiveINFO;
	private String pollutionINFO;
	private String playerSpecies;
	private String invasiveSpecies;
	private String pollutionType;
	
	private Image bgImage;
	private ImageIcon playerIMG;
	private ImageIcon invasiveIMG;
	private ImageIcon pollutionIMG;

	//Constructor
	/** Constructor for the pre-level screen
	 * @author Team 8
	 * @param s - The current screentype, from the enum Screens defined in ScreenPanel
	 * @param parent - The gameboard that this screen will be viewed from
	 */
	public PreLevelScreen(Screens s, JFrame parent){
		super();
		currentScreen = s;
		parentBoard = parent;
		board_Width = ((GameBoard) parent).getCurrentWidth();
		board_Height = ((GameBoard) parent).getCurrentHeight();
		scaleFactor = ((GameBoard) parent).getScaleFactor();
		this.setPreferredSize(new Dimension(board_Width, board_Height));
		this.setMinimumSize(new Dimension(board_Width, board_Height));
		bgImage = initBGImage();
		initAssociatedLevel(s);
		cnfgFieldsFromTXT(s);
		initImageIcons();
		initDisplay();
		repaint();
		this.setVisible(true);
		}

	// Getters and setters
	/**
	 * Gets the current difficulty, from enum DifficultyLVL
	 * @author - Team 8
	 * @return The difficulty of the current game, from enum DifficultyLVL
	 */
	public DifficultyLVL getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Sets the current difficulty, from enum DifficultyLVL
	 * @author - Team 8
	 * @param difficulty - The difficulty level of the current game, from enum DifficultyLVL
	 */

	public void setDifficulty(DifficultyLVL difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	public Screens getScreenType(){
		return currentScreen;
	}
	// Functions to initialize parameters and display

	/** Ininitalizes the field associatedLevel, which is used in other functions, in particular
	 *  so that the screen can be changed to the associated LevelScreen when done with the current
	 *  PreLevelScreen
	 * @author - Team 8
	 * @param s - The current screen type, from enum ScreenType in SceenPanel
	 */
	public void initAssociatedLevel(Screens s){
		switch(s){
		case L1Pre:
			associatedLevel=Screens.L1;
			break;
		case L2Pre:
			associatedLevel=Screens.L2;
			break;
		case L3Pre:
			associatedLevel=Screens.L3;
			break;
		case L4Pre:
			associatedLevel=Screens.L4;
			break;
		}
		
	}
	
	public Image initBGImage(){
		Image img = uploadImage(currentScreen.name());
		return img.getScaledInstance(board_Width, board_Height, Image.SCALE_DEFAULT);
	}
	
	
	/** Initializes the images to be used in the JLabels for the player, invasive, and pollution Objects
	 * @author - Team 8
	 */
	public void initImageIcons(){
		playerIMG = new ImageIcon(scale(uploadImage(associatedLevel.name()+"PLAYER"), scaleFactor));
		invasiveIMG = new  ImageIcon(scale(uploadImage(associatedLevel.name()+"INVASIVE"), scaleFactor));
		pollutionIMG = new ImageIcon(scale(uploadImage(associatedLevel.name()+"POLLUTION"), scaleFactor));
	}
	
	/** Initializes the view for this instance of PreLevelScreen, such as setting a LayoutManager,
	 * adding JLabels, etc.
	 * @author - Team 8
	 */
	public void initDisplay(){
		BoxLayout col = new BoxLayout(this, BoxLayout.Y_AXIS);
		JLabel levelLabel = new JLabel(levelSetting);
		initLabelAppearance(levelLabel);
		levelLabel.setFont(new Font("Calibri", Font.BOLD, (int) (scaleFactor*30)));
		JLabel levelTXT = new JLabel(settingINFO);
		initLabelAppearance(levelTXT);
		levelTXT.setFont(new Font("Calibri", Font.BOLD, (int) (scaleFactor*13)));
		JLabel playerLabel = new JLabel("Player Species: "+playerSpecies+ " - "+playerINFO, playerIMG, JLabel.RIGHT);
		initLabelAppearance(playerLabel);
		playerLabel.setFont(new Font("Calibri", Font.PLAIN, (int) (scaleFactor*13)));
		JLabel invasiveLabel = new JLabel("Invasive Species: "+ invasiveSpecies+" - "+invasiveINFO, invasiveIMG, JLabel.RIGHT);
		invasiveLabel.setFont(new Font("Calibri", Font.PLAIN, (int) (scaleFactor*13)));
		initLabelAppearance(invasiveLabel);
		JLabel pollutionLabel = new JLabel("Pollution Type: "+pollutionType+ " - "+pollutionINFO, pollutionIMG, JLabel.RIGHT);
		pollutionLabel.setFont(new Font("Calibri", Font.PLAIN, (int) (scaleFactor*13)));
		initLabelAppearance(pollutionLabel);
		JButton start = new JButton("Start Level");
		start.setFont(new Font("Calibri", Font.PLAIN, (int) (scaleFactor*20)));
		this.setLayout(col);
		start.addActionListener(this);
		start.setActionCommand("start");
		add(Box.createRigidArea(new Dimension(10,(int) (30*scaleFactor))));
		this.add(levelLabel);
		add(Box.createRigidArea(new Dimension(10,(int) (30*scaleFactor))));
		this.add(levelTXT);
		add(Box.createRigidArea(new Dimension(10,(int) (60*scaleFactor))));
		this.add(playerLabel);
		add(Box.createRigidArea(new Dimension(10,(int) (60*scaleFactor))));
		this.add(invasiveLabel);
		add(Box.createRigidArea(new Dimension(10,(int) (60*scaleFactor))));
		this.add(pollutionLabel);
		add(Box.createRigidArea(new Dimension(10,(int) (40*scaleFactor))));
		this.add(start);
		
	}
	
	/** Configures the information to be displayed on JLabels from .txt files in resources package
	 * @author - Team 8
	 * @param currentScreen - The current screen instance
	 */
	private void cnfgFieldsFromTXT(Screens currentScreen){
		ArrayList<String> allText = readTXTFile(associatedLevel.name());   
		levelSetting = allText.get(0);
		settingINFO = allText.get(1);
		playerSpecies = allText.get(2);
		playerINFO = allText.get(3);
		invasiveSpecies= allText.get(4);
		invasiveINFO = allText.get(5);
		pollutionType = allText.get(6);
		pollutionINFO = allText.get(7);
	}
	
	/** Takes a JComponent and sets certain parameters, such as color. Is used to standardize 
	 * the appearance of the components
	 * @author - Team 8
	 * @param component - The JComponent that will have its appearance modified
	 */
	public void initLabelAppearance(JComponent component){
		component.setForeground(Color.WHITE);
		component.setBackground(Color.BLUE);
		component.setOpaque(true);
	}
	
	//Override of paintComponent
	
	/** 
	 * @author - Team 8
	 * Overrides paintComponent, used primarily to set the background image for this screen
	 */
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    if (bgImage != null)
	    {
	        g.drawImage(bgImage,0,0,this);
	    }
	}
	
	// Implementation of ActionListener
	
	/** Implements the method actionPerformed from ActionListener, switches from this PreLevelScreen
	 *  to the relevant LevelScreen
	 * @author - Team 8
	 * @param e - The ActionEvent that was performed
	 */
	public void actionPerformed(ActionEvent e){
		switch(currentScreen){
		case L1Pre :{
			((GameBoard) parentBoard).changeScreenTo(Screens.L1);
			break;
		}
		case L2Pre:{
			((GameBoard) parentBoard).changeScreenTo(Screens.L2);
			break;
		}
		case L3Pre :{
			((GameBoard) parentBoard).changeScreenTo(Screens.L3);
			break;
		}
		case L4Pre :{
			((GameBoard) parentBoard).changeScreenTo(Screens.L4);
			break;
		}
		}
	}
	
	public void receiveBoardInfo(int boardWidth, int boardHeight, double scaleFactor){
		board_Width = boardWidth;
		board_Height = boardHeight;
		this.scaleFactor = scaleFactor;
	}
	
}
