package com.madpanda.metalslug.screens.game;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.madpanda.metalslug.screens.game.scene.Scene;
import com.madpanda.metalslug.screens.game.scene.Tile;

/**
 * Static class for created GameEntities given their id.
 * GameEntities are initialized from files.
 *
 */
public class GameEntityFactory {

	//disable instantiation
	private GameEntityFactory() { }

	/**
	 * Creates a new scene from a file given its id and assigns a camera to the scene.
	 * @param id - The id of the scene to be created
	 * @param camera - The camera that will display the scene.
	 * @return - The created scene
	 */
	public static Scene createScene(int id, OrthographicCamera camera, int lives) {
		Tile tiles[][]; //the tiles of the new scene
		
		//read from the scene data folder
		try(Scanner scanner = new Scanner(Gdx.files.internal("data/scenes/" + id + ".txt").reader(8192))) {
			tiles = new Tile[scanner.nextInt()][scanner.nextInt()];
			
			//reads every tile from the file
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[i].length; j++) {
					int type = scanner.nextInt();
					//created the type of tile depending on the information read.
					tiles[tiles.length - 1 - i][j] = new Tile((type & 1) != 0, (type & 3) != 0);
				}
			}
		}
		
		return new Scene(tiles, camera, id, lives);
	}

}
