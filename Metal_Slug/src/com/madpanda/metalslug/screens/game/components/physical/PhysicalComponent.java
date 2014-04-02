package com.madpanda.metalslug.screens.game.components.physical;

import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.Component;

public class PhysicalComponent extends Component {

	public PhysicalComponent(Entity entity) {
		super(entity);
	}

	public void update() {
		for(Entity entity : getEntity().getChildren()) {
			entity.getPhysicalComponent().update();
		}
	}

}
