package com.devcamp.cadejo.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cadejo {
	
	static final float SPEED = 2f;
	static final float JUMP_VEL = 1f;
	static final float SIZE_W = 0.5f;
	static final float SIZE_H = 0.5f;
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	
	public Cadejo(Vector2 position){
		this.position = position;
		this.bounds.height = SIZE_H;
		this.bounds.width = SIZE_W;
	}
	
	public void update(float delta){
		Vector2 velTemp = velocity.cpy();
		position.add(velTemp.scl(delta));
	}

}
