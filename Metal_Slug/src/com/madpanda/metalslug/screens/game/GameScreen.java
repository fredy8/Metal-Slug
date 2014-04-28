package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.AbstractScreen;
import com.madpanda.metalslug.screens.game.scene.Scene;

/**
 * The game screen.
 * Contains a game instance and manages the game.
 *
 */
public class GameScreen extends AbstractScreen {

	private static GameScreen instance; //The game instance
	
	private Scene currentScene;
	
	/**
	 * Initializes the game and the initial scene.
	 */
	public GameScreen() {
		setScene(GameEntityFactory.createScene(1, getCamera()));
		Gdx.input.setInputProcessor(this); //sets the input of the screen to the game
	}

	@Override
	public void update() {
		//keyDownCheck();
		//update(); //updates the game
	}
	
	@Override
	public void render(SpriteBatch batch) {
		//render(batch); //renders the game
	}
	
	@Override
	public void renderShapes() {
		//renderShapes(); //renders the game shapes
	}
	
	public void setScene(Scene scene) {
		currentScene = scene;
	}
	
	public static GameScreen getInstance() {
		return instance;
	}
	
}
