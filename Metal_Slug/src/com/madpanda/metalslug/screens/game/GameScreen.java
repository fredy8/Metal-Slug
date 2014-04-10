package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.AbstractScreen;

/**
 * The game screen.
 * Contains a game instance and manages the game.
 *
 */
public class GameScreen extends AbstractScreen {

	private MetalSlug game; //The game instance
	
	/**
	 * Initializes the game and the initial scene.
	 */
	public GameScreen() {
		game = new MetalSlug(GameEntityFactory.createScene(1, getCamera()));
		Gdx.input.setInputProcessor(game.getInputComponent()); //sets the input of the screen to the game
	}

	@Override
	public void update() {
		game.getPhysicalComponent().update(); //updates the game
	}
	
	@Override
	public void render(SpriteBatch batch) {
		game.getGraphicalComponent().render(batch); //renders the game
	}
	
}
