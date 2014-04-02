package com.madpanda.metalslug.screens.game.components.input;

import com.badlogic.gdx.InputProcessor;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.Component;

public class InputComponent extends Component implements InputProcessor {

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
