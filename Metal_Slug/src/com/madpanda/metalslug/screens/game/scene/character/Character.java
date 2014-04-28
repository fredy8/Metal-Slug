package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.graphical.AnimationRender;
import com.madpanda.metalslug.screens.game.components.graphical.TextureRender;
import com.madpanda.metalslug.screens.game.components.physical.Body;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;
import com.madpanda.metalslug.screens.game.scene.Bullet;
import com.madpanda.metalslug.screens.game.scene.Scene;

/**
 * A character in the game. Moves freely in the scene colliding with some tiles.
 *
 */
public class Character extends Entity {

	private static final float SHOOT_DELAY = .15f;
	
	private MovementState movementState; //the state in which the character is
	private HorizontalFacingDirection horizontalFacingDirection; //the direction towards which the player is facing horizontally
	private VerticalFacingDirection verticalFacingDirection; //the direction towards which the player is facing vertically
	private AnimationRender moveAnimation; //the move animation
	private TextureRender standingTexture; //the standing texture
	private boolean canShoot;
	private Timer enableShoot = new Timer();
	private int healthPoints;
		
	/**
	 * Creates a new character given its bounds and the scene in which it is created.
	 * @param rectangle - The bounds of the character
	 * @param scene - The scene where that character is created.
	 */
	public Character(Rectangle rectangle, Scene scene, int healthPoints) {
		//set its physical component to the character physics component
		this.setUpdateComponent(new CharacterPhysics(this, rectangle, scene, new CharacterCollisionHandler(this)));
		
		this.healthPoints = healthPoints;
		
		//by default, its state is jumping as it can be created in midair, its state will change to standing if it touches the floor.
		movementState = MovementState.Jumping;
		horizontalFacingDirection = HorizontalFacingDirection.Right;
		verticalFacingDirection = VerticalFacingDirection.None;
		canShoot = true;
	}
	
	/**
	 * Makes the character jump.
	 * Can only jump when standing.
	 */
	public void jump() {
		if(movementState == MovementState.Standing) {
			getPhysics().jump();
			movementState = MovementState.Jumping; //set the state to jumping
			standingTexture.setFlipX(horizontalFacingDirection == HorizontalFacingDirection.Left);
			setGraphicalComponent(standingTexture);
		}
	}
	
	/**
	 * Makes the character stand.
	 * Can only stand when in jumping state or when crouching.
	 */
	public void stand() {
		if(movementState == MovementState.Jumping) {
			getPhysics().stand();
			movementState = MovementState.Standing;
			if(Math.abs(getPhysics().getSpeed().x) > 0) {
				moveAnimation.setFlipX(horizontalFacingDirection == HorizontalFacingDirection.Left);
				setGraphicalComponent(moveAnimation);
			}
		} else if(movementState == MovementState.Crouching) {
			Body body = (Body) getPhysicalComponent();
			//stop crouching.
			body.getRectangle().setHeight(body.getRectangle().getHeight()*2);
			movementState = MovementState.Standing;
		}
	}
	
	/**
	 * Makes the player crouch.
	 * Can only crouch when standing.
	 */
	public void crouch() {
		if(movementState == MovementState.Standing) {
			Body body = (Body) getPhysicalComponent();
			//Makes the character crouch
			body.getRectangle().setHeight(body.getRectangle().getHeight()/2);
			movementState = MovementState.Crouching;
		}
	}

	/**
	 * The possible movement states of the character.
	 *
	 */
	public enum MovementState {
		Standing, Jumping, Crouching;
	}
	
	/**
	 * The possible horizontal facing directions of the character
	 */
	public enum HorizontalFacingDirection {
		Right, Left
	}
	
	/**
	 * The possible vertical facing directions of the character
	 */
	public enum VerticalFacingDirection {
		Up, Down, None
	}
	
	public void lookUp() {
		if(verticalFacingDirection == VerticalFacingDirection.None) {
			verticalFacingDirection = VerticalFacingDirection.Up;
		}
	}
	
	public void lookDown() {
		if(verticalFacingDirection == VerticalFacingDirection.None) {
			verticalFacingDirection = VerticalFacingDirection.Down;
		}
	}
	
	public void stopLook() {
		if(verticalFacingDirection != VerticalFacingDirection.None) {
			verticalFacingDirection = VerticalFacingDirection.None;
		}
	}
	
	/**
	 * Returns the current state of the character
	 * @return - The current state of the character
	 */
	public MovementState getState() {
		return movementState;
	}

	/**
	 * Makes the player move to the right until stopped.
	 */
	public void moveRight() {
		if(movementState == MovementState.Standing) {
			moveAnimation.setFlipX(false);
			setGraphicalComponent(moveAnimation);
		}
		horizontalFacingDirection = HorizontalFacingDirection.Right;
		standingTexture.setFlipX(false);
		getPhysics().runRight();
	}
	
	/**
	 * Makes the player move to the left until stopped.
	 */
	public void moveLeft() {
		if(movementState == MovementState.Standing) {
			moveAnimation.setFlipX(true);
			setGraphicalComponent(moveAnimation);
		}
		horizontalFacingDirection = HorizontalFacingDirection.Left;
		standingTexture.setFlipX(true);
		getPhysics().runLeft();
	}

	/**
	 * Stops the player horizontal movement.
	 */
	public void stopMovement() {
		standingTexture.setFlipX(horizontalFacingDirection == HorizontalFacingDirection.Left);
		setGraphicalComponent(standingTexture);
		MovingBody body = (MovingBody) getPhysicalComponent();
		body.getSpeed().x = 0;
	}

	/**
	 * Sets the standing texture to a texture graphical component
	 * @param animationRender - The standing texture
	 */
	public void setStandingTexture(TextureRender textureRender) {
		standingTexture = textureRender;
	}
	
	/**
	 * Sets the move animation to an animation graphical component
	 * @param animationRender - The movement animation
	 */
	public void setMoveAnimation(AnimationRender animationRender) {
		moveAnimation = animationRender;
	}
	
	/**
	 * Shoots a bullet.
	 */
	public void shoot() {
		
		if(!canShoot) {
			return;
		}
		
		Rectangle rect = getPhysics().getRectangle();
		
		float posx = 0, posy = 0;
		Vector2 dir = new Vector2();
		
		if(verticalFacingDirection == VerticalFacingDirection.None) {
			if(horizontalFacingDirection == HorizontalFacingDirection.Right) {
				posx = rect.x + rect.width;
				posy = rect.y + rect.height/2;
				dir.x = 1;
			} else {
				posx = rect.x;
				posy = rect.y + rect.height/2;
				dir.x = -1;
			}
		} else if (verticalFacingDirection == VerticalFacingDirection.Up) {
			posx = rect.x + rect.width/2;
			posy = rect.y + rect.height;
			dir.y = 1;
		} else {
			posx = rect.x + rect.width/2;
			posy = rect.y;
			dir.y = -1;
		}
		
		Bullet bullet = new Bullet(posx, posy, dir.scl(400));
		addChild(bullet);
		
		canShoot = false;
		enableShoot.scheduleTask(new Task() {

			@Override
			public void run() {
				canShoot = true;
			}
			
		}, SHOOT_DELAY);
	}
	
	private CharacterPhysics getPhysics() {
		return (CharacterPhysics) getPhysicalComponent();
	}
	
}
