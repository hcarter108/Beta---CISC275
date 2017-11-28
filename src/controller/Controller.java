package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Timer;

import model.Game;
import model.GameTimer;
import view.LevelScreen;
import view.ScreenPanel.Screens;

public class Controller {

	// Fields

	private LevelScreen currentGameView;

	private int timeDelay = 40;
	private int seconds = 0;
	private int counter = 0;
	private int time2Run = 100;
	private boolean hasRunThisSecond;

	private Game currentGame;
	private Timer timer;
	private GameTimer gt;

	// Constructor
	/**
	 * Constructor for Controller
	 * @author - Team 8
	 * @param delay
	 *            - The delay to be used by the internal Timer
	 * @param currentGameView
	 *            - The current instance of LevelScreen on which this controller
	 *            will act
	 */
	public Controller(int delay, LevelScreen currentGameView) {
		timeDelay = delay;
		this.currentGameView = currentGameView;
		currentGame = currentGameView.getGame();
		timer = new Timer(timeDelay, gameAction);
		gt = currentGame.getGameTimer();
		time2Run = gt.getMaxTime();
	}

	ActionListener gameAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			if (currentGameView.getScreenType().equals(Screens.TUTORIAL)) {
				if (!hasRunThisSecond) {
					currentGameView.runTutorialMessage(seconds);
					hasRunThisSecond = true;
				}
				Point mousePoint = currentGameView.getMousePosition();
				if (mousePoint != null)
					currentGame.movePlayer(mousePoint);

				currentGame.moveEnemies();

				currentGameView.refreshView();

				counter += (1000 / timeDelay);
				if (Math.floorMod(counter, 1000) == 0) {
					hasRunThisSecond = false;
					gt.setTimeRemaining(time2Run - (++seconds));
				}
			}

			else {
				Point mousePoint = currentGameView.getMousePosition();
				if (mousePoint != null)
					currentGame.movePlayer(mousePoint);

				currentGame.moveEnemies();

				currentGameView.refreshView();

				counter += (1000 / timeDelay);
				if (Math.floorMod(counter, 1000) == 0)
					gt.setTimeRemaining(time2Run - (++seconds));
				if (seconds >= time2Run)
					currentGame.hasWon();
			}
		}
	};

	/**
	 * Starts the internal Timer
	 * @author - Team 8
	 */
	public void tick() {
		timer.start();
	}

	/**
	 * Pauses the internal Timer, using Timer's stop() method
	 * @author - Team 8
	 */
	public void pause() {
		timer.stop();
	}

}
