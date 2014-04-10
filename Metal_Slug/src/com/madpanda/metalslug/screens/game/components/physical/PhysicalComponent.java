package com.madpanda.metalslug.screens.game.components.physical;

import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.Component;

/**
 * The physical component that defines the physical behavior of the entity.
 *
 */
public class PhysicalComponent extends Component {

	/**
	 * Creates a new PhysicalComponent given the containing entity.
	 * @param entity - The containing entity.
	 */
	public PhysicalComponent(Entity entity) {
		super(entity);
	}

	/**
	 * By default, the update callback is delegated to the children of the containing entity.
	 */
	public void update() {
		for(Entity entity : getEntity().getChildren()) {
			entity.getPhysicalComponent().update();
		}
	}

}
