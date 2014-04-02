package com.madpanda.metalslug.screens.game.components.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;

public abstract class RenderWithOffset extends GraphicalComponent {

	public RenderWithOffset(Entity entity) {
		super(entity);
	}

	@Override
	public void render(SpriteBatch batch) {
		render(batch, Vector2.Zero);
	}
	
	public abstract void render(SpriteBatch batch, Vector2 offset);

}
