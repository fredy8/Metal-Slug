package com.madpanda.metalslug.screens.game.scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.App;
import com.madpanda.metalslug.screens.MainMenuScreen;
import com.madpanda.metalslug.screens.game.scene.character.Character;
import com.madpanda.metalslug.screens.game.scene.character.Enemy;
import com.madpanda.metalslug.screens.game.scene.character.Player;

/**
 * A scene in a game. Contains all the tiles and the players in the scene.
 * 
 */
public class Scene implements InputProcessor {

	private final Tile[][] tiles; // the game tiles
	private final int rows, columns; // the dimensions of the scene
	private Set<Character> characters; // the characters in the scene.
	private Player player; // the player character
	private static Scene instance;
	private OrthographicCamera camera;
	private int score;
	private Texture background;
	private Texture livesTextures;
	private Texture numbers[];
	private List<Character> characterRemovals;

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
		this.camera = camera;
		characters = new HashSet<>();
		instance = this;
		
		characterRemovals = new ArrayList<>();
		
		player = new Player(220, 100);
		addCharacter(player);

		Enemy e1 = new Enemy(new Texture("images/game/blob/blob4.png"), 500, 200, 4, false);
		addCharacter(e1);

		Enemy e2 = new Enemy(new Texture("images/game/blob/blob4.png"), 1000, 200, 4, false);
		addCharacter(e2);
		
		Enemy e3 = new Enemy(new Texture("images/game/blob/blob4.png"), 1700, 200, 4, false);
		addCharacter(e3);
		
		Enemy e4 = new Enemy(new Texture("images/game/blob/blob4.png"), 2500, 100, 4, false);
		addCharacter(e4);
		
		Enemy e5 = new Enemy(new Texture("images/game/blob/blob4.png"), 3200, 100, 4, false);
		addCharacter(e5);
		
		Enemy e6 = new Enemy(new Texture("images/game/skull/skull4.png"), 700, 300, 4, false);
		addCharacter(e6);

		Enemy e7 = new Enemy(new Texture("images/game/skull/skull4.png"), 1300, 300, 4, false);
		addCharacter(e7);
		
		Enemy e8 = new Enemy(new Texture("images/game/skull/skull4.png"), 2200, 300, 4, false);
		addCharacter(e8);
		
		Enemy e9 = new Enemy(new Texture("images/game/skull/skull4.png"), 2900, 300, 4, false);
		addCharacter(e9);
		
		Enemy e10 = new Enemy(new Texture("images/game/skull/skull4.png"), 3300, 300, 4, false);
		addCharacter(e10);
		
		Enemy e11 = new Enemy(new Texture("images/game/jefe5.png"), 3715, 300, 20, true);
		addCharacter(e11);
		
		if(characters.size() == 1) {
			((App) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
		}
		
		background = new Texture("images/game/background.png");
		livesTextures = new Texture("images/game/fullHeart.png");
		numbers = new Texture[10];
		for(int i = 0; i < 10; i++) {
			numbers[i] = new Texture("images/game/numbers/" + i + ".png");
		}
	}	

	// adds a character to the scene
	private void addCharacter(Character character) {
		characters.add(character);
	}

	// removes a character from the scene
	public void removeCharacter(Character character) {
		characterRemovals.add(character);
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
	public Player getPlayer() {
		return player;
	}

	public void update() {
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			if(!Gdx.input.isKeyPressed(Input.Keys.D)) {
				player.moveLeft();
			} else {
				player.stopMovement();
			}
		} else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.moveRight();
		} else {
			player.stopMovement();
		}
		
		Rectangle rect = player.getRectangle();
		
		camera.position.x = rect.x;
		camera.position.y = rect.y;
		camera.update();
		
		for(Character character : characterRemovals) {
			characters.remove(character);
		}
		
		characterRemovals.clear();
		
		for(Character character : characters) {
			character.update();
		}
		
	}
	
	/**
	 * Renders the characters of the scene.
	 */
	public void render(SpriteBatch batch) {
		
		batch.draw(background, 0, 0, background.getWidth()/background.getHeight()*350, 350);
		
		//render each character
		for(Character character : getCharacters()) {
			character.render(batch);
		}
	}

	/**
	 * Returns the current instance of the game.
	 * @return
	 */
	public static Scene getInstance() {
		return instance;
	}
	
	/**
	 * Returns the camera of the scene.
	 * @return - the camera of the scene.
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Input.Keys.W:
			player.jump();
			break;
		case Input.Keys.SPACE:
			player.shoot(350, .3f);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
