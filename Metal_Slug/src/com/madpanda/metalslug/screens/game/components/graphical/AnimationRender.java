package com.madpanda.metalslug.screens.game.components.graphical;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;


public class AnimationRender extends GraphicalComponent {
	
	private Rectangle rectangle;
	private Animation animation;
	private float stateTime;
	
	/**
	 * Creates a new AnimationRender graphical component for rendering an animation in a rectangle.
	 * @param entity - The entity containing this component
	 * @param rectangle - The area where the texture is rendered.
	 * @param texturesFilePaths - The paths to the textures.
	 */
	public AnimationRender(Entity entity, Rectangle rectangle, List<String> texturesFilePaths) {
		super(entity);
		this.rectangle = rectangle;
		TextureRegion textures[] = new TextureRegion[texturesFilePaths.size()];
		for(int i = 0; i < texturesFilePaths.size(); i++) {
			textures[i] = new TextureRegion(new Texture(Gdx.files.internal(texturesFilePaths.get(i))));
		}
		animation = new Animation(.025f, textures);
	}

	/**
	 * Renders the texture in the given rectangle.
	 */
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion region = animation.getKeyFrame(stateTime, true);
		batch.draw(region, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		stateTime += Gdx.graphics.getDeltaTime();
	}
	
	/**
	 * @return The rectangle in which the sprite is rendered.
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}
