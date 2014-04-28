package com.madpanda.metalslug.screens.game.components.graphical;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.GameScreen;
import com.madpanda.metalslug.screens.game.components.physical.Body;

/**
 * A graphical component that will render the physical shape of an entity.
 * The entity must have a physical component of a body.
 *
 */
public class BodyRender extends GraphicalComponent {

	private Color color; //The color of the shape
	
	/**
	 * Creates a new BodyRender component with the given color
	 * @param entity - The containing entity
	 * @param color - The color of the body
	 */
	public BodyRender(Entity entity, Color color) {
		super(entity);
		this.color = color;
	}

	/**
	 * Renders the body as a shape of its physical component.
	 */
	@Override
	public void renderShapes() {
		ShapeRenderer renderer = GameScreen.getShapeRenderer(); //gets the shape renderer
		//gets the shape of the physical component of the containing entity
		Rectangle rect = ((Body) getEntity().getPhysicalComponent()).getRectangle();
		renderer.setColor(color); //sets the color
		renderer.begin(ShapeType.Filled); //renders the shape filled
		renderer.rect(rect.x, rect.y, rect.width, rect.height);
		renderer.end();
		super.renderShapes();
	}
	
}
