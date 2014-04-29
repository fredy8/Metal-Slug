package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.scene.character.Character;

/**
 * The graphical component of the scene.
 * Renders the tiles and the characters of the scene.
 *
 */
public class SceneRender extends GraphicalComponent {
	
	private Texture background;
	private Texture livesTextures;
	private Texture numbers[];
	
	/**
	 * Creates a new SceneRender component given the scene.
	 * @param scene
	 */
	public SceneRender(Scene scene) {
		super(scene);
		background = new Texture("images/game/background.png");
		livesTextures = new Texture("images/game/fullHeart.png");
		numbers = new Texture[10];
		for(int i = 0; i < 10; i++) {
			numbers[i] = new Texture("images/game/numbers/" + i + ".png");
		}
	}
	
	/**
	 * Renders the characters of the scene.
	 */
	public void render(SpriteBatch batch) {
		Scene scene = (Scene) getEntity();
		
		batch.draw(background, 0, 0, background.getWidth()/background.getHeight()*350, 350);
		
		//render each character
		for(Character character : scene.getCharacters()) {
			character.getGraphicalComponent().render(batch);
		}
		
		for(int i = 0; i < scene.getPlayer().getLivesCount(); i++) {
			batch.draw(livesTextures, (livesTextures.getWidth() + 10)*i + scene.getCamera().position.x - scene.getCamera().viewportWidth/2 + 20, scene.getCamera().position.y + scene.getCamera().viewportHeight/2 - 50);
		}
		
		String score = Integer.toString(scene.getScore());
		for(int i = 0; i < score.length(); i++) {
			batch.draw(numbers[score.charAt(i) - '0'], 25*i + scene.getCamera().position.x + 20, scene.getCamera().position.y + scene.getCamera().viewportHeight/2 - 110);
		}
		
		super.render(batch);
	}
	
	/**
	 * Renders the tiles of the scene.
	 */
	public void renderShapes() {
		/*Scene scene = (Scene) getEntity();
		
		//render each tile.
		for(int i = 0; i < scene.getRowCount(); i++) {
			for(int j = 0; j < scene.getColumnCount(); j++) {
				RenderWithOffset render = (RenderWithOffset) scene.getTile(i, j).getGraphicalComponent(); 
				render.renderShapes(new Vector2(j * Tile.SIZE, i * Tile.SIZE));
			}
		}*/
	
		super.renderShapes();
	}

}