package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;

public class CharacterPhysics extends MovingBody {

	private Scene scene;
	
	public CharacterPhysics(Entity entity, Rectangle rectangle, Scene scene) {
		super(entity, rectangle);
		this.scene = scene;
	}
	
	@Override
	public void move(Vector2 vector) {
		if (getSpeed().x > 0) {
			int[] lowerRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y);
			int[] upperRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = lowerRight[0]; i < scene.getColumnCount() && !foundObstacle; i++) {
				for(int j = lowerRight[1]; j < upperRight[1] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacle()) {
						vector.x = Math.min(vector.x, i*Tile.SIZE - getRectangle().x - getRectangle().width);
					}
				}
			}
		} else if (getSpeed().x < 0) {
			int[] lowerLeft = getTilePosition(getRectangle().x, getRectangle().y);
			int[] upperLeft = getTilePosition(getRectangle().x, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = lowerLeft[0]; i >= 0 && !foundObstacle; i--) {
				for(int j = lowerLeft[1]; j < upperLeft[1] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacle()) {
						vector.x = Math.max(vector.x, getRectangle().x - (i+1)*Tile.SIZE);
					}
				}
			}
		}
		
		if (getSpeed().y > 0) {
			int[] upperLeft = getTilePosition(getRectangle().x, getRectangle().y + getRectangle().height);
			int[] upperRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = upperLeft[1]; i < scene.getRowCount() && !foundObstacle; i++) {
				for(int j = upperLeft[0]; j < upperRight[0] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacle()) {
						vector.y = Math.min(vector.y, i*Tile.SIZE - getRectangle().y - getRectangle().height);
					}
				}
			}
		} else if (getSpeed().y < 0) {
			int[] lowerLeft = getTilePosition(getRectangle().x, getRectangle().y);
			int[] lowerRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y);
			boolean foundObstacle = false;
			for(int i = lowerLeft[1]; i >= 0 && !foundObstacle; i--) {
				for(int j = lowerLeft[0]; j < lowerRight[0] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacle()) {
						vector.y = Math.max(vector.y, getRectangle().y - (i+1)*Tile.SIZE);
					}
				}
			}
		}
		super.move(vector);
	}
	
	private int[] getTilePosition(float x, float y) {
		return new int[] { (int) (x/Tile.SIZE), (int) (y / Tile.SIZE) };
	}
	
	

}
