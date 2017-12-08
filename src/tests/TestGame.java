package tests;

import java.awt.Point;
import java.awt.Toolkit;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Game;
import model.Game.DifficultyLVL;
import view.LevelScreen;
import view.ScreenPanel.Screens;

public class TestGame {
	
	private static Game testGame = new Game(Screens.L1, DifficultyLVL.EASY);

	@Test
	public void testMovePlayer(){
		int x = (int) testGame.getPlayer().getCoordinates().getxPos();
		testGame.movePlayer(new Point(10,10));
		assert(x!=(int)testGame.getPlayer().getCoordinates().getxPos());
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
		testGame.setBoardInfo(100, 100, 2.0);
		assert(testGame.getSpawnXEnding()==150);
		assert(testGame.getSpawnYEnding()==150);
		assert(testGame.getScaleFactor()==2.0);
		
	}
}
