package com.madpanda.metalslug.screens.game.scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.App;
import com.madpanda.metalslug.screens.MainMenuScreen;
import com.madpanda.metalslug.screens.game.GameEntityFactory;
import com.madpanda.metalslug.screens.game.GameScreen;
import com.madpanda.metalslug.screens.game.TextureManager;
import com.madpanda.metalslug.screens.game.scene.character.Character;
import com.madpanda.metalslug.screens.game.scene.character.Enemy;
import com.madpanda.metalslug.screens.game.scene.character.Player;
import com.madpanda.metalslug.screens.game.scene.character.PowerUp;
import com.madpanda.metalslug.screens.game.scene.character.PowerUp.PowerUpType;

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
	private Texture background;
	private List<Character> characterRemovals;
	private int level;
	private List<PowerUp> powerups, removePowerups;

	/**
	 * Creates a new Scene given the tiles information and the camera to display
	 * the scene.
	 * 
	 * @param tiles
	 *            - The information of each of the tiles of the scene.
	 * @param camera
	 *            - The camera to display the scene.
	 */
	public Scene(Tile[][] tiles, OrthographicCamera camera, int level, int lives) {
		this.tiles = tiles;
		this.rows = tiles.length;
		this.columns = tiles[0].length;
		this.camera = camera;
		this.level = level;
		characters = new HashSet<>();
		instance = this;
				
		camera.position.x = camera.viewportWidth/2;
		camera.position.y = camera.viewportHeight/2;
		camera.update();
		
		characterRemovals = new ArrayList<>();
		powerups = new ArrayList<>();
		removePowerups = new ArrayList<>();
		
		if(level == 1) {
			player = new Player(220, 100, lives);
			addCharacter(player);
	
			Enemy e1 = new Enemy("images/game/blob/blob", 7, 500, 200, 4, false);
			addCharacter(e1);
	
			Enemy e2 = new Enemy("images/game/blob/blob", 7, 1000, 200, 4, false);
			addCharacter(e2);
			
			Enemy e3 = new Enemy("images/game/blob/blob", 7, 1700, 200, 4, false);
			addCharacter(e3);
			
			Enemy e4 = new Enemy("images/game/blob/blob", 7, 2500, 100, 4, false);
			addCharacter(e4);
			
			Enemy e5 = new Enemy("images/game/blob/blob", 7, 3200, 100, 4, false);
			addCharacter(e5);
			
			Enemy e6 = new Enemy("images/game/blob/blob", 7, 700, 300, 4, false);
			addCharacter(e6);
	
			Enemy e7 = new Enemy("images/game/blob/blob", 7, 1300, 300, 4, false);
			addCharacter(e7);
			
			Enemy e8 = new Enemy("images/game/blob/blob", 7, 2200, 300, 4, false);
			addCharacter(e8);
			
			Enemy e9 = new Enemy("images/game/blob/blob", 7, 2900, 300, 4, false);
			addCharacter(e9);
			
			Enemy e10 = new Enemy("images/game/blob/blob", 7, 3300, 300, 4, false);
			addCharacter(e10);
			
			Enemy e11 = new Enemy("images/game/Rhyn/rhyn", 8, 3715, 300, 15, true);
			addCharacter(e11);
			
			background = TextureManager.getTexture("images/game/background1.png");
		} else if(level == 2) {
			player = new Player(220, 100, lives);
			addCharacter(player);
	
			Enemy e1 = new Enemy("images/game/skull/skull", 7, 500, 200, 5, false);
			addCharacter(e1);
	
			Enemy e2 = new Enemy("images/game/bomb/bomb", 8, 1000, 200, 5, false);
			addCharacter(e2);
			
			Enemy e3 = new Enemy("images/game/skull/skull", 7, 1700, 200, 5, false);
			addCharacter(e3);
			
			Enemy e4 = new Enemy("images/game/bomb/bomb", 8, 2500, 100, 5, false);
			addCharacter(e4);
			
			Enemy e5 = new Enemy("images/game/skull/skull", 7, 3200, 100, 5, false);
			addCharacter(e5);
			
			Enemy e6 = new Enemy("images/game/bomb/bomb", 8, 700, 300, 5, false);
			addCharacter(e6);
	
			Enemy e7 = new Enemy("images/game/skull/skull", 7, 1300, 300, 5, false);
			addCharacter(e7);
			
			Enemy e8 = new Enemy("images/game/bomb/bomb", 8, 2200, 300, 5, false);
			addCharacter(e8);
			
			Enemy e9 = new Enemy("images/game/skull/skull", 7, 2900, 300, 5, false);
			addCharacter(e9);
			
			Enemy e10 = new Enemy("images/game/bomb/bomb", 8, 3300, 300, 5, false);
			addCharacter(e10);
			
			Enemy e11 = new Enemy("images/game/Alien/alien", 8, 3715, 300, 20, true);
			addCharacter(e11);
			
			background = TextureManager.getTexture("images/game/background2.png");
		} else if(level == 3) {
			player = new Player(180, 100, lives);
			addCharacter(player);
			
			PowerUp tank = new PowerUp(290, 50, PowerUpType.Tank);
			addPowerUp(tank);
	
			Enemy e1 = new Enemy("images/game/ghost/ghost", 4, 500, 200, 6, false);
			addCharacter(e1);
	
			Enemy e2 = new Enemy("images/game/brain/brain", 8, 1000, 200, 6, false);
			addCharacter(e2);
			
			Enemy e3 = new Enemy("images/game/ghost/ghost", 4, 1700, 200, 6, false);
			addCharacter(e3);
			
			Enemy e4 = new Enemy("images/game/brain/brain", 8, 2500, 100, 6, false);
			addCharacter(e4);
			
			Enemy e5 = new Enemy("images/game/ghost/ghost", 4, 3200, 100, 6, false);
			addCharacter(e5);
			
			Enemy e6 = new Enemy("images/game/brain/brain", 8, 700, 300, 6, false);
			addCharacter(e6);
	
			Enemy e7 = new Enemy("images/game/ghost/ghost", 4, 1300, 300, 6, false);
			addCharacter(e7);
			
			Enemy e8 = new Enemy("images/game/brain/brain", 8, 2200, 300, 6, false);
			addCharacter(e8);
			
			Enemy e9 = new Enemy("images/game/ghost/ghost", 4, 2900, 300, 6, false);
			addCharacter(e9);
			
			Enemy e10 = new Enemy("images/game/brain/brain", 8, 3300, 300, 6, false);
			addCharacter(e10);
			
			Enemy e11 = new Enemy("images/game/Skeletol/jefe", 6, 3715, 300, 25, true);
			addCharacter(e11);
			
			background = TextureManager.getTexture("images/game/background3.png");
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
		
		if(characters.size() == 1) {
			if(level != 3) {
				Scene nextScene = GameEntityFactory.createScene(level+1, camera, player.getLivesCount());
				GameScreen.getInstance().setScene(nextScene);
				nextScene.getPlayer().setScore(player.getScore());
				return;
			} else {
				((App) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
				GameScreen.getInstance().getMusic().stop();
				Preferences prefs = Gdx.app.getPreferences("Highscore");
				
				int highscore = prefs.getInteger("highscore");
				if(player.getScore() > highscore) {
					prefs.putInteger("highscore", player.getScore());
				}
				
				prefs.flush();
				return;
			}
		}
		
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
		
		if(rect.x - camera.viewportWidth/2 > 0 && rect.x + camera.viewportWidth/2 < tiles[0].length * Tile.SIZE) {
			camera.position.x = rect.x;
		}
		if(rect.y - camera.viewportHeight/2 > 0 && rect.y + camera.viewportHeight/2 < tiles.length * Tile.SIZE) {
			camera.position.y = rect.y;
		}
		camera.update();
		
		for(Character character : characterRemovals) {
			characters.remove(character);
		}
		
		characterRemovals.clear();
		
		for(PowerUp powerup : removePowerups) {
			powerups.remove(powerup);
		}
		
		removePowerups.clear();
		
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
		
		for(PowerUp powerup: powerups) {
			powerup.render(batch);
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
			player.shoot(350, player.getShootingDelay(), player.getBulletStrength(), .8f);
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
	
	public int getLevel() {
		return level;
	}
	
	public void addPowerUp(PowerUp powerup) {
		powerups.add(powerup);
	}
	
	public void removePowerUp(PowerUp powerup) {
		removePowerups.add(powerup);
	}
	
	public List<PowerUp> getPowerUps() {
		return powerups;
	}
	
}
