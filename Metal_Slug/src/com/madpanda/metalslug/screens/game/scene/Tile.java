package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.GameScreen;
import com.madpanda.metalslug.screens.game.components.graphical.RenderWithOffset;

public class Tile extends Entity {

	public static final int SIZE = 15;
	private boolean obstacleX, obstacleY;
	
	public static final Tile NullTile = new Tile(false, false); //TODO change to ImmutableTile
	
	public Tile(boolean obstacleX, boolean obstacleY) {
		this.obstacleX = obstacleX;
		this.obstacleY = obstacleY;
		this.setGraphicalComponent(new ObstacleView(this));
	}
	
	public Tile(Sprite sprite) {
		this(false, false);
	}
	
	public boolean isObstacleX() {
		return obstacleX;
	}
	
	public boolean isObstacleY() {
		return obstacleY;
	}
	
	public static class ObstacleView extends RenderWithOffset {

		public ObstacleView(Tile tile) {
			super(tile);
		}

		@Override
		public void render(SpriteBatch batch, Vector2 offset) {
			if (!((Tile) this.getEntity()).isObstacleX() && !((Tile) this.getEntity()).isObstacleY()) {
				ShapeRenderer renderer = GameScreen.getShapeRenderer();
				renderer.setColor(Color.RED);
				renderer.begin(ShapeType.Filled);
				renderer.rect((float) offset.x, (float) offset.y, SIZE, SIZE);
				renderer.end();
			}
		}
		
	}
	
}
