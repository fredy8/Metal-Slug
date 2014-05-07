package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.game.scene.Scene;

public class GameUI {
	
	private static Sprite heartSprite = new Sprite(TextureManager.getTexture("images/game/fullHeart.png"));
	private static Sprite emptyHeartSprite = new Sprite(TextureManager.getTexture("images/game/emptyHeart.png"));
	private static Sprite[] numbers = new Sprite[10];
	private static int highscore;
	
	private GameUI() { }
	
	static
	{
		for(int i = 0; i < 10; i++) {
			numbers[i] = new Sprite(TextureManager.getTexture("images/game/numbers/" + i + ".png"));
		}
	}
	
	public static void checkHighScore() {
		Preferences prefs = Gdx.app.getPreferences("Highscore");
		if(!prefs.contains("highscore")) {
			prefs.putInteger("highscore", 0);
		}
		
		highscore = prefs.getInteger("highscore");
		prefs.flush();
	}
	
	public static void render(SpriteBatch batch, OrthographicCamera camera) {
		int lives = Scene.getInstance().getPlayer().getLivesCount();
		String score = Integer.toString(Scene.getInstance().getPlayer().getScore());
		
		for(int i = 0; i < lives; i++) {
			heartSprite.setBounds(15 + 20*i + camera.position.x - camera.viewportWidth/2, camera.viewportHeight/2 - 30 + camera.position.y, 15, 15);
			heartSprite.draw(batch);
		}
		
		for(int i = lives; i < Scene.getInstance().getPlayer().getMaxLives(); i++) {
			emptyHeartSprite.setBounds(15 + 20*i + camera.position.x - camera.viewportWidth/2, camera.viewportHeight/2 - 30 + camera.position.y, 15, 15);
			emptyHeartSprite.draw(batch);
		}
		
		for(int i = 0; i < score.length(); i++) {
			numbers[score.charAt(i) - '0'].setBounds(camera.position.x - (score.length()/2 - i)*22 - 10, camera.position.y + camera.viewportHeight / 4, 20, 30);
			numbers[score.charAt(i) - '0'].draw(batch);
		}
		
		String hscore = Integer.toString(highscore);
		for(int i = 0; i < hscore.length(); i++) {
			numbers[hscore.charAt(i) - '0'].setBounds(camera.position.x + camera.viewportWidth/2 - 10 - hscore.length() * 6 + 6*i, camera.position.y + camera.viewportHeight/2 - 15, 6, 8);
			numbers[hscore.charAt(i) - '0'].draw(batch);
		}
		
	}
	
}
