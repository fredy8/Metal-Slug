package com.madpanda.metalslug.screens.game.components.input;

import com.badlogic.gdx.InputProcessor;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.Component;

/**
 * The input component for defining the processing of the input for the entity.
 * By default, the entity will delegate the all input callbacks to its children.
 * Each callback returns true if the input was processed, if so, the callback will not be delegated to
 * its children.
 */
public class InputComponent extends Component implements InputProcessor {

	/**
	 * Created a new InputComponent given the containing entity.
	 * @param entity - The containing entity.
	 */
	public InputComponent(Entity entity) {
		super(entity);
	}

	@Override
	public boolean keyDown(int keycode) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().keyDown(keycode);
		}
		return processed;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().keyUp(keycode);
		}
		return processed;
	}

	@Override
	public boolean keyTyped(char character) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().keyTyped(character);
		}
		return processed;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().touchDown(screenX, screenY, pointer, button);
		}
		return processed;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().touchUp(screenX, screenY, pointer, button);
		}
		return processed;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().touchDragged(screenX, screenY, pointer);
		}
		return processed;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().mouseMoved(screenX, screenY);
		}
		return processed;
	}

	@Override
	public boolean scrolled(int amount) {
		boolean processed = false;
		for(Entity entity : getEntity().getChildren()) {
			processed |= entity.getInputComponent().scrolled(amount);
		}
		return processed;
	}
	
}
