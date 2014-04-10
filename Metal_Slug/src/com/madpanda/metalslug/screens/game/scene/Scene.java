package com.madpanda.metalslug.screens.game.scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.graphical.AnimationRender;
import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.components.graphical.TextureRender;

/**
 * A scene in a game.
 * Contains all the tiles and the players in the scene.
 *
 */
public class Scene extends Entity {

	private final Tile[][] tiles; //the game tiles
	private final int rows, columns; //the dimensions of the scene
	private Set<Character> characters; //the characters in the scene.
	private Character player; //the player character
	
	/**
	 * Creates a new Scene given the tiles information and the camera to display the scene.
	 * @param tiles - The information of each of the tiles of the scene.
	 * @param camera - The camera to display the scene.
	 */
	public Scene(Tile[][] tiles, OrthographicCamera camera) {
		this.tiles = tiles;
		this.rows = tiles.length;
		this.columns = tiles[0].length;
		this.setGraphicalComponent(new SceneRender(this));
		characters = new HashSet<>();
		
		//creates the player character
		Rectangle rect = new Rectangle(220, 100, Tile.SIZE*2, Tile.SIZE*3);
		player = new Character(rect, this);
		List<String> playerFrames = new ArrayList<>();
		for(int i = 1; i < 9; i++) {
			playerFrames.add("images/game/bueno" + i + ".png");
		}
		player.setMoveAnimation(new AnimationRender(player, rect, playerFrames));
		player.setGraphicalComponent(new TextureRender(player, rect, "images/game/bueno9.png"));
		player.setStandingTexture((TextureRender) player.getGraphicalComponent());
		
		//make the camera follow the player
		this.setPhysicalComponent(new CameraLockTarget(camera, this, new Rectangle(camera.viewportWidth/4, camera.viewportHeight/4, camera.viewportWidth/2, camera.viewportHeight/2)));
		player.setInputComponent(new PlayerInput(player));
		addCharacter(player);
	}
	
	//adds a character to the scene
	private void addCharacter(Character character) {
		addChild(character);
		characters.add(character);
	}
	
	//removes a character from the scene
	private void removeCharacter(Character character) {
		removeChild(character);
		characters.remove(character);
	}

	/**
	 * Returns the number of rows in the scene.
	 * @return The number of rows.
	 */
	public int getRowCount() {
		return rows;
	}
	
	/**
	 * Returns the number of columns in the scene.
	 * @return The number of columns.
	 */
	public int getColumnCount() {
		return columns;
	}

	/**
	 * Returns the tile given its coordinate
	 * @param i The row of the tile
	 * @param j The column of the tile
	 * @return The tile at position (i, j)
	 */
	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}

	/**
	 * @return the characters in the scene.
	 */
	public Set<Character> getCharacters() {
		return characters;
	}

	/**
	 * @return the player character in the scene.
	 */
	public Character getPlayer() {
		return player;
	}
	
	
}
