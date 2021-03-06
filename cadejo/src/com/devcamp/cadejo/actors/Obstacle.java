package com.devcamp.cadejo.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.devcamp.cadejo.screens.MainGameScreen;

public class Obstacle {
	
	static final float SPEED = -7.75f;
	public float size_w;
	public float size_h;
	static Vector2 tmp;
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	private int id;
	
	public Obstacle(Vector2 position, int id){
		this.velocity.x = SPEED;
		this.position = position;
		switch(id){
		case 1:
			size_h = 1.25f;
			size_w = 5f;
			break;
		case 2:
			size_h = 3.03f;
			size_w = 3.75f;
			break;
		case 3:
			size_h = 3f;
			size_w = 2.64f;
			break;
		case 4:
			size_h = 2.5f;
			size_w = 3.26f;
			break;
		}
		this.bounds.height = size_h;
		this.bounds.width = size_w;
		this.id = id;
	}
	
	public void update(float delta){
		tmp = velocity.cpy();
		tmp.scl(MainGameScreen.dificultySpeed);
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
	
	@Override
	public boolean equals(Object i){
		if(((Obstacle)i).id == this.id)
			return true;
		return false;
	}
	
	

}
