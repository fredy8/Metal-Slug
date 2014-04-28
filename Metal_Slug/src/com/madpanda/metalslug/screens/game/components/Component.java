package com.madpanda.metalslug.screens.game.components;

import com.madpanda.metalslug.screens.game.Entity;

/**
 * A component for an entity.
 * Contains a reference to the containing entity to provide its behavior.
 *
 */
public abstract class Component {

	private Entity entity; //The entity that has this component
	
	/**
	 * Creates a new component
	 * @param entity
	 */
	public Component(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Returns the entity.
	 * @return - the entity that contains this component.
	 */
	protected Entity getEntity() {
		return entity;
	}
	
}
