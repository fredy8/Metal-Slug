package com.madpanda.metalslug.screens.game.components.physical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;

public class MovingBody extends Body {
	
	private Vector2 speed, acceleration;
	
	public MovingBody(Entity entity, Rectangle rectangle) {
		super(entity, rectangle);
		speed = new Vector2();
		acceleration = new Vector2();
	}
	
	@Override
	public void update() {
		float dt = Gdx.graphics.getDeltaTime();
		move(new Vector2(speed.cpy().scl(dt)));
		speed.add(acceleration.cpy().scl(dt));
	}

	public void move(Vector2 vector) {
		getRectangle().x += vector.x;
		getRectangle().y += vector.y;
	}
	
	public Vector2 getSpeed() {
		return speed;
	}

	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

}
