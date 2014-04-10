package com.madpanda.metalslug.screens.game;

import java.util.HashSet;
import java.util.Set;

import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.components.input.InputComponent;
import com.madpanda.metalslug.screens.game.components.physical.PhysicalComponent;

/**
 * The main unit of the game.
 * An entity can contain child entities.
 * Every entity has a physical, graphical and input component.
 *
 */
public class Entity {

	//the entity components
	private PhysicalComponent physicalComponent;
	private GraphicalComponent graphicalComponent;
	private InputComponent inputComponent;
	
	private Set<Entity> children; //the child entities
	
	/**
	 * Creates a new entity and initializes its components.
	 */
	public Entity() {
		children = new HashSet<>();
		physicalComponent = new PhysicalComponent(this);
		graphicalComponent = new GraphicalComponent(this);
		inputComponent = new InputComponent(this);
	}
	
	/**
	 * Adds a child entity to this entity.
	 * By default, the components in this entity will call the corresponding methods to the child entities.
	 * @param entity - The entity to be added as a child.
	 */
	public void addChild(Entity entity) {
		children.add(entity);
	}
	
	/**
	 * Removes a child from this entity if the entity is a child of this entity
	 * @param entity - The entity to be removed.
	 */
	public void removeChild(Entity entity) {
		if(children.contains(entity)) {
			children.remove(entity);
		}
	}

	public Set<Entity> getChildren() {
		return children;
	}

	public PhysicalComponent getPhysicalComponent() {
		return physicalComponent;
	}

	public void setPhysicalComponent(PhysicalComponent physicalComponent) {
		this.physicalComponent = physicalComponent;
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
	
}
