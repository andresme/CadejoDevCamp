package com.devcamp.cadejo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.devcamp.cadejo.MyGame;
import com.devcamp.cadejo.ScoreManager;
import com.devcamp.cadejo.actors.Character.State;
import com.devcamp.cadejo.actors.CharacterController;
import com.devcamp.cadejo.world.World;
import com.devcamp.cadejo.world.WorldRenderer;

public class MainGameScreen implements Screen, InputProcessor{
	public enum GameState{
		RUNNING, STOPPED, PAUSED
	}

	public static float dificultySpeed = 1;
	private World world;
	private WorldRenderer renderer;
	private CharacterController controller;
	private ScoreManager scoreManager;
	public GameState state = GameState.RUNNING;
	private float dificulty = 0.03f;

	private MyGame g;

	public MainGameScreen(MyGame g){
		this.g = g;
	}

	@Override
	public void render(float delta) {
		if(!world.getMainCharacter().getState().equals(State.COLLISION) && state.equals(GameState.RUNNING)
				&& !state.equals(GameState.PAUSED)){
			int score = Integer.parseInt(scoreManager.getScore());
			if(score%10000 == 0 && score != 0){
				dificultySpeed *= 2;
			}
			if(score%550 == 0 && score != 0){
				dificulty += 0.2*(float) Math.random();
				dificultySpeed += 0.0040;
			}
			if(score%750 == 0 && score != 0){
				dificulty += 0.4*(float) Math.random();
				dificultySpeed += 0.0045;
			}
			if(score%1150 == 0 && score != 0){
				dificulty += (float) Math.random();
				dificultySpeed += 0.0055;
			}
			if(score%100 == 0 && score != 0){
				dificulty = (float) Math.random();
			}
			if(dificultySpeed > 3) dificultySpeed = 3f;
			Gdx.gl.glClearColor(0f, 0f, 0f, 1);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			world.update(delta, dificulty);
			controller.update(delta);
			renderer.render();
		}
		else if(state.equals(GameState.STOPPED)){
			controller.touchUp();
			scoreManager.stopGame();
			g.showScore(scoreManager.getScore());
			dificultySpeed = 1;
		}

	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);

	}

	@Override
	public void show() {
		dificultySpeed = 1;
		world = new World(this);
		scoreManager = new ScoreManager();
		renderer = new WorldRenderer(world, true, scoreManager);
		controller = new CharacterController(world);
		scoreManager.startGame();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		state = GameState.PAUSED;

	}

	@Override
	public void pause() {
		state = GameState.PAUSED;

	}

	@Override
	public void resume() {
		dificultySpeed = 1;
		state = GameState.RUNNING;

	}

	@Override
	public void dispose() {
		dificultySpeed = 1;

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		controller.touchDown();
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		controller.touchUp();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
