package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	private Music music;
	
	/**
	 * Initializes the game and the initial scene.
	 */
	public GameScreen() {
		music = Gdx.audio.newMusic(Gdx.files.internal("music/background.mp3"));
		music.setLooping(true);
		
		setScene(GameEntityFactory.createScene(1, getCamera(), 10));
		GameUI.checkHighScore();
		instance = this;
	}

	@Override
	public void update() {
		currentScene.update();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		currentScene.render(batch); //renders the game
		GameUI.render(batch, getCamera());
	}
	
	/**
	 * Sets the scene of the game to the one given.
	 * @param scene - the new scene.
	 */
	public void setScene(Scene scene) {
		currentScene = scene;
		music.stop();
		music.play();
		Gdx.input.setInputProcessor(currentScene); //sets the input of the screen to the game
	}
	
	/**
	 * Returns the current instance of the game.
	 * @return the current gamescreen instance.
	 */
	public static GameScreen getInstance() {
		return instance;
	}

	public Music getMusic() {
		return music;
	}
	
}
