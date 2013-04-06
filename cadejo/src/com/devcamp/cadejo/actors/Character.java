package com.devcamp.cadejo.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character {
	
	public enum State{
		RUNNING, JUMPING, COLLISION, FALL
	}
	
	static final float SPEED = 2f;
	static final float JUMP_VEL = 1f;
	static final float SIZE_W = 2f;
	static final float SIZE_H = 2f;
	
	public static final float SIZE = 2f; // half a unit
	static Vector2 tmp = new Vector2();
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state = State.RUNNING;
	float stateTime = 0;
	
	public float getStateTime()
	{
		return stateTime;
	}
	
	public Character(Vector2 position){
		this.position = position;
		this.bounds.height = SIZE_H;
		this.bounds.width = SIZE_W;
	}
	
	public void update(float delta) {		
		stateTime += delta;
		Vector2 velTemp = velocity.cpy();
		position.add(velTemp.scl(delta));
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
