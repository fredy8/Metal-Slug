package com.madpanda.metalslug.screens.game.scene;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;

public class Scene extends Entity {

	private final Tile[][] tiles;
	private final int rows, columns;
	private Set<Character> characters;
	
	public Scene(Tile[][] tiles) {
		this.tiles = tiles;
		this.rows = tiles.length;
		this.columns = tiles[0].length;
		this.setGraphicalComponent(new SceneRender(this));
		characters = new HashSet<>();
		addCharacter(new Character(new Rectangle(220, 100, Tile.SIZE*2, Tile.SIZE*3), this));
	}
	
	private void addCharacter(Character character) {
		addChild(character);
		characters.add(character);
	}
	
	private void removeCharacter(Character character) {
		removeChild(character);
		characters.remove(character);
	}

	public int getRowCount() {
		return rows;
	}
	
	public int getColumnCount() {
		return columns;
	}

	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}

	public Set<Character> getCharacters() {
		return characters;
	}
	
}
