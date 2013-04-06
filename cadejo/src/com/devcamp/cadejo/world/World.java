package com.devcamp.cadejo.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.devcamp.cadejo.actors.Background;
import com.devcamp.cadejo.actors.Cadejo;
import com.devcamp.cadejo.actors.Character;
import com.devcamp.cadejo.actors.Character.State;
import com.devcamp.cadejo.actors.Floor;
import com.devcamp.cadejo.actors.Obstacle;
import com.devcamp.cadejo.screens.MainGameScreen;
import com.devcamp.cadejo.screens.MainGameScreen.GameState;

public class World {

	private Character mainCharacter;
	private Cadejo cadejo;
	private MainGameScreen screen;

	private Array<Background> backgrounds = new Array<Background>();
	private Array<Obstacle> obstacles = new Array<Obstacle>();
	private Array<Floor> floor = new Array<Floor>();

	//Cache:
	private Array<Background> cachedBackgrounds = new Array<Background>();
	private Array<Obstacle> cachedObstacles = new Array<Obstacle>();
	private Array<Floor> cachedFloors = new Array<Floor>();

	//stuff Exit screen
	private Array<Background> goneBackgrounds = new Array<Background>();
	private Array<Obstacle> goneObstacles = new Array<Obstacle>();
	private Array<Floor> goneFloor = new Array<Floor>();

	public World(MainGameScreen screen){
		this.screen = screen;
		createWorld();
	}

	private void createWorld(){
		mainCharacter = new Character(new Vector2(2, 1.5f));
		cadejo = new Cadejo(new Vector2(0.5f, 1.5f));
		backgrounds.add(new Background(new Vector2(0, 0), 1));
		for(int i = 1; i < 4; i++){
			backgrounds.add(new Background(new Vector2((float)i*Background.SIZE_W, 0), 1));
		}
		for(int i = 1; i < 4; i++){
			floor.add(new Floor(new Vector2((float)i*Background.SIZE_W, 0)));
		}

	}

	public void update(float delta, float dificulty){
		checkCollisions();
		updateBackground(delta);
		updateFloor(delta);
		updateObstacles(delta);
		checkGone();
		makeCache();
		deleteGone();
		checkBackgroundCreation();
		checkFloorCreation();
		checkObstacleCreation(delta);
	}

	public void checkCollisions(){
		for(Obstacle i: obstacles){
			if(mainCharacter.getPosition().x+mainCharacter.getBounds().width > i.getPosition().x &&
					mainCharacter.getPosition().x < i.getPosition().x+i.getBounds().width &&
					mainCharacter.getPosition().y < i.getPosition().y+i.getBounds().height){
				mainCharacter.setState(State.COLLISION);
				screen.state = GameState.STOPPED;
			}
		}
	}

	public void updateBackground(float delta){
		for(Background i : backgrounds){
			i.update(delta);
		}
	}

	public void updateFloor(float delta){
		for(Floor i : floor){
			i.update(delta);
		}
	}

	public void updateObstacles(float delta){
		for(Obstacle i : obstacles){
			i.update(delta);
		}
	}

	public void checkGone(){
		for(Background i : backgrounds){
			if(i.getPosition().x < -Background.SIZE_W){
				goneBackgrounds.add(i);
			}
		}
		for(Obstacle i : obstacles){
			if(i.getPosition().x < -i.size_w){
				goneObstacles.add(i);
			}
		}
		for(Floor i : floor){
			if(i.getPosition().x < -Floor.SIZE_W){
				goneFloor.add(i);
			}
		}
	}

	public void makeCache(){
		for(Background i : goneBackgrounds){
			if(i.getPosition().x < 0){
				cachedBackgrounds.add(i);
			}
		}
		for(Obstacle i : goneObstacles){
			if(i.getPosition().x < 0){
				cachedObstacles.add(i);
			}
		}
		for(Floor i : goneFloor){
			if(i.getPosition().x < 0){
				cachedFloors.add(i);
			}
		}
	}

