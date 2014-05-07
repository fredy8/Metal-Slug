package com.madpanda.metalslug.screens.game.scene.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.madpanda.metalslug.App;
import com.madpanda.metalslug.screens.MainMenuScreen;
import com.madpanda.metalslug.screens.game.GameScreen;
import com.madpanda.metalslug.screens.game.TextureManager;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class Player extends Character {

	private static final float WIDTH = Tile.SIZE * 2;
	private static final float HEIGHT = Tile.SIZE * 3;
	private static final float JUMPING_SPEED = 320;
	
	private boolean onFloor;
	private Sprite standingSprite, jumpingSprite;
	private int score;
	private Animation moveAnimation;
	private float animationTime;
	private float shootingDelay = .3f;
	private int bulletStrength = 1;
	private static Sound gunshot = Gdx.audio.newSound(Gdx.files.internal("music/gunshot.mp3"));
	private static Sprite gunSprite = new Sprite(TextureManager.getTexture("images/game/gun.png"));
	private static Sprite tankSprite;
	private static Animation tankAnimation;
	private boolean tankMode;
	private int maxLives;

	/**
	 * Creates a new player at the given position.
	 */
	public Player(float x, float y, int lives) {
		super(new Rectangle(x, y, WIDTH, HEIGHT), lives);
		
		standingSprite = new Sprite(TextureManager.getTexture("images/game/Soldier/soldier0.png"));
		jumpingSprite = new Sprite(TextureManager.getTexture("images/game/Soldier/soldier9.png"));
		
		TextureRegion movingTextures[] = new TextureRegion[8];
		for (int i = 1; i < 9; i++) {
			movingTextures[8-i] = new TextureRegion(TextureManager.getTexture("images/game/Soldier/soldier" + i + ".png"));
		}
		
		maxLives = 10;
		
		moveAnimation = new Animation(.025f, movingTextures);
	}
	
	public void update() {
		onFloor = false;
		super.update();
		
		for(PowerUp powerup : Scene.getInstance().getPowerUps()) {
			if(powerup.getRectangle().overlaps(getRectangle())) {
				Timer timer = new Timer();
				Scene.getInstance().removePowerUp(powerup);
				switch(powerup.getType()) {
				case FireRate:
					if(tankMode) {
						break;
					}
					shootingDelay = .1f;
					timer.scheduleTask(new Task() {
						@Override
						public void run() {
							shootingDelay = .3f;
						}
					}, 5f);
					break;
				case Speed:
					setRunningSpeed(280);
					timer.scheduleTask(new Task() {
						@Override
						public void run() {
							setRunningSpeed(200);
						}
					}, 5f);
					break;
				case SuperShot:
					if(tankMode) {
						break;
					}
					bulletStrength = 2;
					timer.scheduleTask(new Task() {
						@Override
						public void run() {
							bulletStrength = 1;
						}
					}, 5f);
					break;
				case ExtraLife:
					lives++;
					if(lives > getMaxLives()) {
						lives = getMaxLives();
					}
					break;
				case Tank:
					tankMode = true;
					tankSprite = new Sprite(TextureManager.getTexture("images/game/Tank/tank1.png"));
					TextureRegion tankTextures[] = new TextureRegion[4];
					for(int i = 0; i < 4; i++) {
						tankTextures[i] = new TextureRegion(TextureManager.getTexture("images/game/Tank/tank" + (i+1) + ".png"));
					}
					tankAnimation = new Animation(.05f, tankTextures);
					getRectangle().width *= 3;
					getRectangle().height *= 1.2;
					maxLives = 15;
					lives += 5;
					bulletStrength = 2;
					shootingDelay = .2f;
					break;
				}
			}
		}
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
		
		if(!tankMode) {
			if(getFacingDirection() == FacingDirection.Left) {
				gunSprite.setBounds(getRectangle().x - getRectangle().width/3, getRectangle().y + getRectangle().height/4, getRectangle().width, getRectangle().height/3);
			} else {
				gunSprite.setBounds(getRectangle().x + getRectangle().width/3, getRectangle().y + getRectangle().height/4, getRectangle().width, getRectangle().height/3);
			}
			gunSprite.setFlip(getFacingDirection() == FacingDirection.Left, false);
			gunSprite.draw(batch);
		}
		
		Sprite sprite = standingSprite;
		
		if(tankMode) {
			sprite = tankSprite;
		} 
		
		if(!onFloor) {
			if(tankMode) {
				sprite = tankSprite;
			} else {
				sprite = jumpingSprite;
			}
		} else if(getSpeed().x != 0) {
			animationTime += Gdx.graphics.getDeltaTime();
			if(tankMode) {
				sprite = new Sprite(tankAnimation.getKeyFrame(animationTime, true));
			} else {
				sprite = new Sprite(moveAnimation.getKeyFrame(animationTime, true));
			}
		}
		
		sprite.setFlip(getFacingDirection() == FacingDirection.Left, false);
		
		sprite.setBounds(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
		sprite.draw(batch);
		
	}
	
	@Override
	public void die() {
		super.die();
		Preferences prefs = Gdx.app.getPreferences("Highscore");
		
		int highscore = prefs.getInteger("highscore");
		if(getScore() > highscore) {
			prefs.putInteger("highscore", getScore());
		}
		
		prefs.flush();
		((App) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
		GameScreen.getInstance().getMusic().stop();
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

	public void setScore(int score) {
		this.score = score;
		System.out.println(score);
	}
	
	public float getShootingDelay() {
		return shootingDelay;
	}

	public int getBulletStrength() {
		return bulletStrength;
	}

	@Override
	public boolean shoot(int speed, float delay, int bulletStrength, float lifetime) {
		if(super.shoot(speed, delay, bulletStrength, lifetime)) {
			gunshot.play();
			return true;
		}
		return false;
	}

	@Override
	public Texture getBulletTexture() {
		if(tankMode) {
			return TextureManager.getTexture("images/game/balaFuerte.png");
		}
		return TextureManager.getTexture("images/game/balaNormal.png");
	}
	
	public int getMaxLives() {
		return maxLives;
	}

}
