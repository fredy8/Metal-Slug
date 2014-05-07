package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;
import com.madpanda.metalslug.screens.game.scene.character.Character;

public class Bullet extends MovingBody {

	private static final float WIDTH = 10, HEIGHT = 5;
	
	private Character shooter;
	private Sprite sprite;
	private boolean removed;

	private int bulletStrength;

	/**
	 * Creates a new bullet given its firing position and its speed.
	 * @param shooter - The character that fired the bullet.
	 * @param posX - The x component of the firing position.
	 * @param posY - The y component of the firing position.
	 * @param speed - The speed and direction of the bullet. Must be horizontal or vertical.
	 * @param bulletStrength 
	 */
	public Bullet(final Character shooter, float posX, float posY, Vector2 speed, Texture texture, int bulletStrength, float lifetime) {
		super(calculateBounds(posX, posY, speed.cpy().nor()));
		
		this.shooter = shooter;
		this.bulletStrength = bulletStrength;
		getRectangle().width *= bulletStrength;
		getRectangle().height *= bulletStrength;
		
		if(speed.equals(Vector2.Zero)) {
			throw new IllegalArgumentException("The speed cannot be zero.");
		}
		
		if(speed.x != 0 && speed.y != 0) {
			throw new IllegalArgumentException("The direction of the speed cannot be diagonal.");
		}
		
		Timer bulletTimer = new Timer();
		bulletTimer.scheduleTask(new Task() {

			@Override
			public void run() {
				remove();
			}
			
		}, .7f);
		
		setSpeed(speed);
		
		sprite = new Sprite(texture);
	}
	
	public void remove() {
		if(!removed) {
			shooter.removeBullet(Bullet.this);
			removed = true;
		}
	}
	
	public void update() {
		super.update();
		
		for(Character character : Scene.getInstance().getCharacters()) {
			if(shooter != Scene.getInstance().getPlayer() && character == Scene.getInstance().getPlayer() ||
					shooter == Scene.getInstance().getPlayer() && character != Scene.getInstance().getPlayer()) {
				if(getRectangle().overlaps(character.getRectangle())) {
					character.hit(this);
				}
			}
		}
	}
	
	private static Rectangle calculateBounds(float posX, float posY, Vector2 direction) {
		Rectangle rect = new Rectangle(posX, posY, WIDTH, HEIGHT);
		
		//if shooting vertically, rotate the bullet 90 degrees.
		if(direction.x == 0) {
			float w = rect.width;
			rect.width = rect.height;
			rect.height = w;
		}
		
		if(direction.x > 0) {
			rect.y -= rect.height/2;
		} else if(direction.x < 0) {
			rect.x -= rect.width;
			rect.y -= rect.height/2;
		} else if(direction.y > 0) {
			rect.x -= rect.height/2;
		} else if(direction.y < 0) {
			rect.x -= rect.height/2;
			rect.y -= rect.height;
		}
				
		return rect;
	}

	public void render(SpriteBatch batch) {
		sprite.setFlip(getSpeed().x < 0, false);
		sprite.setBounds(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
		sprite.draw(batch);
	}

	public int getBulletStrength() {
		return bulletStrength;
	}
	
}
