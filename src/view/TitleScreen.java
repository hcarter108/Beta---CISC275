package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import view.ScreenPanel.Screens;

public class TitleScreen extends ScreenPanel implements ActionListener{
	
	private Image bgImage;
	private int board_Width;
	private int board_Height;
	private double scaleFactor = 1;
	private JFrame parentBoard;
	
	/**
	 * Constructor for TitleScreen
	 * @author - Team 8
	 * @param s - a value from enum Screens, used to instantiate this class
	 * @param parent - the associated GameBoard
	 */
	public TitleScreen(Screens s, JFrame parent){
		super();
		parentBoard = parent;
		board_Width = ((GameBoard) parent).getCurrentWidth();
		board_Height = ((GameBoard) parent).getCurrentHeight();
		scaleFactor = ((GameBoard) parent).getScaleFactor();
		this.setPreferredSize(new Dimension(board_Width, board_Height));
		this.setMinimumSize(new Dimension(board_Width, board_Height));
		bgImage = initBGImage();
		buildTitleScreen();
		chooseRandomSong();
		setVisible(true);
		}
	
	
	@Override
	public Screens getScreenType(){
		return Screens.TITLE;
	}
	//Initialization of display
	
	/**
	 * Builds various aspects of this TitleScreen's display, such as JLabels and JButtons
	 * @author - Team 8
	 */
	public void buildTitleScreen(){
		BoxLayout col = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(col);
		
		JLabel title = new JLabel("Estuary Survival");
		title.setFont(new Font("Calibri", Font.BOLD, (int) (100*scaleFactor)));
		add(Box.createRigidArea(new Dimension(0,(int)board_Height/5)));
		this.add(title);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton start = new JButton("START");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.setFont(new Font("Calibri", Font.BOLD, (int) scaleFactor*80));
		start.addActionListener(this);
		add(Box.createRigidArea(new Dimension(0,(int)board_Height/8)));
		this.add(start);
		repaint();
	}
	
	/**
	 * Initializes the BG Image associated with this screen type
	 * @author - Team 8
	 * @return- a scaled version of the BG Image associated with this screen type
	 */
	public Image initBGImage(){
		Image img = uploadImage(Screens.TITLE.name());
		return img.getScaledInstance(board_Width, board_Height, Image.SCALE_DEFAULT);
	}
	
	/**
	 * Chooses a random song to play
	 * @author - Team 8
	 */
	public void chooseRandomSong(){
		double rand = Math.random();
		if(rand<.5)
			uploadAndPlaySound("Ride of the Valkyries.wav");
		else 
			uploadAndPlaySound("2001 A Space Odyssey Opening.wav");
	}
	//Override of paintComponent()
	
	/**
	 * Overrides paintComponent from JPanel, in particular it draws a background image.
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

		((GameBoard) parentBoard).changeScreenTo(Screens.MAIN);
	}
	
	/**
	 * Called by a Parent GameBoard after a resize event, updates key variables
	 * @author - Team 8
	 */
	public void receiveBoardInfo(int boardWidth, int boardHeight, double scaleFactor){
		board_Width = boardWidth;
		board_Height = boardHeight;
		this.scaleFactor = scaleFactor;
	}
}
