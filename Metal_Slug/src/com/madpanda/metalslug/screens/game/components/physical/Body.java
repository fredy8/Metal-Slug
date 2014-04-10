package com.madpanda.metalslug.screens.game.components.physical;

import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;

/**
 * A physical body defined by and Axis Aligned Bounding Box.
 */
public class Body extends PhysicalComponent {

	private Rectangle rectangle; //The bounds of the body
	
	/**
	 * Creates a new Body given the containing entity and its bounds.
	 * @param entity - The containing entity.
	 * @param rectangle - The bounds of the body.
	 */
	public Body(Entity entity, Rectangle rectangle) {
		super(entity);
		this.rectangle = rectangle;
	}
	
	/**
	 * Return the bounds of the body.
	 * @return - The rectangle that defines this body's bounds.
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}
	
}
