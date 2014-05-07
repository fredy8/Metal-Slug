package com.madpanda.metalslug.screens.game.scene.character;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;
import com.madpanda.metalslug.screens.game.scene.Bullet;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

/**
 * A character in the game. Moves freely in the scene colliding with some tiles.
 *
 */
public class Character extends MovingBody {

	private static final float RUNNING_SPEED = 200; //the gravity vector
	private static final float GRAVITY = 400;
	
	private FacingDirection horizontalFacingDirection; //the direction towards which the player is facing horizontally
	private Animation moveAnimation; //the move animation
	private Sprite standingSprite; //the standing texture
	private boolean canShoot;
	private Timer enableShoot = new Timer();
	private int lives;
	private boolean dead;
	private Set<Bullet> bullets;
	private List<Bullet> removeBullets;

	/**
	 * Creates a new character given its bounds and the scene in which it is created.
	 * @param rectangle - The bounds of the character
	 * @param scene - The scene where that character is created.
	 */
	public Character(Rectangle rectangle, int lives) {
		super(rectangle);
		
		setAcceleration(new Vector2(0, -GRAVITY));
		this.lives = lives;
		
		horizontalFacingDirection = FacingDirection.Right;
		canShoot = true;
		
		bullets = new HashSet<>();
		removeBullets = new ArrayList<>();
	}
	
	/**
	 * The possible horizontal facing directions of the character
	 */
	public enum FacingDirection {
		Right, Left
	}

	/**
	 * Makes the player move to the right until stopped.
	 */
	public void moveRight() {
		horizontalFacingDirection = FacingDirection.Right;
		setSpeed(new Vector2(RUNNING_SPEED, getSpeed().y));
	}
	
	/**
	 * Makes the player move to the left until stopped.
	 */
	public void moveLeft() {
		horizontalFacingDirection = FacingDirection.Left;
		setSpeed(new Vector2(-RUNNING_SPEED, getSpeed().y));
	}

	/**
	 * Stops the player horizontal movement.
	 */
	public void stopMovement() {
		setSpeed(new Vector2(0, getSpeed().y));
	}
	
	/**
	 * Shoots a bullet.
	 */
	public void shoot(int speed, float delay) {
		
		if(!canShoot) {
			return;
		}
		
		Rectangle rect = getRectangle();
		
		float posx = 0, posy = 0;
		Vector2 dir = new Vector2();
		
		if(horizontalFacingDirection == FacingDirection.Right) {
			posx = rect.x + rect.width;
			posy = rect.y + rect.height/2;
			dir.x = 1;
		} else {
			posx = rect.x;
			posy = rect.y + rect.height/2;
			dir.x = -1;
		}
		
		Bullet bullet = new Bullet(this, posx, posy, dir.scl(speed), new Texture("images/game/fullHeart.png"));
		bullets.add(bullet);
		
		canShoot = false;
		enableShoot.scheduleTask(new Task() {
			@Override
			public void run() {
				canShoot = true;
			}
		}, delay);
	}
	
	/**
	 * Sets the facing direction of the character.
	 */
	public void setFacingDirection(FacingDirection direction) {
		this.horizontalFacingDirection = direction;
	}

	/**
	 * Hits the character with a bullet.
	 * @param bullet - the bullet that hit the character
	 */
	public void hit(Bullet bullet) {
		bullet.remove();
		lives--;
		if(lives == 0) {
			die();
		}
	}
	
	/**
	 * Kills the character.
	 */
	public void die() {
		Scene.getInstance().removeCharacter(this);
	}
	
	/**
	 * @return whether the character is dead or not.
	 */
	public boolean dead() {
		return dead;
	}
	
	/**
	 * Return the lives left of the character
	 * @return
	 */
	public int getLivesCount() {
		return lives;
	}

	public void render(SpriteBatch batch) {
		for(Bullet bullet : bullets) {
			bullet.render(batch);
		}
	}
	
