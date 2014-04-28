package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.graphical.BodyRender;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;

public class Bullet extends Entity {

	private static final float WIDTH = 20, HEIGHT = 10;
	
	/**
	 * Creates a new bullet given its firing position and its speed.
	 * @param posX - The x component of the firing position.
	 * @param posY - The y component of the firing position.
	 * @param speed - The speed and direction of the bullet. Must be horizontal or vertical.
	 */
	public Bullet(float posX, float posY, Vector2 speed) {
		Vector2 direction = speed.cpy().nor();
		if(direction.equals(Vector2.Zero)) {
			throw new IllegalArgumentException("The speed cannot be zero.");
		}
		
		if(direction.x != 0 && direction.y != 0) {
			throw new IllegalArgumentException("The direction of the speed cannot be diagonal.");
		}
		
		Rectangle rect = calculateBounds(posX, posY, direction);
		MovingBody physicalComponent = new MovingBody(this, rect);
		physicalComponent.setSpeed(speed);
		setUpdateComponent(physicalComponent);
		
		setGraphicalComponent(new BodyRender(this, Color.BLUE));
	}
	
	private Rectangle calculateBounds(float posX, float posY, Vector2 direction) {
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
	
}
