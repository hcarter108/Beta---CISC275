package tests;

import java.awt.Point;

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
}
