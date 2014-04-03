package com.madpanda.metalslug;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.madpanda.metalslug.screens.SplashScreen;
import com.madpanda.metalslug.screens.game.GameScreen;

public class App extends Game {
	
	@Override
	public void create() {		
		GameScreen game = new GameScreen();
		setScreen(new SplashScreen());
	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

}
