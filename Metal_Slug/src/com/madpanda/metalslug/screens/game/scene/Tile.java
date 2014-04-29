package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.GameScreen;
import com.madpanda.metalslug.screens.game.components.graphical.RenderWithOffset;

public class Tile extends Entity {

	public static final int SIZE = 15; //The size of each tile
	private boolean obstacleX, obstacleY; //whether it is an obstacle horizontally and vertically
	
	/**
	 * Creates a new Tile
	 * @param obstacleX - whether the characters collide with this tile when moving horizontally
	 * @param obstacleY - whether the characters collide with this tile when moving vertically
	 */
	public Tile(boolean obstacleX, boolean obstacleY) {
		this.obstacleX = obstacleX;
		this.obstacleY = obstacleY;
		//sets the graphical component to the obstacleview of the scene
		this.setGraphicalComponent(new ObstacleView(this));
	}
	
	/**
	 * @return - whether this tile is an obstacle when moving horizontally
	 */
	public boolean isObstacleX() {
		return obstacleX;
	}

	/**
	 * @return - whether this tile is an obstacle when moving vertically
	 */
	public boolean isObstacleY() {
		return obstacleY;
	}
	
	/**
	 * A graphical component for a tile where each tile is rendered black if it is an obstacle, red otherwise
	 */
	public static class ObstacleView extends RenderWithOffset {

		/**
		 * Creates a new obstacle view for the tile
		 * @param tile - the tile to be rendered
		 */
		public ObstacleView(Tile tile) {
			super(tile);
		}

		/**
		 * The tile is rendered black if it is an obstacle, red otherwise.
		 */
		@Override
		public void render(SpriteBatch batch, Vector2 offset) { }

		@Override
		public void renderShapes(Vector2 offset) {
			ShapeRenderer renderer = GameScreen.getShapeRenderer();
			renderer.setColor(Color.MAGENTA);
			if (!((Tile) this.getEntity()).isObstacleX() && !((Tile) this.getEntity()).isObstacleY()) {
				renderer.setColor(Color.RED);
			}
			renderer.begin(ShapeType.Filled);
			renderer.rect((float) offset.x, (float) offset.y, SIZE, SIZE);
			renderer.end();
		}
		
	}
	
}
