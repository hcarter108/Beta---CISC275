package model;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.MoveableObject.MoveableType;
import view.LevelScreen;
import view.ScreenPanel.Screens;
import view.GameBoard;

public class Game {

	//Fields
	
	public enum DifficultyLVL {EASY, MEDIUM, HARD, TUTORIAL};
	private Screens currentScreenType;

	private JPanel currentPanel;
    
	private int numEnemies;
	private int gameObjSpeed;
    private int windowWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int windowHeight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private int playerCollisionRad;
	private int numInvasiveEnemy;
	private int numPollutionEnemy;
	private boolean notQ=true;
	private double scaleFactor = 1;
    
	private Player player;
	private Enemy[] listEnemies;
    private HealthBar hb;
    private GameTimer gt;
    private ArrayList<Coordinates> randomSpawnFrame = initEnemySpawnFrame(new Coordinates(-50, -50), 

    		new Coordinates(windowWidth + 50, windowHeight + 50));;
	

    //Constructor
    
    /**
     * Constructor for Game
     * @author - Team 8
     * @param screenType - The screen type that this game is using
     * @param d - the difficulty of this game, from enum DifficultyLVL
     */
	public Game(Screens screenType, DifficultyLVL d){
		super();
		currentScreenType = screenType;
		initGameVariables(d);
		numInvasiveEnemy = (int) (.5*numEnemies);
		numPollutionEnemy = numEnemies-numInvasiveEnemy;
		listEnemies = new Enemy[numEnemies];
        initListEnemies();
        player = new Player(new Coordinates(windowWidth/2, windowHeight/2), currentScreenType);
        playerCollisionRad = player.getPlayerDiameter()/2;
	}
	
	// Getters and Setters
	
	/**
	 * Getter for whether or not a question is currently being asked
	 * @author - Team 8
	 * @return - the boolean value of whether or not a question is being asked
	 */
	public boolean isNotQ() {
		return notQ;
	}

	/**
	 * Setter for whether or not a question is being asked
	 * @author - Team 8
	 * @param notQ - a new boolean value for whether or not a question is being asked
	 */
	public void setNotQ(boolean notQ) {
		this.notQ = notQ;
	}

	/**
	 * Get the number of enemies in this Game instance
	 * @author - Team 8
	 * @return - the number of enemies in this Game instance
	 */
	public int getNumEnemies() {
		return numEnemies;
	}
	
	/**
	 * Returns the GameTimer associated with this Game instance
	 * @author - Team 8
	 * @return - returns the GameTimer associated with this Game instance
	 */
	public GameTimer getGameTimer(){
		return gt;
	}
	
	/**
	 * Gets the number of invasive enemies in this Game instance
	 * @author - Team 8
	 * @return - the number of invasive enemies in this game instance
	 */
	public int getNumInvasive(){
		return numInvasiveEnemy;
	}
	
	/**
	 * Gets the number of pollution enemies in this Game instance
	 * @author - Team 8
	 * @return - the number of pollution enemies in this Game instance
	 */
	public int getNumPollution(){
		return numPollutionEnemy;
	}
	
	/**
	 * Sets the number of enemies in this Game instance
	 * @author - Team 8
	 * @param numEnemies - the number of enemies for this Game instance
	 */
	public void setNumEnemies(int numEnemies) {
		this.numEnemies = numEnemies;
	}

	/**
	 * Gets the speed of the in-game objects
	 * @author - Team 8
	 * @return - the speed of the in-game objects
	 */
	public int getGameObjSpeed() {
		return gameObjSpeed;
	}

	/**
	 * Sets the speed of the in-game objects
	 * @author - Team 8
	 * @param enemySpeed - the speed for the in-game objects
	 */
	public void setGameObjSpeed(int enemySpeed) {
		this.gameObjSpeed = enemySpeed;
	}
	
	/**Sets the scale factor for this game
	 * @author - Team 8
	 * @param scaleFactor - the value for the new scale factor
	 */
	public void setScaleFactor(double scaleFactor){
		this.scaleFactor = scaleFactor;
	}
	
