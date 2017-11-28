package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import model.Coordinates;
import model.Enemy;
import model.MovingVector;

public class EnemyView {

	// Fields

	private final int enemyDiameter;

	private Coordinates coordinates;
	private final MovingVector movingVector;
	private Image enemyGraphic;
	private Enemy enemyData; // In case we distinguish between enemy types for
								// enemyDiameter, in particular

	// Constructor

	/**
	 * Constructor for EnemyView. This is used to move an Enemy instance on the
	 * screen
	 * 
	 * @author - Team 8
	 * @param enemy
	 *            - the Enemy instance being represented
	 * @param enemyGraphic
	 *            - the image to be drawn representing this Enemy
	 */
	public EnemyView(Enemy enemy, Image enemyGraphic) {
		enemyData = enemy;
		this.enemyGraphic = enemyGraphic;
		this.coordinates = enemy.getCoordinates();
		this.movingVector = enemy.getMovingVector();
		this.enemyDiameter = enemy.getEnemyDiameter();
	}

	// A method for displaying

	/**
	 * Draws the Enemy on-screen
	 * 
	 * @author - Team 8
	 * @param g
	 *            - an instance of Graphics used internally by Java
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance((coordinates.getxPos() - enemyDiameter / 2),
				(coordinates.getyPos() - enemyDiameter / 2));
		at.rotate(Math.toRadians(enemyData.getMovingAngle() + 90));
		g2.drawImage(enemyGraphic, at, null);
	}

}
