package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class Enemy extends Character {

	private Sprite sprite;
	
	/**
	 * Creates a new enemy at the given position and with the given texture.
	 */
	public Enemy(Texture texture, float x, float y, int lives, boolean isBoss) {
		super(new Rectangle(x, y, Tile.SIZE * (isBoss ? 8 : 2), Tile.SIZE * (isBoss ? 6 : 2)), lives);
		sprite = new Sprite(texture);
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch);
		sprite.setBounds(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
		sprite.draw(batch);
	}
	
	/**
	 * Updates the enemy so that it shoots.
	 */
	public void update() {
		super.update();
		
		Player player = Scene.getInstance().getPlayer();
		if(player.getRectangle().x > getRectangle().x) {
			setFacingDirection(FacingDirection.Right);
		} else {
			setFacingDirection(FacingDirection.Left);
		}
		
		float posy = player.getRectangle().y + player.getRectangle().height/2;
		float posx = player.getRectangle().x + player.getRectangle().width/2;
		if(posy > getRectangle().y && posy < getRectangle().y + getRectangle().height &&
		   Math.abs(posx - getRectangle().x + getRectangle().width/2) < 220) {
			shoot(250, .5f);
		}
		
	}
	
	public void die() {
		super.die();
		Scene.getInstance().getPlayer().increaseScore(20);
	}
	
}