	/**
	 * Gets the scale factor for this game
	 * @author - Team 8
	 * @return- the scale factor for this game
	 */
	public double getScaleFactor(){
		return scaleFactor;
	}
	
	/**
	 * Sets game variables width, height, and scale factor to new values, generally used in resizing event
	 * @author - Team 8
	 * @param width - the new width
	 * @param height- the new height
	 * @param scale- the new scale factor
	 */
	public void setBoardInfo(int width, int height, double scale){
		windowWidth = width;
		windowHeight = height;
		scaleFactor = scale;
		randomSpawnFrame = initEnemySpawnFrame(new Coordinates(-50, -50), 
	    		new Coordinates(windowWidth + 50, windowHeight + 50));
		player.setScaleFactor(scaleFactor);
	}
	
	/**
	 * Gets the list of enemies in this game
	 * @author - Team 8
	 * @return - the list of enemies in this game
	 */
    public Enemy[] getListEnemies() {
        return listEnemies;
    }
    
    /**
     * Gets the player in this game
     * @author - Team 8
     * @return - the Player in this game
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the HealthBar from this game
     * @author - Team 8
     * @return - the HealthBar from this game
     */
	public HealthBar getHealthBar() {
		return hb;
	}
	
	/**
	 * Getter for the x position of the start coordinates
	 * 
	 * @author - Team 8
	 * @return - The x position of the start coordinates
	 */
	public double getSpawnXBeginning() {
		return randomSpawnFrame.get(0).getxPos();
	}

	/**
	 * Getter for the x position of the end coordinate
	 * 
	 * @author - Team 8
	 * @return - The x position of the end coordinate
	 */
	public double getSpawnXEnding() {
		return randomSpawnFrame.get(1).getxPos();
	}

	/**
	 * Getter for the y position of the start coordinate
	 * 
	 * @author - Team 8
	 * @return - The y position of the end coordinate
	 */
	public double getSpawnYBeginning() {
		return randomSpawnFrame.get(0).getyPos();
	}
	
	/**
	 * Getter for the y position of the end coordinate
	 * 
	 * @author - Team 8
	 * @return - The y position of the end coordinate
	 */
	public double getSpawnYEnding() {
		return randomSpawnFrame.get(1).getyPos();
	}
	
	//Initialize Game Variables

	/**
	 * Initializes the game variables, internally considers the current LevelScreen and weights 
	 * that additionally based on DifficultyLVL
	 * @author - Team 8
	 * @param d - the difficulty level for this game, from enum DifficultyLVL
	 */
	public void initGameVariables(DifficultyLVL d){
		double difficultyWeight;
	
		switch(d){
		case EASY:{
			difficultyWeight = 1.0;
			break;
		}
		case MEDIUM:{
			difficultyWeight = 1.2;
			break;
		}
		case HARD:{
			difficultyWeight = 1.4;
			break;
		}
		default:
			difficultyWeight = 1.0;
		}
	
		switch(currentScreenType){
		case L1:{
			numEnemies = (int) (difficultyWeight*15);
			gameObjSpeed = (int) (difficultyWeight*5);
			gt = new GameTimer(20);
			hb = new HealthBar(200);
			break;
		}
		case L2:{
			numEnemies = (int) (difficultyWeight*30);
			gameObjSpeed = (int) (difficultyWeight*6);
			gt = new GameTimer(25);
			hb = new HealthBar(200);
			break;
		}
		case L3:{
			numEnemies = (int) (difficultyWeight*20);
			gameObjSpeed = (int) (difficultyWeight*7);
			gt = new GameTimer(30);
			hb = new HealthBar(200);
			break;
		}
		case L4:{
			numEnemies = (int) (difficultyWeight*20);
			gameObjSpeed = (int) (difficultyWeight*8);
			gt = new GameTimer(35);
			hb = new HealthBar(200);
			break;
		}
		case TUTORIAL:{
			numEnemies = 20;
			gameObjSpeed = 5;
			gt = new GameTimer(15);
			hb = new HealthBar(1000);
			break;
		}
		
		}	
	}
	
