package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.components.graphical.TextureRender;
import com.madpanda.metalslug.screens.game.components.physical.Body;
import com.madpanda.metalslug.screens.game.scene.Bullet;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class Enemy extends Character {

	int shootCounter;
	
	public Enemy(Scene scene, float x, float y, String texture, int lives) {
		super(new Rectangle(x, y, Tile.SIZE * 2, Tile.SIZE * 2), scene, lives);
		TextureRender standingTexture = new TextureRender(this, ((Body) getPhysicalComponent()).getRectangle(), texture);
		this.setStandingTexture(standingTexture);
		setGraphicalComponent(standingTexture);
		this.setFacingDirection(HorizontalFacingDirection.Left);
		shootCounter = 0;
	}

	public void update() {
		if(shootCounter == 30) {
			shoot(150);
		}
		if(shootCounter == 90) {
			Bullet b = (Bullet) children.iterator().next();
			b.die();
			removeChild(b);
			shootCounter = 0;
		}
		shootCounter++;
	}
	
	public void die() {
		super.die();
		Scene.getInstance().increaseScore();
	}
	
}
