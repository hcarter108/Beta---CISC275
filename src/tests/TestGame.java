package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.awt.Toolkit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Coordinates;
import model.Enemy;
import model.Game;
import model.Game.DifficultyLVL;
import model.MoveableObject.MoveableType;
import model.MovingVector;
import view.LevelScreen;
import view.ScreenPanel.Screens;

public class TestGame {
	
	// the things not tested are essentially getters which literally just return fields and a couple randomization functions, difficult to test accurately
	
	private static Game testGame;
	
	// needs to be reinitialized each time so as to avoid changing it between tests
	@Before
	public void setUpGame(){
		testGame = new Game(Screens.L1, DifficultyLVL.EASY);
	}
	
	//This assumes the arbitrary values for initialization haven't been changed. This assumes L1 and EASY
	@Test
	public void testGameInitialization(){
		assert(testGame.getNumEnemies()==15);
		assert(testGame.getGameObjSpeed()==5);
		assert(testGame.getGameTimer().getMaxTime()==20);
		assert(testGame.getHealthBar().getMaxPoints()==200);
		assert(testGame.getListEnemies().length==15);
		assert(testGame.getNumInvasive()==(int)(.5*15));
		assert(testGame.getNumPollution()==(int)(15-(int)(.5*15)));
	}
	
	// I used the enemySpawnFrame's getters since we don't have getters for window width/height, the +50 is used to initialize the spawn frame
	@Test
	public void testResizeChangesVariables(){
		assert(testGame.getSpawnXEnding()==(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()+50);
		assert(testGame.getSpawnYEnding()==(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()+50);
		// the following would be called on a resize event, we just put in arbitrary values
		testGame.setBoardInfo(100, 100, 2.0);
		assert(testGame.getSpawnXEnding()==150);
		assert(testGame.getSpawnYEnding()==150);
		assert(testGame.getScaleFactor()==2.0);
		
	}
	
	// Given all the casts between the various layers, and the fact we use more of a polar coordinate-like movement, we provided a large epsilon for assertEquals
	@Test
	public void testMovePlayer(){
		testGame.setBoardInfo(1000, 1000, 1.0);
		Coordinates initial = testGame.getPlayer().getCoordinates();
		double x = initial.getxPos();
		double y = initial.getyPos();
		testGame.movePlayer(new Point((int)x+5,(int)y+5));
		Coordinates finalC = testGame.getPlayer().getCoordinates();
		assertEquals(finalC.getxPos(),x+5, 3.0);
		assertEquals(finalC.getyPos(),y+5, 3.0);
	}
	
	// This is difficult to test with great accuracy, since everything is randomized, so we just test that the positions change
	// However, since everything is randomized, there is a very small chance that an enemy that went off screen was respawned in the same
	//exact spot it was before the move was called
	@Test
	public void testMoveEnemies(){
		int[] xPos = new int[15];
		int[] yPos = new int[15];
		Enemy[] enemies = testGame.getListEnemies();
		for (int i=0; i<15;i++){
			xPos[i]=(int) enemies[i].getCoordinates().getxPos();
			yPos[i]=(int) enemies[i].getCoordinates().getyPos();
		}
		testGame.moveEnemies();
		for(int i =0; i<15; i++){
			assert(enemies[i].getCoordinates().getxPos()!=xPos[i]);
			assert(enemies[i].getCoordinates().getyPos()!=yPos[i]);
		}
	}
	
	// This test was run somewhat independently of the game, but tests the function contained within the Game class
	@Test
	public void checkCollisionCases(){
		Coordinates playerC = testGame.getPlayer().getCoordinates();
		Enemy eTrue = new Enemy(playerC, new MovingVector(1.0,1.0), Screens.L1, MoveableType.INVASIVE);
		assert(testGame.checkForCollision(eTrue));
		Enemy eFalse = new Enemy(new Coordinates(0,0), new MovingVector(1.0,1.0), Screens.L1, MoveableType.INVASIVE);
		assert(!testGame.checkForCollision(eFalse));
	}
}