	/**
	 * Moves the physical component of the character by the given vector.
	 * Changes the behavior for moving the body of the character so that collisions are checked
	 * before moving and the position is adjusted accordingly. 
	 */
	@Override
	public void move(Vector2 vector) {
		
		Scene scene = Scene.getInstance();
		
		if (getSpeed().x > 0) { //moving right
			//checks the next tile obstacle to the right and checks for collision
			int[] lowerRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y);
			int[] upperRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = lowerRight[0]; i < scene.getColumnCount() && !foundObstacle; i++) {
				for(int j = lowerRight[1]; j <= upperRight[1] && !foundObstacle; j++) {
					if(scene.getTile(j, i).isObstacleX()) {
						if(i*Tile.SIZE - getRectangle().x - getRectangle().width < vector.x) {
							//collision found
							vector.x = i*Tile.SIZE - getRectangle().x - getRectangle().width - .001f;
							foundObstacle = true;
							onCollision(1);
						}
					}
				}
			}
		} else if (getSpeed().x < 0) { //moving left
			//checks the next tile obstacle to the left and checks for collision
			int[] lowerLeft = getTilePosition(getRectangle().x, getRectangle().y);
			int[] upperLeft = getTilePosition(getRectangle().x, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = lowerLeft[0]; i >= 0 && !foundObstacle; i--) {
				for(int j = lowerLeft[1]; j <= upperLeft[1] && !foundObstacle; j++) {
					if(scene.getTile(j, i).isObstacleX()) {
						if((i+1)*Tile.SIZE - getRectangle().x > vector.x) {
							//collision found
							foundObstacle = true;
							vector.x = (i+1)*Tile.SIZE - getRectangle().x + .001f;
							onCollision(3);
						}
					}
				}
			}
		}
		
		super.move(vector.cpy().scl(1, 0)); //processes horizontal movement
		
		if (getSpeed().y > 0) { //moving up
			//checks the next tile obstacle up and checks for collision
			int[] upperLeft = getTilePosition(getRectangle().x, getRectangle().y + getRectangle().height);
			int[] upperRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = upperLeft[1]; i < scene.getRowCount() && !foundObstacle; i++) {
				for(int j = upperLeft[0]; j <= upperRight[0] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacleY()) {
						if(i*Tile.SIZE - getRectangle().y - getRectangle().height < vector.y) {
							//collision found
							vector.y = i*Tile.SIZE - getRectangle().y - getRectangle().height - .001f;
							foundObstacle = true;
							getSpeed().y = 0;
							onCollision(0);
						}
					}
				}
			}
		} else if (getSpeed().y < 0) { //moving down
			//checks the next tile obstacle down and checks for collision
			int[] lowerLeft = getTilePosition(getRectangle().x, getRectangle().y);
			int[] lowerRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y);
			boolean foundObstacle = false;
			for(int i = lowerLeft[1]; i >= 0 && !foundObstacle; i--) {
				for(int j = lowerLeft[0]; j <= lowerRight[0] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacleY()) {
						if((i+1)*Tile.SIZE - getRectangle().y > vector.y) {
							//collision found
							vector.y = (i+1)*Tile.SIZE - getRectangle().y + .001f;
							foundObstacle = true;
							getSpeed().y = 0;
							onCollision(2);
						}
					}
				}
			}
		}
		
		super.move(vector.cpy().scl(0, 1)); //processes vertical movement
	}
	
	/**
	 * 
	 * @param direction - 0 for up, 1 for right, 2 for down, 3 for left;
	 */
	protected void onCollision(int direction) { }
	
	private int[] getTilePosition(float x, float y) {
		return new int[] { (int) (x/Tile.SIZE), (int) (y / Tile.SIZE) };
	}
	
	@Override
	public void update() {
		super.update();
		
		for(Bullet bullet : removeBullets) {
			bullets.remove(bullet);
		}
		
		removeBullets.clear();
		
		for(Bullet bullet : bullets) {
			bullet.update();
		}
	}

	public void removeBullet(Bullet bullet) {
		removeBullets.add(bullet);
	}
	
}
