package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.App;
import com.madpanda.metalslug.screens.MainMenuScreen;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class Player extends Character {

	private static final float WIDTH = Tile.SIZE * 2;
	private static final float HEIGHT = Tile.SIZE * 3;
	private static final float JUMPING_SPEED = 300;
	
	private boolean onFloor;
	private Sprite standingSprite;
	private int score;

	/**
	 * Creates a new player at the given position.
	 */
	public Player(float x, float y) {
		super(new Rectangle(x, y, WIDTH, HEIGHT), 5);
		
		standingSprite = new Sprite(new Texture("images/game/Soldier/soldier0.png"));
		
		/*List<String> playerFrames = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			playerFrames.add("images/game/Soldier/soldier" + i + ".png");
		}*/
	}
	
	public void update() {
		onFloor = false;
		super.update();
	}
	
	/**
	 * Makes the character jump.
	 * Can only jump when standing.
	 */
	public void jump() {
		if(onFloor) {
			setSpeed(new Vector2(getSpeed().x, JUMPING_SPEED));
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		standingSprite.setBounds(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
		standingSprite.draw(batch);
	}
	
	@Override
	public void die() {
		super.die();
		((App) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
	}

	@Override
	protected void onCollision(int direction) {
		if(direction == 2) { //down collision
			onFloor = true;
		}
	}

	public int getScore() {
		return score;
	}
	
	public void increaseScore(int amount) {
		score += 10;
	}
	
	
}
