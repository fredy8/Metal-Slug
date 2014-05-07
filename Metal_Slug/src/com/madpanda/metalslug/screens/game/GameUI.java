package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.game.scene.Scene;

public class GameUI {

	private static SpriteBatch batch = new SpriteBatch();
	
	private static Sprite heartSprite = new Sprite(new Texture("images/game/fullHeart.png"));
	private static Sprite[] numbers = new Sprite[10];
	
	private GameUI() { }
	
	static
	{
		for(int i = 0; i < 10; i++) {
			numbers[i] = new Sprite(new Texture("images/game/numbers/" + i + ".png"));
		}
	}
	
	public static void render() {
		
		int lives = Scene.getInstance().getPlayer().getLivesCount();
		String score = Integer.toString(Scene.getInstance().getPlayer().getScore());
		
		batch.begin();
		for(int i = 0; i < lives; i++) {
			heartSprite.setBounds(15 + 40*i, Gdx.graphics.getHeight() - 45, 30, 30);
			heartSprite.draw(batch);
		}
		
		for(int i = 0; i < score.length(); i++) {
			numbers[score.charAt(i) - '0'].setBounds(Gdx.graphics.getWidth() / 2 + 50*i, Gdx.graphics.getHeight() * 3 / 4, 40, 60);
			numbers[score.charAt(i) - '0'].draw(batch);
		}
		
		batch.end();
	}
	
}
