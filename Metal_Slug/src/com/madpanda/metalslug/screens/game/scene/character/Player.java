package com.madpanda.metalslug.screens.game.scene.character;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.components.graphical.AnimationRender;
import com.madpanda.metalslug.screens.game.components.graphical.TextureRender;
import com.madpanda.metalslug.screens.game.components.physical.Body;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class Player extends Character {

	private static final float WIDTH = Tile.SIZE * 2;
	private static final float HEIGHT = Tile.SIZE * 3;
	
	public Player(Scene scene, float x, float y) {
		super(new Rectangle(x, y, WIDTH, HEIGHT), scene, 5);
		
		List<String> playerFrames = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			playerFrames.add("images/game/Soldier/soldier" + i + ".png");
		}
		setMoveAnimation(new AnimationRender(this, ((Body) this.getPhysicalComponent()).getRectangle(), playerFrames));
		setGraphicalComponent(new TextureRender(this, ((Body) this.getPhysicalComponent()).getRectangle(), "images/game/Soldier/soldier0.png"));
		setStandingTexture((TextureRender) getGraphicalComponent());
		setJumpingTexture(new TextureRender(this, ((Body) this.getPhysicalComponent()).getRectangle(), "images/game/Soldier/soldier9.png"));

		// make the camera follow the player
		setInputComponent(new PlayerInput(this));
	}

}
