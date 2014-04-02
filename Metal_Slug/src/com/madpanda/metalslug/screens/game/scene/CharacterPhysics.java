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
		System.out.println(getSpeed());
		if (getSpeed().x > 0) {
			int[] lowerRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y);
			int[] upperRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = lowerRight[0]; i < scene.getColumnCount() && !foundObstacle; i++) {
				for(int j = lowerRight[1]; j < upperRight[1] && !foundObstacle; j++) {
					if(scene.getTile(i, j).isObstacle()) {
						if(i*Tile.SIZE - getRectangle().x - getRectangle().width < vector.x) {
							vector.x = i*Tile.SIZE - getRectangle().x - getRectangle().width;
							foundObstacle = true;
						}
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
						if((i+2)*Tile.SIZE - getRectangle().x > vector.x) {
							foundObstacle = true;
							vector.x = (i+2)*Tile.SIZE - getRectangle().x; 
						}
					}
				}
			}
		}
		
		super.move(vector.cpy().scl(1, 0));
		
		if (getSpeed().y > 0) {
			int[] upperLeft = getTilePosition(getRectangle().x, getRectangle().y + getRectangle().height);
			int[] upperRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y + getRectangle().height);
			boolean foundObstacle = false;
			for(int i = upperLeft[1]; i < scene.getRowCount() && !foundObstacle; i++) {
				for(int j = upperLeft[0]; j < upperRight[0] && !foundObstacle; j++) {
					if(scene.getTile(j, i).isObstacle()) {
						if(j*Tile.SIZE - getRectangle().y - getRectangle().height < vector.y) {
							System.out.println("COLLISION");
							vector.y = j*Tile.SIZE - getRectangle().y - getRectangle().height;
							foundObstacle = true;
							getSpeed().y = 0;
						}
					}
				}
			}
		} else if (getSpeed().y < 0) {
			int[] lowerLeft = getTilePosition(getRectangle().x, getRectangle().y);
			int[] lowerRight = getTilePosition(getRectangle().x + getRectangle().width, getRectangle().y);
			boolean foundObstacle = false;
			for(int i = lowerLeft[1]; i >= 0 && !foundObstacle; i--) {
				for(int j = lowerLeft[0]; j < lowerRight[0] && !foundObstacle; j++) {
					if(scene.getTile(j, i).isObstacle()) {
						if((j+2)*Tile.SIZE - getRectangle().y > vector.y) {
							vector.y = (j+2)*Tile.SIZE - getRectangle().y;
							foundObstacle = true;
							getSpeed().y = 0;
						}
					}
				}
			}
		}
		super.move(vector.cpy().scl(0, 1));
	}
	
	private int[] getTilePosition(float x, float y) {
		return new int[] { (int) (x/Tile.SIZE), (int) (y / Tile.SIZE) };
	}
	
	

}
