package com.madpanda.metalslug.screens.game.components.physical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;

/**
 * A body that can change position given a speed and an acceleration.
 *
 */
public class MovingBody extends Body {
	
	private Vector2 speed, acceleration; //The speed and acceleration for moving the body.
	
	/**
	 * Creates a new MovingBody given the containing entity and the bounds of the body-
	 * @param entity - The containing entity
	 * @param rectangle - The bounds of the body.
	 */
	public MovingBody(Entity entity, Rectangle rectangle) {
		super(entity, rectangle);
		speed = new Vector2(); //initializes to zero vector
		acceleration = new Vector2(); //initializes to zero vector
	}
	
	/**
	 * Moves the body given how much time elapsed and updates the speed with the acceleration.
	 */
	@Override
	public void update() {
		float dt = Gdx.graphics.getDeltaTime(); //the time elapsed from the last frame.
		move(new Vector2(speed.cpy().scl(dt))); //move the body.
		speed.add(acceleration.cpy().scl(dt)); //update the speed.
		super.update();
	}

	/**
	 * Moves the body by a vector
	 * @param vector - The translation vector of the body.
	 */
	public void move(Vector2 vector) {
		getRectangle().x += vector.x;
		getRectangle().y += vector.y;
	}
	
	/**
	 * Returns the speed of the moving body.
	 * @return The speed vector of the body.
	 */
	public Vector2 getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the moving body to the given speed vector.
	 * @param speed - The new speed of this moving body.
	 */
	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}

	/**
	 * Returns the acceleration of this moving body.
	 * @return - The acceleration of the body.
	 */
	public Vector2 getAcceleration() {
		return acceleration;
	}

	/**
	 * Sets the acceleration of the moving body to the given acceleration vector.
	 * @param acceleration - The acceleration of the body.
	 */
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

}
