package com.madpanda.metalslug.screens.game.scene;

import com.madpanda.metalslug.screens.game.scene.Character.CharacterState;
import com.madpanda.metalslug.screens.game.scene.CharacterPhysics.AbstractCollisionHandler;

/**
 * Handles the collision of the character with the scene.
 *
 */
public class CharacterCollisionHandler extends AbstractCollisionHandler {

	private Character character; //the character for which the collisions are handled.
	
	/**
	 * Creates a new CharacterCollisionHandler given the character.
	 * @param character - The character for which the collisions are handled.
	 */
	public CharacterCollisionHandler(Character character) {
		this.character = character;
	}

	/**
	 * When hitting the floor, the player changes its state to standing.
	 */
	@Override
	public void onDownCollision(Tile tile) {
		if(character.getState() == CharacterState.Jumping) {
			character.stand();
		}
	}
	
	
}
