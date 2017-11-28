package model;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Enemy.EnemyType;
import view.LevelScreen;
import view.ScreenPanel.Screens;
import view.GameBoard;

public class Game {

	//Fields
	
	public enum DifficultyLVL {EASY, MEDIUM, HARD, TUTORIAL};
	private Screens currentScreenType;
	
    private JFrame currentGameView;
    private LevelScreen currentLevelScreen;
    
	private int numEnemies;
	private int gameObjSpeed;
    private int windowWidth = 800;
    private int windowHeight = 600;
    private int playerCollisionRad;
    private int enemyCollisionRad;
	private int numInvasiveEnemy;
	private int numPollutionEnemy;
	private boolean notQ=true;
    
	private Player player;
	private Enemy[] listEnemies;
    private HealthBar hb;
    private GameTimer gt;
    private final EnemySpawnFrame enemySpawnFrame = new EnemySpawnFrame(new Coordinates(-50, -50), 
    		new Coordinates(windowWidth + 50, windowHeight + 50));
	

    //Constructor
    
    /**
     * Constructor for Game
     * @author - Team 8
     * @param boardView - the current GameBoard instance
     * @param panel - the current Level, an instance of LevelScreen
     * @param d - the difficulty of this game, from enum DifficultyLVL
     */
	public Game(JFrame boardView, LevelScreen panel, DifficultyLVL d){
		super();
		currentLevelScreen = panel;
		currentScreenType = panel.getScreenType();
		this.currentGameView = boardView;
		playerCollisionRad = panel.getPlayerDiameter()/2;
		enemyCollisionRad = panel.getEnemyDiameter()/2;
		initGameVariables(d);
		numInvasiveEnemy = (int) (.5*numEnemies);
		numPollutionEnemy = numEnemies-numInvasiveEnemy;
		listEnemies = new Enemy[numEnemies];
        player = new Player(new Coordinates(windowWidth/2, windowHeight/2), panel.getPlayerDiameter());
        initListEnemies();
		
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
	 * Initializes the list of enemies, making some invasive and some pollution
	 * @author - Team 8
	 */
    private void initListEnemies() {
        for(int i = 0; i<numInvasiveEnemy; i++){
            Coordinates ballCoordinates = randomizeCoordinates();
            MovingVector movingVector = randomizeMovingVector();
            Enemy anEnemy = new Enemy(ballCoordinates, movingVector, 2*enemyCollisionRad, EnemyType.INVASIVE);
            listEnemies[i] = anEnemy;
        }
        for(int i = 0; i< numPollutionEnemy; i++){
            Coordinates ballCoordinates = randomizeCoordinates();
            MovingVector movingVector = randomizeMovingVector();
            Enemy anEnemy = new Enemy(ballCoordinates, movingVector, 2*enemyCollisionRad, EnemyType.POLLUTION);
            listEnemies[numInvasiveEnemy+i] = anEnemy;
        }
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

        double x = (gameObjSpeed * Math.cos(Math.toRadians(angle)));
        double y = (gameObjSpeed * Math.sin(Math.toRadians(angle)));

        player.moveByVector(new MovingVector(x, y));

    }
    
    /**
     * Moves the enemies to a new location, checks for collisions and handles in-game death and 
     * or victory as well, based on either zero health or zero time, respectiely. Also, if the enemy 
     * has moved outside the frame or if it collided with the player, it is replaced by a new
     * enemy instance
     * 
     */
    public void moveEnemies() {

        for(int i = 0; i< listEnemies.length; i++) {
            Coordinates ballCoordinates = listEnemies[i].getCoordinates();
            if(ballCoordinates.getxPos() < enemySpawnFrame.getXBeginning()
                    || ballCoordinates.getxPos() > enemySpawnFrame.getXEnding()
                    || ballCoordinates.getyPos() > enemySpawnFrame.getYEnding()
                    || ballCoordinates.getyPos() < enemySpawnFrame.getYBeginning() ) {
                Coordinates newBallCoordinates = randomizeCoordinates();
                MovingVector movingVector = randomizeMovingVector();
                Enemy anEnemy = new Enemy(newBallCoordinates, movingVector, 
                		2*enemyCollisionRad, listEnemies[i].getEnemyType());
                listEnemies[i] = anEnemy;
            }

            if(checkForCollision(listEnemies[i])) {
                int currentHealthPoints = getHealthBar().getCurrentPoints();
                getHealthBar().setCurrentPoints(currentHealthPoints - 30);
                if(getHealthBar().getCurrentPoints()<=0 && notQ) {
                	currentLevelScreen.pause();
                	notQ=false;
                	if((currentLevelScreen.getNumQsRemaining()-1)<0)
                		currentLevelScreen.endGame();
                	else
                		currentLevelScreen.runDeathScenario();
                		
                }

                Coordinates newBallCoordinates = randomizeCoordinates();
                MovingVector movingVector = randomizeMovingVector();
                listEnemies[i] = new Enemy(newBallCoordinates, movingVector, 
                		2*enemyCollisionRad, listEnemies[i].getEnemyType());
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
            xPos = (int) enemySpawnFrame.getXBeginning();
            yPos = (int)(Math.random()*enemySpawnFrame.getYEnding());
        } else if(random >= 0.25 && random < 0.50) {
            xPos = (int)(Math.random()*enemySpawnFrame.getXEnding());
            yPos = (int) enemySpawnFrame.getYBeginning();
        } else if(random >= 0.50 && random < 0.75) {
            xPos = (int) enemySpawnFrame.getXEnding();
            yPos = (int) (Math.random()*enemySpawnFrame.getYEnding());
        } else {
            xPos = (int) (Math.random()*enemySpawnFrame.getXEnding());
            yPos = (int) enemySpawnFrame.getYEnding();
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
        double x = (gameObjSpeed * Math.cos(Math.toRadians(angle)));
        double y = (gameObjSpeed * Math.sin(Math.toRadians(angle)));

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

        if(player.getCoordinates().getxPos() - playerCollisionRad < enemyXCoordinates + enemyCollisionRad
                && enemyXCoordinates - enemyCollisionRad < player.getCoordinates().getxPos() + playerCollisionRad
                && player.getCoordinates().getyPos() - playerCollisionRad < enemyYCoordinates + enemyCollisionRad
                && player.getCoordinates().getyPos() + playerCollisionRad > enemyYCoordinates - enemyCollisionRad) {
            return true;
        }
        return false;

    }
    
    // Handle winning state
    
    /**
     * Handles the case that the GameTimer has reached zero, and changes the screen to the next
     * unless it was the last level, in which case it changes the screen to the main menu
     * @author - Team 8
     */
	public void hasWon(){
		currentLevelScreen.pause();
		if(!currentScreenType.equals(Screens.L4))
		{
		int choice = JOptionPane.showConfirmDialog(currentLevelScreen, "You've survived the level! Would you like to continue to the next level?", 
		"You've won!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
		if(choice==JOptionPane.YES_OPTION){
			switch(currentScreenType){
			case L1:{
				((GameBoard) currentGameView).changeScreenTo(Screens.L2Pre);
				break;
			}
			case L2:{
				((GameBoard) currentGameView).changeScreenTo(Screens.L3Pre);
				break;
			}
			case L4:{
				((GameBoard) currentGameView).changeScreenTo(Screens.L4Pre);
				break;}
			default:
				break;
			}
		}
		else
			((GameBoard) currentGameView).changeScreenTo(Screens.MAIN);
		}
		else{
			JOptionPane.showMessageDialog(currentLevelScreen, "You've completed the last level, "
					+ "press ok to continue to the main menu!");
			((GameBoard) currentGameView).changeScreenTo(Screens.MAIN);
		}
	}
	
	
}
