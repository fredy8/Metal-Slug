package com.madpanda.metalslug.screens.game.components.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;

/**
 * A input component for moving a camera with the arrows.
 * Used for debugging purposes.
 *
 */
public class MovingCameraWithArrows extends InputComponent {

	private OrthographicCamera camera; //the camera that is moved
	private float speed; //the speed of the camera
	
	/**
	 * Creates a new MovingCameraWithArrows Component given the containing entity, the camera and the speed.
	 * @param entity - The containing entity
	 * @param camera - The camera to be moved.
	 * @param speed - The speed with which the camera will be moved.
	 */
	public MovingCameraWithArrows(Entity entity, OrthographicCamera camera, float speed) {
		super(entity);
		this.camera = camera;
		this.speed = speed;
	}

	/**
	 * Handles the movement of the camera if any of the arrow keys are pressed.
	 */
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
		//moves the camera
		camera.translate(displacement);
		camera.update();
		if(displacement.equals(Vector2.Zero)) { //if any other key was pressed, the input callback is delegated to the entities' children.
			return super.keyUp(keycode);
		}
		return false;
	}
	
}
