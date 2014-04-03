package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.components.graphical.RenderWithOffset;

public class SceneRender extends GraphicalComponent {
	
	public SceneRender(Scene scene) {
		super(scene);
	}

	public void render(SpriteBatch batch) {
		Scene scene = (Scene) getEntity();
		for(int i = 0; i < scene.getRowCount(); i++) {
			for(int j = 0; j < scene.getColumnCount(); j++) {
				RenderWithOffset render = (RenderWithOffset) scene.getTile(i, j).getGraphicalComponent(); 
				render.render(batch, new Vector2(i * Tile.SIZE, j * Tile.SIZE));
			}
		}
		for(Character character : scene.getCharacters()) {
			character.getGraphicalComponent().render(batch);
		}
	}

}