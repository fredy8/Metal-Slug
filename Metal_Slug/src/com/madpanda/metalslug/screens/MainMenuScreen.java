package com.madpanda.metalslug.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.madpanda.metalslug.App;
import com.madpanda.metalslug.TweenAccessors.ActorAccessor;
import com.madpanda.metalslug.screens.game.GameScreen;

public class MainMenuScreen extends AbstractScreen {
	
	/**
	 * MainMenu Screen.
	 * Contains buttons to start game.
	 * 
	 */
	
	private Stage stage;
	private TextureAtlas atlas;
	
	private Skin skin;
	private Table table;
	private TextButton buttonPlay, buttonOptions;
	private BitmapFont minecrafter, minecraftia;
	private Label heading;
	private TweenManager tweenManager;
	

	public MainMenuScreen() {
		stage = new Stage();//Initializes stage
		
		Gdx.input.setInputProcessor(stage);//Enables controls on the stage.
		
		atlas = new TextureAtlas("menu/buttons/blacknorange.pack");//Loads button atlas. (Texture)
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Creating fonts
		minecrafter = new BitmapFont(Gdx.files.internal("menu/fonts/minecrafter.fnt"));
		minecraftia = new BitmapFont(Gdx.files.internal("menu/fonts/minecraftia.fnt"));
		
		
		//creating textbuttonstyle
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button2");		  //Sets button2 texture for when the button is up.
		textButtonStyle.down = skin.getDrawable("button2pressed");//Sets button2pressed texture for when the button is down.
		textButtonStyle.pressedOffsetX = 1; //Pressed down effect.
		textButtonStyle.pressedOffsetY = -1;//Pressed down effect.
		textButtonStyle.font = minecraftia; //Sets buttons font
		textButtonStyle.font.setScale(2f);  //Sets buttons font size
		
		//Creating play button.
		buttonPlay = new TextButton("Play", textButtonStyle);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("START", "Starting...");
				MainMenuScreen.this.dispose();
				((App) Gdx.app.getApplicationListener()).setScreen(new GameScreen());//Changes screen to game screen
			}
		});
		buttonPlay.pad(20);
		
		//Creating options button
		buttonOptions = new TextButton("Options", textButtonStyle);
		buttonOptions.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("OPTIONS", "Options...");
				MainMenuScreen.this.dispose();
				((App) Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());//Changes screen to OptionsScreen.
			}
		});
		buttonOptions.pad(20);
		
		
		//Creating heading(title)
		LabelStyle headingStyle = new LabelStyle(minecrafter, Color.WHITE);//Font style and color.
		heading = new Label("Guacamole", headingStyle); //heading text
		
		heading.setFontScale(3f);//Heading font Size
		
		//Creating sprite for MainMenu
		/*texture = new Texture("player/character2.png");
		Image walker = new Image(texture);
		
		putting stuff together
		table.add(walker).size(250);
		table.getCell(walker).spaceBottom(50);
		table.row();
		*/
		table.add(heading).spaceBottom(25);
		table.row();
		table.add(buttonPlay).size(160, 40);
		table.getCell(buttonPlay).spaceBottom(13);
		table.row();
		table.add(buttonOptions).size(160, 40);
		table.getCell(buttonOptions).spaceBottom(13);
		table.row();
		table.debug();//debug
		stage.addActor(table);
		
		//Creating animations
		tweenManager = new TweenManager();//Initialize tweenManager to manage tweens
		Tween.registerAccessor(Actor.class , new ActorAccessor());
		
		Timeline.createSequence().beginSequence()//Fade in animations
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))//sets starting alpha(0)
			.push(Tween.set(buttonOptions, ActorAccessor.ALPHA).target(0))
			.beginParallel()
			.push(Tween.from(heading, ActorAccessor.ALPHA, 1f).target(0))
			.pushPause(350)
			.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 1f).target(1))
			.pushPause(350)
			.push(Tween.to(buttonOptions, ActorAccessor.ALPHA, 1f).target(1))
			.pushPause(350)
			.end()
			.end().start(tweenManager);
		
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);//Jump in animation
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(Gdx.graphics.getDeltaTime());//Updates tween
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);//debug
	}

	@Override
	public void dispose() {
		super.dispose();
	}	
	
}