	/**
	 * Sets the JPanel for this game, this will only be called if there is a view associated with this game, else the games runs only logically
	 * @author - Team 8
	 * @param panel- the JPanel associated with this game
	 */
	public void setPanelForCommunication(JPanel panel){
        currentPanel = panel;
	}
	
	/**
	 * Initializes the list of enemies, making some invasive and some pollution
	 * @author - Team 8
	 */
    private void initListEnemies() {
        for(int i = 0; i<numInvasiveEnemy; i++){
            Coordinates spawnCoordinates = randomizeCoordinates();
            MovingVector movingVector = randomizeMovingVector();
            Enemy anEnemy = new Enemy(spawnCoordinates, movingVector, currentScreenType, MoveableType.INVASIVE);
            listEnemies[i] = anEnemy;
        }
        for(int i = 0; i< numPollutionEnemy; i++){
            Coordinates spawnCoordinates = randomizeCoordinates();
            MovingVector movingVector = randomizeMovingVector();
            Enemy anEnemy = new Enemy(spawnCoordinates, movingVector, currentScreenType, MoveableType.POLLUTION);
            listEnemies[numInvasiveEnemy+i] = anEnemy;
        }
    }
    
    /**
     * Initializes an enemy spawn frame, or the locations from which enemies can spawn
     * @author - Team 8
     * @param start - the coordinate of the start position
     * @param end - the coordinate of the end position
     * @return - an ArrayList of Coordinates that contain the bounds for the enemy spawn frame
     */
    private ArrayList<Coordinates> initEnemySpawnFrame(Coordinates start, Coordinates end){
 	   ArrayList<Coordinates> randomSpawnFrame = new ArrayList<Coordinates>();
 	   randomSpawnFrame.add(start);
 	   randomSpawnFrame.add(end);
 	   return randomSpawnFrame;
    }

    // Movement logic, also checks for wins and losses
	
    /**
     * Updates the player information using the point that the mouse is hovering on
     * @author - Team 8
     * @param mousePoint - the Point that the mouse cursor is currently on
     */
    public void movePlayer(Point mousePoint) {

        if(mousePoint.x > player.getCoordinates().getxPos() - 5 && mousePoint.x < player.getCoordinates().getxPos() + 5
                && mousePoint.y > player.getCoordinates().getyPos() - 5 && mousePoint.y < player.getCoordinates().getyPos() + 5) return;

        float angle = (float) Math.toDegrees(Math.atan2(mousePoint.y - player.getCoordinates().getyPos(),
                mousePoint.x - player.getCoordinates().getxPos()));

        if(angle < 0){
            angle += 360;
        }
        
        player.setDirectionAngle(angle);

        double x = (gameObjSpeed * scaleFactor *  Math.cos(Math.toRadians(angle)));
        double y = (gameObjSpeed * scaleFactor *  Math.sin(Math.toRadians(angle)));

        player.moveByVector(new MovingVector(x, y));

    }
    
