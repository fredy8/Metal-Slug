package com.madpanda.metalslug.screens.game.components.physical;

import com.badlogic.gdx.math.Rectangle;

/**
 * A physical body defined by and Axis Aligned Bounding Box.
 */
public class Body {

	private Rectangle rectangle; //The bounds of the body
	
	/**
	 * Creates a new Body given the containing entity and its bounds.
	 * @param entity - The containing entity.
	 * @param rectangle - The bounds of the body.
	 */
	public Body(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	/**
	 * Return the bounds of the body.
	 * @return - The rectangle that defines this body's bounds.
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void update() { }
	
}
