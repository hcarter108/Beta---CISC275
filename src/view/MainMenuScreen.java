package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Game;
import model.Game.DifficultyLVL;
import view.ScreenPanel.Screens;

public class MainMenuScreen extends ScreenPanel implements ActionListener{
	
	//Fields
	
	private JFrame parentBoard;
	private JButton start;
	private JButton l1;
	private JButton l2;
	private JButton l3;
	private JButton l4;
	
	private int DEFAULT_WIDTH = 800;
	private int DEFAULT_HEIGHT = 600;
	
	private Image bgImage;
	
	//Constructor
	
	/**
	 * Constructor for MainMenuScreen
	 * @author - Team 8
	 * @param s- the screen type for this Object, from enum Screens
	 * @param parent- the parent GameBoard which contains this screen
	 */
	public MainMenuScreen(Screens s, JFrame parent){
		super();
		parentBoard = parent;
		bgImage = uploadImage(s.name());
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT ));
		this.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		buildMainMenuScreen();
		repaint();
		this.setVisible(true);
		}
	
	// Initialization of display and other display functions
	
	/**
	 * Builds various aspects of the MainMenuScreen display, such as JLabels and JButtons.
	 * @author - Team 8
	 */
	public void buildMainMenuScreen(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel main = new JLabel("Main Menu");
		main.setAlignmentX(Component.LEFT_ALIGNMENT);
		main.setFont(new Font("Calibri", Font.PLAIN, 60));
		add(main);
		add(Box.createRigidArea(new Dimension(20,(int)DEFAULT_HEIGHT/40)));
		
		JLabel campaign = new JLabel("Campaign");
		campaign.setFont(new Font("Calibri", Font.PLAIN, 36));
		add(campaign);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		start = new JButton("Begin Campaign");
		start.setFont(new Font("Calibri", Font.PLAIN, 20));
		start.addActionListener(this);
		start.setActionCommand("start");
		this.add(start);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		JLabel lvlSelect = new JLabel("Level Select");
		lvlSelect.setFont(new Font("Calibri", Font.PLAIN, 36));
		this.add(lvlSelect);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		l1 = new JButton("Level 1 (BlackBird Creek Part I)");
		l1.setFont(new Font("Calibri", Font.PLAIN, 12));
		l1.addActionListener(this);
		l1.setActionCommand("l1");
		this.add(l1);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		l2 = new JButton("Level 2 (BlackBird Creek Part II)");
		l2.setFont(new Font("Calibri", Font.PLAIN, 12));
		l2.addActionListener(this);
		l2.setActionCommand("l2");
		this.add(l2);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		l3 = new JButton("Level 3 (St. Jones River Part I)");
		l3.setFont(new Font("Calibri", Font.PLAIN, 12));
		l3.addActionListener(this);
		l3.setActionCommand("l3");
		this.add(l3);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		l4 = new JButton("Level 4 (St. Jones River Part II)");
		l4.setFont(new Font("Calibri", Font.PLAIN, 12));
		l4.addActionListener(this);
		l4.setActionCommand("l4");
		this.add(l4);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		JLabel tutorial = new JLabel("Tutorial");
		tutorial.setFont(new Font("Calibri", Font.PLAIN, 36));
		this.add(tutorial);
		
		add(Box.createRigidArea(new Dimension(0,(int)DEFAULT_HEIGHT/40)));
		
		JButton tutorialButton = new JButton("Begin Tutorial");
		tutorialButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		tutorialButton.addActionListener(this);
		tutorialButton.setActionCommand("tutorial");
		this.add(tutorialButton);
	}
	
	/**
	 * Displays a select difficulty window, after a difficulty is chosen it also changes to a given level.
	 * @author - Team 8
	 * @param s - the screen type from Enum Screens, used to change the screen after a difficulty is chosen.
	 */
    public void showSelectDifficultyWindow(Screens s) {
    	
    	JButton easyLevelButton = new JButton("Easy");
	    JButton mediumLevelButton = new JButton("Medium");
	    JButton hardLevelButton = new JButton("Hard");
	    JLabel instructionsLabel = new JLabel("<html><center>Select difficulty and then <br> in next window click on red ball <br> to start game!</center></html>");
    	
    	JFrame window = new JFrame("Select Difficulty");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.1;
        panel.add(instructionsLabel, c);
        c.gridy = 1;
        panel.add(easyLevelButton, c);
        c.gridy = 2;
        panel.add(mediumLevelButton, c);
        c.gridy = 3;
        panel.add(hardLevelButton, c);

        window.setTitle("Select Level.");
        window.add(panel);
        window.setSize(250, 270);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        easyLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	((GameBoard) parentBoard).changeScreenTo(s);
            	((GameBoard) parentBoard).setDifficulty(DifficultyLVL.EASY);
            	window.setVisible(false);
            }
        });

        mediumLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	((GameBoard) parentBoard).changeScreenTo(s);
            	((GameBoard) parentBoard).setDifficulty(DifficultyLVL.MEDIUM);
            	window.setVisible(false);
            }
        });

        hardLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	((GameBoard) parentBoard).changeScreenTo(s);
            	((GameBoard) parentBoard).setDifficulty(DifficultyLVL.HARD);
            	window.setVisible(false);
            }
        });

    
}
	
	//Override of paintComponent()
	
    /**
     * Override of JPanel's paintComponent method. Specifically, it paints a background image.
     * @author - Team 8
     */
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    if (bgImage != null)
	    {
	        g.drawImage(bgImage,0,0,this);
	    }
	}
	

	//Implementation of ActionListener
	
	/**
	 * Implementation of ActionListener
	 * @author - Team 8
	 */
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
			case("start"):
			{
				showSelectDifficultyWindow(Screens.L1Pre);
				break;
			}
			case("l1"):
			{
				showSelectDifficultyWindow(Screens.L1Pre);
				break;
			}
			case("l2"):
			{
				showSelectDifficultyWindow(Screens.L2Pre);
				break;
			}
			case("l3"):
			{
				showSelectDifficultyWindow(Screens.L3Pre);
				break;
			}
			case("l4"):
			{
				showSelectDifficultyWindow(Screens.L4Pre);
				break;
			}
			case("tutorial"):
			{
				((GameBoard) parentBoard).changeScreenTo(Screens.TUTORIAL);
            	((GameBoard) parentBoard).setDifficulty(DifficultyLVL.TUTORIAL);
				break;
			}
		}
	}


}

