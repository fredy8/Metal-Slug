package com.madpanda.metalslug.screens.game;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class GameEntityFactory {

	private GameEntityFactory() {
	}

	public static Scene createScene(int id) {
		Tile tiles[][];
		try(Scanner scanner = new Scanner(Gdx.files.internal("data/scenes/" + id + ".txt").reader(8192))) {
			tiles = new Tile[scanner.nextInt()][scanner.nextInt()];
			
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[i].length; j++) {
					tiles[tiles.length - 1 - i][j] = new Tile(scanner.nextInt() == 1);
				}
			}
		}
		
		return new Scene(tiles);
	}

}
