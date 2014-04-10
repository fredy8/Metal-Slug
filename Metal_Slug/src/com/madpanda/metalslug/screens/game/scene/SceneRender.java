package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.components.graphical.RenderWithOffset;

/**
 * The graphical component of the scene.
 * Renders the tiles and the characters of the scene.
 *
 */
public class SceneRender extends GraphicalComponent {
	
	/**
	 * Creates a new SceneRender component given the scene.
	 * @param scene
	 */
	public SceneRender(Scene scene) {
		super(scene);
	}
	
	/**
	 * Renders the characters of the scene.
	 */
	public void render(SpriteBatch batch) {
		Scene scene = (Scene) getEntity();
				
		//render each character
		for(Character character : scene.getCharacters()) {
			character.getGraphicalComponent().render(batch);
		}
	}
	
	/**
	 * Renders the tiles of the scene.
	 */
	public void renderShapes() {
		Scene scene = (Scene) getEntity();
		
		//render each tile.
		for(int i = 0; i < scene.getRowCount(); i++) {
			for(int j = 0; j < scene.getColumnCount(); j++) {
				RenderWithOffset render = (RenderWithOffset) scene.getTile(i, j).getGraphicalComponent(); 
				render.renderShapes(new Vector2(j * Tile.SIZE, i * Tile.SIZE));
			}
		}
		
	}

}