	public void deleteGone(){
		for(Background i : goneBackgrounds){
			if(i.getPosition().x < 0){
				backgrounds.removeValue(i, false);
			}
		}
		for(Obstacle i : goneObstacles){
			if(i.getPosition().x < 0){
				obstacles.removeValue(i, false);
			}
		}
		for(Floor i : goneFloor){
			if(i.getPosition().x < 0){
				floor.removeValue(i, false);
			}
		}
		goneBackgrounds.clear();
		goneObstacles.clear();
		goneFloor.clear();
	}

	public void checkBackgroundCreation(){
		Background newBackground = null;
		int randomBackground =  1 + (int)(Math.random() * 1); //5);
		if(backgrounds.get(backgrounds.size-1).getPosition().x <= WorldRenderer.CAMERA_W - Background.SIZE_W){
			for(int i = 0; i < cachedBackgrounds.size-1; i++){
				if(cachedBackgrounds.get(i).getId() == randomBackground){
					newBackground = cachedBackgrounds.get(i);
					newBackground.getPosition().x = WorldRenderer.CAMERA_W;
					break;
				}
			}
			if(newBackground != null){
				backgrounds.add(newBackground);
				cachedBackgrounds.removeValue(newBackground, false);
			}
			else{
				backgrounds.add(new Background(new Vector2(WorldRenderer.CAMERA_W, 0), randomBackground));
			}
		}
	}

	public void checkFloorCreation(){
		Floor newFloor = null;
		if(floor.get(floor.size-1).getPosition().x <= WorldRenderer.CAMERA_W - Floor.SIZE_W){
			if(cachedFloors.size > 0){
				newFloor = cachedFloors.get(0);
				newFloor.getPosition().x = WorldRenderer.CAMERA_W;
				floor.add(newFloor);
				cachedFloors.removeIndex(0);
			}
			else{
				floor.add(new Floor(new Vector2(WorldRenderer.CAMERA_W, 0)));
			}
		}

	}

	public void checkObstacleCreation(float dificulty){
		Obstacle newObstacle = null;
		boolean enable = false;
		int randomObstacle = 1 + (int)(Math.random() * 5);
		Gdx.app.log("random", randomObstacle+"");
		if(Math.random() < 1*dificulty){
			if(obstacles.size > 0 && obstacles.size < 8){
				if(obstacles.get(obstacles.size-1).getPosition().x < 
						WorldRenderer.CAMERA_W - (obstacles.get(obstacles.size-1).size_w+
								mainCharacter.getBounds().width*1.75)){
					enable = true;
				}
				else{
					enable = false;
				}

			}
			else{
				enable = true;
			}
			if(enable){
				for(int i = 0; i < cachedObstacles.size-1; i++){
					if(cachedObstacles.get(i).getId() == randomObstacle){
						newObstacle = cachedObstacles.get(i);
						newObstacle.getPosition().x = WorldRenderer.CAMERA_W;
						break;
					}
				}
				if(newObstacle != null){
					obstacles.add(newObstacle);
					cachedObstacles.removeValue(newObstacle, false);
				}
				else{
					obstacles.add(new Obstacle(new Vector2(WorldRenderer.CAMERA_W, 1.5f), randomObstacle));
				}
			}
		}
	}

	public Character getMainCharacter() {
		return mainCharacter;
	}

	public void setMainCharacter(Character mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public Cadejo getCadejo() {
		return cadejo;
	}

	public void setCadejo(Cadejo cadejo) {
		this.cadejo = cadejo;
	}

	public Array<Background> getBackgrounds() {
		return backgrounds;
	}

	public void setBackgrounds(Array<Background> backgrounds) {
		this.backgrounds = backgrounds;
	}

	public Array<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(Array<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public Array<Floor> getFloor() {
		return floor;
	}

	public void setFloor(Array<Floor> floor) {
		this.floor = floor;
	}



}
