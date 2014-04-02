package com.madpanda.metalslug.screens.game.components.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.Component;

public class GraphicalComponent extends Component {

	public GraphicalComponent(Entity entity) {
		super(entity);
	}

	public void render(SpriteBatch batch) {
		for(Entity entity : getEntity().getChildren()) {
			entity.getGraphicalComponent().render(batch);
		}
	}

}
