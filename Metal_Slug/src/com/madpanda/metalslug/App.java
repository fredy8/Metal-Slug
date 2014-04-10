package com.madpanda.metalslug;

import com.badlogic.gdx.Game;
import com.madpanda.metalslug.screens.SplashScreen;

/**
 * The launcher class for the app, handles the app events and manages the game screens.
 *
 */
public class App extends Game {
	
	@Override
	public void create() {		
		setScreen(new SplashScreen());
	}

}
