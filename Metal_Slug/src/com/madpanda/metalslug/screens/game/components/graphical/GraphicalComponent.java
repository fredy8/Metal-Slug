package com.madpanda.metalslug.screens.game.components.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.Component;

/**
 * The graphical component for defining the rendering of an entity
 *
 */
public class GraphicalComponent extends Component {

	/**
	 * Creates a new graphical component given the containing entity.
	 * @param entity - The containing entity for this component.
	 */
	public GraphicalComponent(Entity entity) {
		super(entity);
	}

	/**
	 * By default, the entity will delegate the render callbacks to its children.
	 * @param batch - The batch in which the entity is drawn.
	 */
	public void render(SpriteBatch batch) {
		//renders the entities' children.
		for(Entity entity : getEntity().getChildren()) {
			entity.getGraphicalComponent().render(batch);
		}
	}

	/**
	 * By default, the entity will delegate the render shape callbacks to its children.
	 */
	public void renderShapes() {
		//renders the entities' children.
		for(Entity entity : getEntity().getChildren()) {
			entity.getGraphicalComponent().renderShapes();
		}
	}

}
