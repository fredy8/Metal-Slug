package com.madpanda.metalslug;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Metal Slug";
		cfg.width = 538;
		cfg.height = 320;
		
		new LwjglApplication(new App(), cfg);
	}
}
