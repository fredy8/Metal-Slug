package com.madpanda.metalslug.screens.game.scene.character;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.TextureManager;
import com.madpanda.metalslug.screens.game.components.physical.Body;

public class PowerUp extends Body {

	private Sprite sprite;
	private PowerUpType type;
	
	public enum PowerUpType {
		FireRate("images/game/fireRate.png"), Speed("images/game/speed.png"), SuperShot("images/game/superShot.png"), ExtraLife("images/game/fullHeart.png"), Tank("images/game/Tank/tank1.png");
		
		private Texture texture;
		
		private PowerUpType(String texturePath) {
			this.texture = TextureManager.getTexture(texturePath);
		}

		public Texture getTexture() {
			return texture;
		}
	}
	
	public static PowerUp getRandom(float posx, float posy) {
		int type = new Random().nextInt(4);
		if(type == 0) {
			return new PowerUp(posx, posy, PowerUpType.FireRate);
		}
		if(type == 1) {
			return new PowerUp(posx, posy, PowerUpType.Speed);
		}
		if(type == 2) {
			return new PowerUp(posx, posy, PowerUpType.ExtraLife);
		}
		
		return new PowerUp(posx, posy, PowerUpType.SuperShot);
	}
	
	public PowerUp(float posx, float posy, PowerUpType type) {
		super(new Rectangle(posx, posy, 20, 20));
		this.type = type;
		sprite = new Sprite(type.getTexture());
		sprite.setBounds(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
	}

	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	public PowerUpType getType() {
		return type;
	}
	
}
