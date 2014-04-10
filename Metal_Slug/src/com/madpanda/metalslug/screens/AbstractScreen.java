package com.madpanda.metalslug.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * The base class for screens.
 * Contains the batch in which the screen draws and a camera.
 */
public class AbstractScreen implements Screen, InputProcessor {
	
	private SpriteBatch batch; //the batch for drawing to the screen
	private OrthographicCamera camera; //the screen's camera
	protected static ShapeRenderer shapeRenderer = new ShapeRenderer(); //used for rendering shapes
	
	public AbstractScreen() {
		//init the batch and camera
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 568, 320);
		Gdx.gl.glClearColor(0, 0, 0, 1); //set clear color to black
	}
	
	/**
	 * Called every frame and used to update the components of the screen
	 */
	public void update() { }
	
	/**
	 * Called every frame and used to draw objects to the screen
	 * @param batch - The batch that is displayed to the screen
	 */
	public void render(SpriteBatch batch) { }
	
	/**
	 * Called every frame and used to draw shapes to the screen
	 */
	public void renderShapes() { }
	
	@Override
	public final void render(float delta) {
		update(); //update before rendering
		
		//render
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears the screen
		//applies the camera to the batch and the shape renderer
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);
		
		renderShapes();
		
		//renders the batch
		batch.begin();
		render(batch);
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) { }

	@Override
	public void show() { }

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) { return false; }

	@Override
	public boolean keyUp(int keycode) { return false; }

	@Override
	public boolean keyTyped(char character) { return false; }

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false; }

	@Override
	public boolean scrolled(int amount) { return false; }
	
	protected final OrthographicCamera getCamera() {
		return camera;
	}
	
	/**
	 * Returns the shape renderer
	 * @return - the renderer for drawing shapes
	 */
	public static ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
}