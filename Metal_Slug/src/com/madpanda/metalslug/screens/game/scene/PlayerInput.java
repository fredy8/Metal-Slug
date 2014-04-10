package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.Input;
import com.madpanda.metalslug.screens.game.components.input.InputComponent;
import com.madpanda.metalslug.screens.game.scene.Character.CharacterState;

/**
 * Handles the input for the character controlled by the player.
 * 
 * @author Alfredo_Altamirano
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
		case Input.Keys.S:
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
			character.stopMovement();
			return true;
		case Input.Keys.D:
			character.stopMovement();
			return true;
		case Input.Keys.S:
			if (character.getState() == CharacterState.Crouching) {
				character.stand();
			}
			return true;
		}
		return false;
	}

}