package com.madpanda.metalslug.screens.game;

import java.io.BufferedReader;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

public class GameEntityFactory {

	private GameEntityFactory() {
	}

	public static Scene createScene(int id) {
		
		BufferedReader reader = Gdx.files.internal("data/scenes/" + id + ".txt").reader(8192);

		Tile tiles[][];
		try(Scanner scanner = new Scanner(reader)) {
			tiles = new Tile[scanner.nextInt()][scanner.nextInt()];
			
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[i].length; j++) {
					tiles[i][j] = new Tile(false);
				}
			}
		}
		
		return new Scene(tiles);
	}

}
