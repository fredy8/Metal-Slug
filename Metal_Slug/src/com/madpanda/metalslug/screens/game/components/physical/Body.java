package com.madpanda.metalslug.screens.game.components.physical;

import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;

public class Body extends PhysicalComponent {

	private Rectangle rectangle;
	
	public Body(Entity entity, Rectangle rectangle) {
		super(entity);
		this.rectangle = rectangle;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
}
