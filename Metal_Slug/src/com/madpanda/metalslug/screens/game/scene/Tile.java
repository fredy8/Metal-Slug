package com.madpanda.metalslug.screens.game.scene;


public class Tile {

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
	
}
