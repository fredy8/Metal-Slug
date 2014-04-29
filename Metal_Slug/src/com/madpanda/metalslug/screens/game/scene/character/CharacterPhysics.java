package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

/**
 * Defines the physical behavior for a character.
 * Handles collisions with the scene.
 */
public class CharacterPhysics extends MovingBody {

	private static final Vector2 JUMP = new Vector2(0, 250); //The jumping speed
	private static final Vector2 GRAVITY = new Vector2(0, -320); //the gravity vector
	private static final float RUNNING_SPEED = 200; //the gravity vector
	private Scene scene; //The scene that contains the character
	private CollisionHandler collisionHandler; //The collision handler for the character
	
	/**
	 * Created a new CharacterCollision component given the character, the bounds of the character,
	 * the scene in which the character is created, and a collision handler.
	 * @param entity - The character
	 * @param rectangle - The bounds of the character's body
	 * @param scene - The scene that contains the character
	 * @param collisionHandler - The collision handler for the character.
	 */
	public CharacterPhysics(Entity entity, Rectangle rectangle, Scene scene, CollisionHandler collisionHandler) {
		super(entity, rectangle);
		this.scene = scene;
		this.collisionHandler = collisionHandler;
		initPhysics();
	}
	
	//defines the character physics
	private void initPhysics() {
		setSpeed(new Vector2(0, 0));
		setAcceleration(GRAVITY);
	}
	
	/**
	 * Makes the character jump by adding speed in the positive y direction.
	 */
	public void jump() {
		setSpeed(getSpeed().add(JUMP));
	}
	
	/**
	 * Makes the character stand by setting the vertical speed to zero.
	 */
	public void stand() {
		getSpeed().y = 0;
	}
	
	public void runRight() {
		getSpeed().x = RUNNING_SPEED;
	}
	
	public void runLeft() {
		getSpeed().x = -RUNNING_SPEED;
	}
	
	/**
	 * Moves the physical component of the character by the given vector.
	 * Changes the behavior for moving the body of the character so that collisions are checked
	 * before moving and the position is adjusted accordingly. 
	 */
	@Override
	public void move(Vector2 vector) {
		
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
							collisionHandler.rightCollision(scene.getTile(j, i));
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
							collisionHandler.leftCollision(scene.getTile(j, i));
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
							collisionHandler.upCollision(scene.getTile(i, j));
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
							collisionHandler.downCollision(scene.getTile(i, j));
						}
					}
				}
			}
		}
		
		super.move(vector.cpy().scl(0, 1)); //processes vertical movement
	}
	
	/**
	 * Returns the tile coordinate that occupies the given absolute coordinate
	 * @param x - The x component of the coordinate
	 * @param y - The y component of the coordinate
	 * @return - The tile coordinate
	 */
	private int[] getTilePosition(float x, float y) {
		return new int[] { (int) (x/Tile.SIZE), (int) (y / Tile.SIZE) };
	}
	
	/**
	 * Handles what happens when the character collides with a tile.
	 *
	 */
	public static interface CollisionHandler {
		
		/**
		 * The character collides with a tile to its right.
		 * @param tile - The tile that is collides with.
		 */
		public void rightCollision(Tile tile);
		
		/**
		 * The character collides with a tile to its left.
		 * @param tile - The tile that is collides with.
		 */
		public void leftCollision(Tile tile);
		
		/**
		 * The character collides with a tile up.
		 * @param tile - The tile that is collides with.
		 */
		public void upCollision(Tile tile);
		
		/**
		 * The character collides with a tile down.
		 * @param tile - The tile that is collides with.
		 */
		public void downCollision(Tile tile);
		
	}
	
	/**
	 * A base collision handler with different types of collision.
	 * The calls to its methods are obtained from the CollisionHandler interface.
	 *
	 */
	public static abstract class AbstractCollisionHandler implements CollisionHandler {

		@Override
		public final void rightCollision(Tile tile) {
			onRightCollision(tile);
			onXCollision(tile);
			onCollision(tile);
		}

		@Override
		public final void leftCollision(Tile tile) {
			onLeftCollision(tile);
			onXCollision(tile);
			onCollision(tile);
		}

		@Override
		public final void upCollision(Tile tile) {
			onUpCollision(tile);
			onYCollision(tile);
			onCollision(tile);
		}

		@Override
		public final void downCollision(Tile tile) {
			onDownCollision(tile);
			onYCollision(tile);
			onCollision(tile);
		}

		/**
		 * The character collides with a tile to its right.
		 * @param tile - The tile that is collides with.
		 */
		public void onRightCollision(Tile tile) { }
		
		/**
		 * The character collides with a tile to its left.
		 * @param tile - The tile that is collides with.
		 */
		public void onLeftCollision(Tile tile) { }
		
		/**
		 * The character collides with a tile down.
		 * @param tile - The tile that is collides with.
		 */
		public void onDownCollision(Tile tile) { }
		
		/**
		 * The character collides with a tile up.
		 * @param tile - The tile that is collides with.
		 */
		public void onUpCollision(Tile tile) { }
		
		/**
		 * The character collides with a tile either left or right.
		 * @param tile - The tile that is collides with.
		 */
		public void onXCollision(Tile tile) { }
		
		/**
		 * The character collides with a tile either up or down.
		 * @param tile - The tile that is collides with.
		 */
		public void onYCollision(Tile tile) { }
		
		/**
		 * The character collides with a tile.
		 * @param tile - The tile that is collides with.
		 */
		public void onCollision(Tile tile) { }
		
	}
	

}
