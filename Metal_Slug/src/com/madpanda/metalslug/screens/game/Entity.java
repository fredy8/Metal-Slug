package com.madpanda.metalslug.screens.game;

import java.util.HashSet;
import java.util.Set;

import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.components.input.InputComponent;
import com.madpanda.metalslug.screens.game.components.physical.UpdateComponent;

/**
 * The main unit of the game.
 * An entity can contain child entities.
 * Every entity has a physical, graphical and input component.
 *
 */
public class Entity {

	//the entity components
	private UpdateComponent updateComponent;
	private GraphicalComponent graphicalComponent;
	private InputComponent inputComponent;
	
	protected Set<Entity> children = new HashSet<>();
		
	/**
	 * Creates a new entity and initializes its components.
	 */
	public Entity() {
		updateComponent = new UpdateComponent(this);
		graphicalComponent = new GraphicalComponent(this);
		inputComponent = new InputComponent(this);
	}

	public UpdateComponent getPhysicalComponent() {
		return updateComponent;
	}

	public void setUpdateComponent(UpdateComponent updateComponent) {
		this.updateComponent = updateComponent;
	}

	public GraphicalComponent getGraphicalComponent() {
		return graphicalComponent;
	}

	public void setGraphicalComponent(GraphicalComponent graphicalComponent) {
		this.graphicalComponent = graphicalComponent;
	}

	public InputComponent getInputComponent() {
		return inputComponent;
	}

	public void setInputComponent(InputComponent inputComponent) {
		this.inputComponent = inputComponent;
	}
	
	public Set<Entity> getChildren() {
		return children;
	}
	
	public void addChild(Entity e) {
		children.add(e);
	}
	
	public void removeChild(Entity e) {
		children.remove(e);
	}
	
}
