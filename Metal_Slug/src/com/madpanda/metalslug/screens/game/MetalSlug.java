package com.madpanda.metalslug.screens.game;

import com.madpanda.metalslug.screens.game.scene.Scene;

/**
 * The instance of the game.
 * Manages the current scene of the game.
 * It is the root entity of the game.
 *
 */
public class MetalSlug extends Entity {
		
	/**
	 * Creates a new game and with the given scene as the current scene.
	 * @param scene - The initial scene
	 */
	public MetalSlug(Scene scene) {
		setScene(scene);
	}

	/**
	 * Changes the scene of the game.
	 * @param scene - The scene to be used.
	 */
	public void setScene(Scene scene) {
		//remove old scene if there is one
		if (scene != null) {
			removeChild(scene);
		}
		addChild(scene); //add the scene as a child of the game.
	}
	
}
