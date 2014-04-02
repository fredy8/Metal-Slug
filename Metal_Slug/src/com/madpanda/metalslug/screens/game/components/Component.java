package com.madpanda.metalslug.screens.game.components;

import com.madpanda.metalslug.screens.game.Entity;

public class Component {

	private Entity entity;
	
	public Component(Entity entity) {
		this.entity = entity;
	}
	
	protected Entity getEntity() {
		return entity;
	}
	
}
