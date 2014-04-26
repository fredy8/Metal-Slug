package com.madpanda.metalslug.screens.game.components.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;

/**
 * A graphical component used to render an entity with a predefined offset.
 *
 */
public abstract class RenderWithOffset extends GraphicalComponent {

	/**
	 * Creates a new RenderWithOffset component given the containing entity.
	 * @param entity - the containing entity.
	 */
	public RenderWithOffset(Entity entity) {
		super(entity);
	}

	/**
	 * The default render method will render the entity with no offset
	 */
	@Override
	public void render(SpriteBatch batch) {
		render(batch, Vector2.Zero);
		super.render(batch);
	}
	
	/**
	 * Defines a render method with an offset.
	 * @param batch - The batch where the entity is drawn.
	 * @param offset - The offset for drawing the entity.
	 */
	public void render(SpriteBatch batch, Vector2 offset) {
		super.render(batch);
	}
	
	/**
	 * The default render method will render the entity shapes with no offset
	 */
	@Override
	public void renderShapes() {
		renderShapes(Vector2.Zero);
		super.renderShapes();
	}
	
	/**
	 * Defines a render method for shapes with an offset.
	 * @param offset - The offset for drawing the entity.
	 */
	public abstract void renderShapes(Vector2 offset);

}
