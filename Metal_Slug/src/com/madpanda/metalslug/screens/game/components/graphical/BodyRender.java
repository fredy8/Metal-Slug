package com.madpanda.metalslug.screens.game.components.graphical;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.GameScreen;
import com.madpanda.metalslug.screens.game.components.physical.Body;

public class BodyRender extends GraphicalComponent {

	private Color color;
	
	public BodyRender(Entity entity, Color color) {
		super(entity);
		this.color = color;
	}

	@Override
	public void render(SpriteBatch batch) {
		ShapeRenderer renderer = GameScreen.getShapeRenderer();
		Rectangle rect = ((Body) getEntity().getPhysicalComponent()).getRectangle();
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.rect(rect.x, rect.y, rect.width, rect.height);
		renderer.end();
	}

	
	
	
}
