package com.devcamp.cadejo.actors;

import java.util.HashMap;
import java.util.Map;

import com.devcamp.cadejo.actors.Character.State;
import com.devcamp.cadejo.world.World;


public class CharacterController {
	
	enum TouchEvents{
		JUMP
	}
	
<<<<<<< HEAD
	private static final float GRAVITY = -7f;
	private static final float MAX_JUMP_SPEED = 3f;
=======
	private static final float GRAVITY = -6f;
	private static final float MAX_JUMP_SPEED = 5f;
>>>>>>> incluyendo menu
	private static final float DAMP = 10f;
	private static final float MAX_VEL = 5f;
	private static final long LONG_JUMP = 150l;
	
	private World world;
	private Character mainCharacter;
	private boolean jumpingPressed;
	private long jumpPressedTime;
	
	static Map<TouchEvents, Boolean> keys = new HashMap<CharacterController.TouchEvents, Boolean>();
	
	static{
		keys.put(TouchEvents.JUMP, false);
	}
	
	public CharacterController(World world){
		this.world = world;
		this.mainCharacter = this.world.getMainCharacter();
	}
	
	public void touchDown(){
		keys.get(keys.put(TouchEvents.JUMP, true));
		jumpingPressed = true;
		mainCharacter.resetStateTime();
	}
	
	public void touchUp(){
		keys.get(keys.put(TouchEvents.JUMP, false));
		jumpingPressed = false;
	}
	
	public void update(float delta){
		processInput();
		mainCharacter.acceleration.y = GRAVITY;
		mainCharacter.acceleration.scl(delta);
		mainCharacter.velocity.add(mainCharacter.acceleration);
		if(mainCharacter.acceleration.x == 0) mainCharacter.velocity.x *= DAMP;
		if(mainCharacter.velocity.x > MAX_VEL) mainCharacter.velocity.x = MAX_VEL;
		if(mainCharacter.velocity.x < -MAX_VEL) mainCharacter.velocity.x = -MAX_VEL;
		
		mainCharacter.update(delta);
		
		mainCharacter.update(delta);
		if(mainCharacter.getPosition().y < 1.5){
			mainCharacter.getPosition().y = 1.5f;
			mainCharacter.setPosition(mainCharacter.getPosition());
			if(mainCharacter.getState().equals(State.JUMPING)){
				mainCharacter.setState(State.RUNNING);
			}
		}
		
	}
	
	public void processInput(){
		if(keys.get(TouchEvents.JUMP)){
			if(!mainCharacter.getState().equals(State.JUMPING)){
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				mainCharacter.setState(State.JUMPING);
				mainCharacter.getVelocity().y = MAX_JUMP_SPEED;
			}
			else{
				if(jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime >= LONG_JUMP))){
					jumpingPressed = false;
				}
				else{
					if(jumpingPressed){
						mainCharacter.getVelocity().y = MAX_JUMP_SPEED;
					}
				}
			}
		}
	}

}
