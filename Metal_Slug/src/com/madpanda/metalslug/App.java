package com.madpanda.metalslug;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.madpanda.metalslug.screens.SplashScreen;

public class App extends Game {
	
	@Override
	public void create() {		
		setScreen(new SplashScreen());
	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

}
