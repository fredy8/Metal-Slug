package com.madpanda.metalslug.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madpanda.metalslug.App;
import com.madpanda.metalslug.TweenAccessors.SpriteTween;

public class SplashScreen extends AbstractScreen {
	
	/**
	 * Screen which displays logo.
	 * Fades in then out. Then swaps to the MainMenu.
	 */
	
	Texture splashTexture;
	Texture blackfade;
	Sprite splashSprite;
	TweenManager manager;
	
	public SplashScreen() {
		
		//Texture(logo) loaded
		splashTexture =new Texture("menu/images/logo.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//Logo positioning 
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1,1,1,0);
		float aspectRatio = splashSprite.getWidth() / splashSprite.getHeight();
		splashSprite.setBounds(getCamera().viewportWidth/2 - 100, getCamera().viewportHeight/2 - 100 / aspectRatio, 200, 200 / aspectRatio);
		
		//Sets up animations to start
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		manager = new TweenManager();
		
		TweenCallback cb = new TweenCallback(){
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();//called when finished
			}
			
		};
		//Animations starts
		Tween.to(splashSprite, SpriteTween.ALPHA, 2f).target(1)
		.ease(TweenEquations.easeInQuad)
		.repeatYoyo(1, 1.5f)//Repeats animation backwards
		.setCallback(cb)
		.setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(Gdx.graphics.getDeltaTime());
		
		//Checks if screen got touched, to skip to the MainMenu screen.
		if(Gdx.input.justTouched()){
			((App) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
		}
		if(Gdx.input.isKeyPressed(Input.Buttons.LEFT)){
			
		}

		splashSprite.draw(batch);
	}
	
	private void tweenCompleted(){
		//Called when animation finished, sets screen to MainMenu.
		Gdx.app.log("MENUS", "SplashScreen Complete");
		((App) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
	}	
	
}
