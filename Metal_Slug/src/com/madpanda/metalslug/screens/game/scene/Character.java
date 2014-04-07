package com.madpanda.metalslug.screens.game.scene;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.madpanda.metalslug.screens.game.Entity;
import com.madpanda.metalslug.screens.game.components.graphical.BodyRender;
import com.madpanda.metalslug.screens.game.components.input.InputComponent;
import com.madpanda.metalslug.screens.game.components.physical.MovingBody;


public class Character extends Entity {

	public Character(Rectangle rectangle, Scene scene) {
		this.setPhysicalComponent(new CharacterPhysics(this, rectangle, scene));
		this.setGraphicalComponent(new BodyRender(this, Color.BLUE));
		this.setInputComponent(new CharacterInput());
		MovingBody body = (MovingBody) this.getPhysicalComponent();
		
		body.setSpeed(new Vector2(0, 0));
		//body.setAcceleration(new Vector2(0, -200));
	}

	public class CharacterInput extends InputComponent {

		public CharacterInput() {
			super(Character.this);
		}

		@Override
		public boolean keyDown(int keycode) {
			Character character = (Character) getEntity();
			MovingBody body = (MovingBody) character.getPhysicalComponent();
			switch (keycode) {
			case Input.Keys.W:
				body.setSpeed(new Vector2(0, 200));
				return true;
			case Input.Keys.S:
				body.setSpeed(new Vector2(0, -200));
				return true;
			case Input.Keys.A:
				body.setSpeed(new Vector2(-200, 0));
				return true;
			case Input.Keys.D:
				body.setSpeed(new Vector2(200, 0));
				return true;
			}
			return false;
		}
		
		
		
	}
	
	
}
