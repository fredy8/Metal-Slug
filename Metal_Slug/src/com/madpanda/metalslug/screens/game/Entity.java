package com.madpanda.metalslug.screens.game;

import java.util.HashSet;
import java.util.Set;

import com.madpanda.metalslug.screens.game.components.graphical.GraphicalComponent;
import com.madpanda.metalslug.screens.game.components.input.InputComponent;
import com.madpanda.metalslug.screens.game.components.physical.PhysicalComponent;

public class Entity {

	private PhysicalComponent physicalComponent;
	private GraphicalComponent graphicalComponent;
	private InputComponent inputComponent;
	
	private Set<Entity> children;
	
	public Entity() {
		children = new HashSet<>();
		physicalComponent = new PhysicalComponent(this);
		graphicalComponent = new GraphicalComponent(this);
		inputComponent = new InputComponent(this);
	}
	
	public void addChild(Entity entity) {
		children.add(entity);
	}
	
	public void removeChild(Entity entity) {
		children.remove(entity);
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
