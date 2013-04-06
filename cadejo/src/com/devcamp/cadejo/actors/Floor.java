package com.devcamp.cadejo.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Floor {
	
	static final float SPEED = -3.5f;
	public static final float SIZE_W = 10f;
	static final float SIZE_H = 1.5f;
	static Vector2 tmp;
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	private int id;
	
	public Floor(Vector2 position){
		this.velocity.x = SPEED;
		this.position = position;
		this.bounds.height = SIZE_H;
		this.bounds.width = SIZE_W;
	}
	
	public void update(float delta){
		tmp = velocity.cpy();
		position.add(tmp.scl(delta));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}
