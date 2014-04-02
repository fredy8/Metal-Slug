package com.madpanda.metalslug.screens.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.madpanda.metalslug.screens.game.components.input.MovingCameraWithArrows;
import com.madpanda.metalslug.screens.game.scene.Scene;

public class MetalSlug extends Entity {
	
	public MetalSlug(Scene scene, OrthographicCamera camera) {
		setScene(scene);
		this.setInputComponent(new MovingCameraWithArrows(this, camera, 20));
	}

	public void setScene(Scene scene) {
		if (scene != null) {
			removeChild(scene);
		}
		addChild(scene);
	}
	
}
