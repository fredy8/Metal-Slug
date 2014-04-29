package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.madpanda.metalslug.screens.game.components.input.InputComponent;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;
import com.madpanda.metalslug.screens.game.scene.character.Character.MovementState;

/**
 * Handles the input for the character controlled by the player.
 * 
 */
public class PlayerInput extends InputComponent {
	
	/**
	 * Creates a new PlayerInputComponent given the player
	 * @param character - The player character.
	 */
	public PlayerInput(Character character) {
		super(character);
	}

	/**
	 * Handles the keyboard press event for the player actions.
	 */
	@Override
	public boolean keyDown(int keycode) {
		Character character = (Character) getEntity();
		switch (keycode) {
		case Input.Keys.SPACE:
			character.jump();
			return true;
		case Input.Keys.A:
			character.moveLeft(); //starts moving
			return true;
		case Input.Keys.D:
			character.moveRight(); //starts moving
			return true;
		case Input.Keys.W:
			character.lookUp();
			return true;
		case Input.Keys.S:
			character.lookDown();
			return true;
		case Input.Keys.C:
			character.crouch();
			return true;
		}
		return false;
	}

	/**
	 * Handles the keyboard release event for the player actions.
	 */
	@Override
	public boolean keyUp(int keycode) {
		Character character = (Character) getEntity();
		switch (keycode) {
		case Input.Keys.SPACE:
			character.jump();
			return true;
		case Input.Keys.A:
			if(((MovingBody) character.getPhysicalComponent()).getSpeed().x < 0) {
				character.stopMovement();
				if(Gdx.input.isKeyPressed(Input.Keys.D)) {
					character.moveRight();
				}
			}
			return true;
		case Input.Keys.D:
			if(((MovingBody) character.getPhysicalComponent()).getSpeed().x > 0) {
				character.stopMovement();
				if(Gdx.input.isKeyPressed(Input.Keys.A)) {
					character.moveLeft();
				}
			}
			return true;
		case Input.Keys.W:
		case Input.Keys.S:
			character.stopLook();
			return true;
		case Input.Keys.C:
			if (character.getState() == MovementState.Crouching) {
				character.stand();
			}
			return true;
		case Input.Keys.K:
			character.shoot(400);
			return true;
		}
		return false;
	}

	@Override
	public void keyDownCheck() {
		Character character = (Character) getEntity();
		if(Gdx.app.getInput().isKeyPressed(Input.Keys.K)) {
			character.shoot(400);
		}
		
		super.keyDownCheck();
	}
	
}