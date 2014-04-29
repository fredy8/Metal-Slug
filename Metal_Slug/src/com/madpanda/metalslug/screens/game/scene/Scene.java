package com.madpanda.metalslug.screens.game.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.physical.Body;
import com.madpanda.metalslug.screens.game.scene.character.Character;
import com.madpanda.metalslug.screens.game.scene.character.Enemy;
import com.madpanda.metalslug.screens.game.scene.character.Player;

/**
 * A scene in a game. Contains all the tiles and the players in the scene.
 * 
 */
public class Scene extends Entity {

	private final Tile[][] tiles; // the game tiles
	private final int rows, columns; // the dimensions of the scene
	private Set<Character> characters; // the characters in the scene.
	private Player player; // the player character
	private Map<Bullet, Character> bullets;
	private static Scene instance;
	private OrthographicCamera camera;
	private int score;

	/**
	 * Creates a new Scene given the tiles information and the camera to display
	 * the scene.
	 * 
	 * @param tiles
	 *            - The information of each of the tiles of the scene.
	 * @param camera
	 *            - The camera to display the scene.
	 */
	public Scene(Tile[][] tiles, OrthographicCamera camera) {
		this.tiles = tiles;
		this.rows = tiles.length;
		this.columns = tiles[0].length;
		this.setGraphicalComponent(new SceneRender(this));
		this.camera = camera;
		characters = new HashSet<>();
		bullets = new HashMap<>();
		instance = this;
		
		player = new Player(this, 220, 100);
		addCharacter(player);

		Enemy e1 = new Enemy(this, 500, 200, "images/game/blob/blob4.png", 4);
		addCharacter(e1);

		Enemy e2 = new Enemy(this, 1000, 200, "images/game/blob/blob4.png", 4);
		addCharacter(e2);
		
		Enemy e3 = new Enemy(this, 1700, 200, "images/game/blob/blob4.png", 4);
		addCharacter(e3);
		
		Enemy e4 = new Enemy(this, 2500, 100, "images/game/blob/blob4.png", 4);
		addCharacter(e4);
		
		Enemy e5 = new Enemy(this, 3200, 100, "images/game/blob/blob4.png", 4);
		addCharacter(e5);
		
		Enemy e6 = new Enemy(this, 700, 300, "images/game/skull/skull4.png", 4);
		addCharacter(e6);

		Enemy e7 = new Enemy(this, 1300, 300, "images/game/skull/skull4.png", 4);
		addCharacter(e7);
		
		Enemy e8 = new Enemy(this, 2200, 300, "images/game/skull/skull4.png", 4);
		addCharacter(e8);
		
		Enemy e9 = new Enemy(this, 2900, 300, "images/game/skull/skull4.png", 4);
		addCharacter(e9);
		
		Enemy e10 = new Enemy(this, 3600, 300, "images/game/skull/skull4.png", 4);
		addCharacter(e10);
		
		Enemy e11 = new Enemy(this, 3800, 300, "images/game/jefe5.png", 20);
		addCharacter(e11);
		
		setUpdateComponent(new CameraLockTarget(camera, this, new Rectangle(camera.viewportWidth / 4,
				camera.viewportHeight / 4, camera.viewportWidth / 2,
				camera.viewportHeight / 2)));
	}

	// adds a character to the scene
	private void addCharacter(Character character) {
		addChild(character);
		characters.add(character);
	}

	// removes a character from the scene
	public void removeCharacter(Character character) {
		removeChild(character);
		characters.remove(character);
	}

	/**
	 * Returns the number of rows in the scene.
	 * 
	 * @return The number of rows.
	 */
	public int getRowCount() {
		return rows;
	}

	/**
	 * Returns the number of columns in the scene.
	 * 
	 * @return The number of columns.
	 */
	public int getColumnCount() {
		return columns;
	}

	/**
	 * Returns the tile given its coordinate
	 * 
	 * @param i
	 *            The row of the tile
	 * @param j
	 *            The column of the tile
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

	public void update() {
		
		for(Character character : characters) {
			character.update();
		}
		
		List<Bullet> deadBullets = new ArrayList<>();
		
		Iterator<Character> it = characters.iterator();
		while(it.hasNext()) {
			Character c = it.next();
			if(c.dead()) {
				removeChild(c);
				it.remove();
			}
		}
		
		for(Bullet bullet : bullets.keySet()) {
			Rectangle bRect = ((Body)bullet.getPhysicalComponent()).getRectangle();
			for(Character character : characters) {
				if(bullet.dead()) {
					deadBullets.add(bullet);
				} else if(bullets.get(character) != character) {
					Rectangle cRect = ((Body)character.getPhysicalComponent()).getRectangle();
					if(bRect.overlaps(cRect)) {
						character.hit(bullet);
						bullet.die();
						deadBullets.add(bullet);
					}
				}
			}
		}
		
		for(Bullet bullet : deadBullets) {
			bullets.remove(bullet);
		}
		
	}
	
	public void increaseScore() {
		score += 10;
	}
	
	public int getScore() {
		return score;
	}

	public static Scene getInstance() {
		return instance;
	}
	
	public void addBullet(Bullet bullet, Character character) {
		bullets.put(bullet, character);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
}
