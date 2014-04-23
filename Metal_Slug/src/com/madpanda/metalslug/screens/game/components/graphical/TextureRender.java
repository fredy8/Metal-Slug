package com.madpanda.metalslug.screens.game.components.graphical;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.madpanda.metalslug.screens.game.Entity;


public class TextureRender extends GraphicalComponent {
	
	private Sprite sprite;
	private Rectangle rectangle;
	
	/**
	 * Creates a new TextureRender graphical component for rendering a texture in a rectangle.
	 * @param entity - The entity containing this component
	 * @param rectangle - The area where the texture is rendered.
	 * @param textureFilePath - The path to the texture.
	 */
	public TextureRender(Entity entity, Rectangle rectangle, String textureFilePath) {
		super(entity);
		this.rectangle = rectangle;
		sprite = new Sprite(new Texture(Gdx.files.internal(textureFilePath)));
	}

	/**
	 * Renders the texture in the given rectangle.
	 */
	@Override
	public void render(SpriteBatch batch) {
		sprite.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		sprite.draw(batch);
	}
	
	/**
	 * @return The rectangle in which the sprite is rendered.
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}
