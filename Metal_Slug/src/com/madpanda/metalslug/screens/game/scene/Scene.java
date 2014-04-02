package com.madpanda.metalslug.screens.game.scene;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;

public class Scene extends Entity {

	private final Tile[][] tiles;
	private final int rows, columns;
	private Set<Character> characters;
	
	public Scene(int rows, int columns) {
		tiles = new Tile[rows][columns];
		this.rows = rows;
		this.columns = columns;
		loadTiles();
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
	
	private void loadTiles() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(i != j) {
					tiles[i][j] = Tile.NullTile;
				}
			}
		}
		for(int i = 0; i < rows; i++) {
			tiles[i][i] = new Tile(true);
		}
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