    /**
     * Moves the enemies to a new location, checks for collisions and handles in-game death and 
     * or victory as well, based on either zero health or zero time, respectively. Also, if the enemy 
     * has moved outside the frame or if it collided with the player, it is replaced by a new
     * enemy instance
     * @author - Team 8
     */
    public void moveEnemies() {

        for(int i = 0; i< listEnemies.length; i++) {
            Coordinates ballCoordinates = listEnemies[i].getCoordinates();
            if(ballCoordinates.getxPos() < getSpawnXBeginning()
                    || ballCoordinates.getxPos() > getSpawnXEnding()
                    || ballCoordinates.getyPos() > getSpawnYEnding()
                    || ballCoordinates.getyPos() < getSpawnYBeginning() ) {
                Coordinates newBallCoordinates = randomizeCoordinates();
                MovingVector movingVector = randomizeMovingVector();
                Enemy anEnemy = new Enemy(newBallCoordinates, movingVector, 
                		currentScreenType, listEnemies[i].getEnemyType());
                listEnemies[i] = anEnemy;
            }

            // uses view
            
            if(checkForCollision(listEnemies[i])) {
                int currentHealthPoints = getHealthBar().getCurrentPoints();
                getHealthBar().setCurrentPoints(currentHealthPoints - 30);
                if(getHealthBar().getCurrentPoints()<=0 && notQ) {
                	((LevelScreen) currentPanel).pause();
                	notQ=false;
                	if((((LevelScreen) currentPanel).getNumQsRemaining()-1)<0)
                		((LevelScreen) currentPanel).endGame();
                	else
                		((LevelScreen) currentPanel).runDeathScenario();
                		
                }

                Coordinates newBallCoordinates = randomizeCoordinates();
                MovingVector movingVector = randomizeMovingVector();
                listEnemies[i] = new Enemy(newBallCoordinates, movingVector, 
                		currentScreenType, listEnemies[i].getEnemyType());
            }

            listEnemies[i].moveByVector();
        }

    }
    
    /**
     * Randomizes the start coordinates for a new enemy instance
     * @author - Team 8
     * @return - a new Coordinates object to be used in instantiating an Enemy
     */
    private Coordinates randomizeCoordinates() {
        
        Double random = Math.random();
        
        Coordinates randomizedCoordinates;
        
        int xPos;
        int yPos;

        if(random < 0.25) {
            xPos = (int) getSpawnXBeginning();
            yPos = (int)(Math.random()*getSpawnYEnding());
        } else if(random >= 0.25 && random < 0.50) {
            xPos = (int)(Math.random()*getSpawnXEnding());
            yPos = (int) getSpawnYBeginning();
        } else if(random >= 0.50 && random < 0.75) {
            xPos = (int) getSpawnXEnding();
            yPos = (int) (Math.random()*getSpawnYEnding());
        } else {
            xPos = (int) (Math.random()*getSpawnXEnding());
            yPos = (int) getSpawnYEnding();
        }

        randomizedCoordinates = new Coordinates(xPos, yPos);
        
        return randomizedCoordinates;
        
    }
    
    /**
     * Creates a randomized MovingVector to be used in the instantiation of an enemy
     * @author - Team 8
     * @return - a MovingVector object to be used in the instantiation of an enemy
     */
    private MovingVector randomizeMovingVector() {
        double angle = Math.random() * 360;
        double x = (gameObjSpeed * scaleFactor * Math.cos(Math.toRadians(angle)));
        double y = (gameObjSpeed * scaleFactor * Math.sin(Math.toRadians(angle)));

        MovingVector movingVector = new MovingVector(x, y);
        return movingVector;
    }
    
    /**
     * Checks for a collision between the player and an enemy
     * @author - Team 8
     * @param anEnemy - an instance of Enemy
     * @return - the boolean value of whether or not a collision occurred
     */
    private boolean checkForCollision(Enemy anEnemy) {

        double enemyXCoordinates = anEnemy.getCoordinates().getxPos();
        double enemyYCoordinates = anEnemy.getCoordinates().getyPos();
        
       int enemyCollisionRad = (int) anEnemy.getEnemyDiameter()/2;

        if (Math.sqrt(Math.pow(player.getCoordinates().getxPos() - enemyXCoordinates, 2) 
        		+ Math.pow(player.getCoordinates().getyPos() - enemyYCoordinates, 2)) 
        		< playerCollisionRad + enemyCollisionRad) {
            return true;
        }
        return false;

    }
    
    // Handle winning state
    
    // uses view
    
    /**
     * Handles the case that the GameTimer has reached zero, and changes the screen to the next
     * unless it was the last level, in which case it changes the screen to the main menu
     * @author - Team 8
     */
	public void hasWon(){
		if(currentPanel!=null)
		((LevelScreen) currentPanel).hasWon();
		else
			System.out.println("You have won the game");
	}
	
}
