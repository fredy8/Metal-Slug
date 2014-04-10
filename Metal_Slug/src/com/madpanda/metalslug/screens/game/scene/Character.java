package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.graphical.BodyRender;
import com.madpanda.metalslug.screens.game.components.physical.Body;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;

/**
 * A character in the game. Moves freely in the scene colliding with some tiles.
 *
 */
public class Character extends Entity {

	private static final Vector2 JUMP = new Vector2(0, 200); //The jumping speed
	private CharacterState state; //the state in which the character is
	
	/**
	 * Creates a new character given its bounds and the scene in which it is created.
	 * @param rectangle - The bounds of the character
	 * @param scene - The scene where that character is created.
	 */
	public Character(Rectangle rectangle, Scene scene) {
		//set its physical component to the character physics component
		this.setPhysicalComponent(new CharacterPhysics(this, rectangle, scene, new CharacterCollisionHandler(this)));
		//for debugging, the graphical component is set to rendering the body's shape.
		this.setGraphicalComponent(new BodyRender(this, Color.BLUE));
		MovingBody body = (MovingBody) this.getPhysicalComponent();
		
		//sets its speed and acceleration
		//TODO - this should be defined in the CharacterPhysics.
		body.setSpeed(new Vector2(0, 0));
		body.setAcceleration(new Vector2(0, -200));
		
		//by default, its state is jumping as it can be created in midair, its state will change to standing if it touches the floor.
		state = CharacterState.Jumping;
	}
	
	/**
	 * Makes the character jump.
	 * Can only jump when standing.
	 */
	public void jump() {
		if(state == CharacterState.Standing) {
			MovingBody body = (MovingBody) getPhysicalComponent();
			body.setSpeed(body.getSpeed().add(JUMP));
			state = CharacterState.Jumping; //set the state to jumping
		}
	}
	
	/**
	 * Makes the character stand.
	 * Can only stand when in jumping state or when crouching.
	 */
	public void stand() {
		if(state == CharacterState.Jumping) {
			MovingBody body = (MovingBody) getPhysicalComponent();
			body.getSpeed().y = 0;
			state = CharacterState.Standing;
		} else if(state == CharacterState.Crouching) {
			Body body = (Body) getPhysicalComponent();
			//stop crouching.
			body.getRectangle().setHeight(body.getRectangle().getHeight()*2);
			state = CharacterState.Standing;
		}
	}
	
	/**
	 * Makes the player crouch.
	 * Can only crouch when standing.
	 */
	public void crouch() {
		if(state == CharacterState.Standing) {
			Body body = (Body) getPhysicalComponent();
			//Makes the character crouch
			body.getRectangle().setHeight(body.getRectangle().getHeight()/2);
			state = CharacterState.Crouching;
		}
	}

	/**
	 * The possible states of the character.
	 *
	 */
	public enum CharacterState {
		Standing, Jumping, Crouching;
	}
	
	/**
	 * Returns the current state of the character
	 * @return - The current state of the character
	 */
	public CharacterState getState() {
		return state;
	}

	/**
	 * Makes the player move to the right until stopped.
	 */
	public void moveRight() {
		MovingBody body = (MovingBody) getPhysicalComponent();
		body.getSpeed().x = 200;
	}
	
	/**
	 * Makes the player move to the left until stopped.
	 */
	public void moveLeft() {
		MovingBody body = (MovingBody) getPhysicalComponent();
		body.getSpeed().x = -200;
	}

	/**
	 * Stops the player horizontal movement.
	 */
	public void stopMovement() {
		MovingBody body = (MovingBody) getPhysicalComponent();
		body.getSpeed().x = 0;
	}
	
	
}
