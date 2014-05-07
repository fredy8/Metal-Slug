package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.TextureManager;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class Enemy extends Character {

	private Animation animation;
	private float animationTime;
	
	private static Sprite heartSprite = new Sprite(TextureManager.getTexture("images/game/fullHeart.png"));
	private static Sprite emptyHeartSprite = new Sprite(TextureManager.getTexture("images/game/emptyHeart.png"));
	private final int totalLives;
	private boolean isBoss;
	
	/**
	 * Creates a new enemy at the given position and with the given texture.
	 */
	public Enemy(String animationPath, int frameCount, float x, float y, int lives, boolean isBoss) {
		super(new Rectangle(x, y, Tile.SIZE * (isBoss ? 7 : 2), Tile.SIZE * (isBoss ? 5 : 2)), lives);
		
		this.isBoss = isBoss;
		
		TextureRegion textures[] = new TextureRegion[frameCount];
		for (int i = 1; i <= frameCount; i++) {
			textures[i-1] = new TextureRegion(TextureManager.getTexture(animationPath + i + ".png"));
		}
		animation = new Animation(.1f, textures);
		totalLives = lives;
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		animationTime += Gdx.graphics.getDeltaTime();
		Sprite sprite = new Sprite(animation.getKeyFrame(animationTime, true));
		
		sprite.setFlip(getFacingDirection() != FacingDirection.Left, false);
		sprite.setBounds(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
		sprite.draw(batch);
		
		for(int i = 0; i < getLivesCount(); i++) {
			heartSprite.setBounds(getRectangle().x + (getRectangle().getWidth() / totalLives + 2) * i - (getRectangle().getWidth() - (totalLives - 1) * 2) / totalLives / 2, getRectangle().y + getRectangle().height + 3, (getRectangle().getWidth() - (totalLives - 1) * 2) / totalLives, (getRectangle().getWidth() - (totalLives - 1) * 2) / totalLives);
			heartSprite.draw(batch);
		}
		
		for(int i = getLivesCount(); i < totalLives; i++) {
			emptyHeartSprite.setBounds(getRectangle().x + (getRectangle().getWidth() / totalLives + 2) * i - (getRectangle().getWidth() - (totalLives - 1) * 2) / totalLives / 2, getRectangle().y + getRectangle().height + 3, (getRectangle().getWidth() - (totalLives - 1) * 2) / totalLives, (getRectangle().getWidth() - (totalLives - 1) * 2) / totalLives);
			emptyHeartSprite.draw(batch);
		}
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
		   Math.abs(posx - getRectangle().x + getRectangle().width/2) < (isBoss ? 500 : 300)) {
			if(isBoss) {
				shoot(230, 1.5f, 3, 5f);
			} else {
				shoot(260, .75f, 1, 1f);
			}
		}
		
		if(Math.abs(posx - getRectangle().x + getRectangle().width/2) < (isBoss ? 500 : 320)) {
			if(posx > getRectangle().x + getRectangle().width/2) {
				getSpeed().x = 50;
			} else {
				getSpeed().x = -50;
			}
		} else {
			getSpeed().x = 0;
		}
		
	}
	
	public void die() {
		super.die();
		Scene.getInstance().getPlayer().increaseScore(20);
		if(Math.random() < .4) {
			Scene.getInstance().addPowerUp(PowerUp.getRandom(getRectangle().x, getRectangle().y));
		}
	}

	@Override
	public Texture getBulletTexture() {
		if(!isBoss) {
			return TextureManager.getTexture("images/game/balaAlien.png");
		}
		
		switch(Scene.getInstance().getLevel()) {
		case 1:
			return TextureManager.getTexture("images/game/balaRhyn1.png");
		case 2:
			return TextureManager.getTexture("images/game/balaAlien.png");
		case 3:
			return TextureManager.getTexture("images/game/fire3.png");
		}
		
		return null;
	}
	
}
