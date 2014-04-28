package com.madpanda.metalslug.screens.game.scene.character;

import com.madpanda.metalslug.screens.game.scene.Tile;
import com.madpanda.metalslug.screens.game.scene.character.Character.MovementState;
import com.madpanda.metalslug.screens.game.scene.character.CharacterPhysics.AbstractCollisionHandler;

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
		if(character.getState() == MovementState.Jumping) {
			character.stand();
		}
	}
	
	
}
