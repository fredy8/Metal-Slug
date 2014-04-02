package com.madpanda.metalslug.screens.game.components.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;

public class MovingCameraWithArrows extends InputComponent {

	private OrthographicCamera camera;
	private float speed;
	
	public MovingCameraWithArrows(Entity entity, OrthographicCamera camera, float speed) {
		super(entity);
		this.camera = camera;
		this.speed = speed;
	}

	@Override
	public boolean keyUp(int keycode) {
		Vector2 displacement = new Vector2();
		switch (keycode) {
		case Input.Keys.UP:
			displacement.y = speed;
			break;
		case Input.Keys.DOWN:
			displacement.y = -speed;
			break;
		case Input.Keys.LEFT:
			displacement.x = -speed;
			break;
		case Input.Keys.RIGHT:
			displacement.x = speed;
			break;
		}
		camera.translate(displacement);
		camera.update();
		return !displacement.equals(Vector2.Zero);
	}
	
}
