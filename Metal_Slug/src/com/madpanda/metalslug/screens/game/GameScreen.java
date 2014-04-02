package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.AbstractScreen;
import com.madpanda.metalslug.screens.game.scene.Scene;

public class GameScreen extends AbstractScreen {

	private MetalSlug game;
	
	public GameScreen() {
		game = new MetalSlug(new Scene(30, 30), getCamera());
		Gdx.input.setInputProcessor(game.getInputComponent());
	}

	@Override
	public void update() {
		game.getPhysicalComponent().update();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		game.getGraphicalComponent().render(batch);
	}
	
}
