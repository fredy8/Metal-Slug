package com.madpanda.metalslug.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
	
	private Stage stage;
	private TextureAtlas atlas;
	
	private Skin skin;
	private Table table;
	private TextButton buttonPlay, buttonOptions;// buttonHighscore, buttonAchievements;
	private BitmapFont minecrafter, minecraftia;
	private Label heading;
	private TweenManager tweenManager;
	

//	private Texture texture;
	

	public MainMenuScreen() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(false);//back button disable
		
		atlas = new TextureAtlas("menu/buttons/blacknorange.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//creating fonts
		minecrafter = new BitmapFont(Gdx.files.internal("menu/fonts/minecrafter3.2.fnt"));
//		minecrafter.getRegion().getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		minecraftia = new BitmapFont(Gdx.files.internal("menu/fonts/minecraftia.fnt"));
//		minecraftia.getRegion().getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		
		
		//creating textbuttonstyle
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button2");
		textButtonStyle.down = skin.getDrawable("button2pressed");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = minecraftia;
		textButtonStyle.font.setScale(2f);
		
		//creating playbutton
		buttonPlay = new TextButton("Play", textButtonStyle);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("START", "Starting...");
				MainMenuScreen.this.dispose();
				((App) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
		buttonPlay.pad(20);
		
		buttonOptions = new TextButton("Options", textButtonStyle);
		buttonOptions.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("OPTIONS", "Options...");
//				MainMenuScreen.this.dispose();
				((App) Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
			}
		});
		buttonOptions.pad(20);
		
		
		//creating heading
		LabelStyle headingStyle = new LabelStyle(minecrafter, Color.WHITE);
		heading = new Label("INSERT NaME HERE", headingStyle);
		
		heading.setFontScale(3f);
		
		//creating sprite
//		texture = new Texture("player/character2.png");
//		Image walker = new Image(texture);
		
		//putting stuff together
//		table.add(walker).size(250);
//		table.getCell(walker).spaceBottom(50);
//		table.row();
		table.add(heading).spaceBottom(25);
		table.row();
		table.add(buttonPlay).size(260, 80);
		table.getCell(buttonPlay).spaceBottom(13);
		table.row();
		table.add(buttonOptions).size(260, 80);
		table.getCell(buttonOptions).spaceBottom(13);
		table.row();
		table.debug();//debug
		stage.addActor(table);
		
		//creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class , new ActorAccessor());
		
		Timeline.createSequence().beginSequence()
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
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
		
//		Tween.from(table, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
//		Tween.from(table, ActorAccessor.ALPHA, 0.5f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.draw(Assets.moon, Gdx.graphics.getWidth()/4 *3, Gdx.graphics.getHeight()/4 *3);
		batch.draw(new Texture(Gdx.files.internal("menu/images/bkg-menu.png")), 0, 0, getCamera().viewportWidth, getCamera().viewportHeight);
		
		tweenManager.update(Gdx.graphics.getDeltaTime());
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);//debug
	}

	@Override
	public void dispose() {
		super.dispose();
	}	
	
}
