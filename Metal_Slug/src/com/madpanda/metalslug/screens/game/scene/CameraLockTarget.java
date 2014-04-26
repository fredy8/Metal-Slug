package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;
import com.madpanda.metalslug.screens.game.components.physical.UpdateComponent;

/**
 * A physical component for moving a camera to follow a moving body.
 *
 */
public class CameraLockTarget extends UpdateComponent {

	private MovingBody body; //The body to be followed.
	private OrthographicCamera camera; //The camera to be moved.
	private Rectangle toleranceArea; //an area of tolerance in which the body can be moved without moving the camera
	
	/**
	 * Creates a new CameraLockTarget given the camera, the scene and a toleranceArea
	 * @param camera - The camera that follows the body.
	 * @param scene - The scene containing the player that is followed.
	 * @param toleranceArea - An area of tolerance in which the body can be moved without moving the camera
	 */
	public CameraLockTarget(OrthographicCamera camera, Scene scene, Rectangle toleranceArea) {
		super(scene);
		this.body = (MovingBody) scene.getPlayer().getPhysicalComponent();
		this.camera = camera;
		this.toleranceArea = toleranceArea;
	}

	@Override
	public void update() {
		Rectangle rect = body.getRectangle();
		
		camera.position.x = rect.x;
		camera.position.y = rect.y;
		camera.update();
		
		/* TODO apply the tolerance area
		if(rect.x + rect.width > toleranceArea.x + toleranceArea.width) {
			camera.position.x += rect.x + rect.width - (toleranceArea.x + toleranceArea.width);
			camera.update();
		} else if (rect.x < toleranceArea.x) {
			camera.position.x -= toleranceArea.x - rect.x;
			camera.update();
		}

		if(rect.x + rect.width > toleranceArea.x + toleranceArea.width) {
			camera.position.x += rect.x + rect.width - (toleranceArea.x + toleranceArea.width);
			camera.update();
		} else if (rect.x < toleranceArea.x) {
			camera.position.x -= toleranceArea.x - rect.x;
			camera.update();
		}*/
		
		
		super.update();
	}

}
