package com.devcamp.cadejo.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.devcamp.cadejo.ScoreManager;
import com.devcamp.cadejo.actors.Background;
import com.devcamp.cadejo.actors.Cadejo;
import com.devcamp.cadejo.actors.Character;
import com.devcamp.cadejo.actors.Floor;
import com.devcamp.cadejo.actors.Obstacle;

public class WorldRenderer {
	
	public static final float CAMERA_W = 10f;
	public static final float CAMERA_H = 7f;
	
	private World world;
	private OrthographicCamera cam;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	private Texture charTexture;
	private Texture cadejoTexture;
	private Array<Texture> backgroundTextures;
	private Array<Texture> floorTextures;
	private Array<Texture> obstacleTextures;
	
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	
	private int width;
	private int height;
	
	private float ppuX;
	private float ppuY;
	
	//Score
	private ScoreManager mScoreManager;
	private BitmapFont mScoreFont;
	
	public WorldRenderer(World world, boolean debug, ScoreManager pScoreManager){
		this.debug = debug;
		sharedConstructor(world, pScoreManager);
	}
	
	public WorldRenderer(World world, ScoreManager pScoreManager){
		sharedConstructor(world, pScoreManager);
	}
	
	public void sharedConstructor(World world, ScoreManager pScoreManager){
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_W, CAMERA_H);
		this.cam.position.set(CAMERA_W/2f, CAMERA_H/2f, 0f);
		this.cam.update();
		spriteBatch = new SpriteBatch();
		
		//Score
		mScoreManager = pScoreManager;
		mScoreFont = new BitmapFont();
		mScoreFont.setColor(0.5f,0.4f,0,1);
		
		//
		loadTextures();
	}
	
	public void loadTextures(){
		
	}
	
	public void render(){
		if(debug)
			drawDebug();
		
		//Abre el sprite batch
		spriteBatch.begin();
		
		//Dibuja todo lo necesario
		drawScore();
		
		//Cierra el sprite batch
		spriteBatch.end();
	}
	
	private void drawScore()
	{
		mScoreFont.draw(spriteBatch, "Score: " + mScoreManager.getScore() , 100, 100);
	}
	
	public void drawCharacter(){
		Character mainCharacter = world.getMainCharacter();
		
	}
	
	public void drawCadejo(){
		Cadejo cadejo = world.getCadejo();
	}
	
	public void drawObstacles(){
		Array<Obstacle> obstacles = world.getObstacles();
		for(Obstacle i : obstacles){
			switch(i.getId()){
			case 1:
				
			}
		}
	}
	
	public void drawBackground(){
		Array<Background> backgrounds = world.getBackgrounds();
		for(Background i : backgrounds){
			switch(i.getId()){
			case 1:
				
			}
		}
	}
	
	public void drawFloor(){
		Array<Floor> floors = world.getFloor();
		for(Floor i : floors){
			switch(i.getId()){
			case 1:
				
			}
		}
	}
	
	public void drawDebug(){
		Character mainCharacter = world.getMainCharacter();
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		Rectangle rect = mainCharacter.getBounds();
		float x1 = mainCharacter.getPosition().x + rect.x;
		float y1 = mainCharacter.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0,1,0,1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		
		Array<Background> backgrounds = world.getBackgrounds();
		for(Background i : backgrounds){
			Rectangle rect2 = i.getBounds();
			float x2 = i.getPosition().x + rect2.x;
			float y2 = i.getPosition().y + rect2.y;
			debugRenderer.setColor(new Color(0,0,1,1));
			debugRenderer.rect(x2, y2, rect2.width, rect2.height);
		}
		
		Array<Floor> floor = world.getFloor();
		for(Floor i : floor){
			Rectangle rect2 = i.getBounds();
			float x2 = i.getPosition().x + rect2.x;
			float y2 = i.getPosition().y + rect2.y;
			debugRenderer.setColor(new Color(1,0,0,1));
			debugRenderer.rect(x2, y2, rect2.width, rect2.height);
		}
		
		Array<Obstacle> obstacles = world.getObstacles();
		for(Obstacle i : obstacles){
			Rectangle rect2 = i.getBounds();
			float x2 = i.getPosition().x + rect2.x;
			float y2 = i.getPosition().y + rect2.y;
			debugRenderer.setColor(new Color(1,1,0,1));
			debugRenderer.rect(x2, y2, rect2.width, rect2.height);
		}
		
		debugRenderer.end();

	}
	
	
	public void setSize(int w, int h){
		this.width = w;
		this.height = h;
		
		ppuX = (float)width / CAMERA_W;
		ppuY = (float)height / CAMERA_H;
	}
	

}
