package com.devcamp.cadejo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.devcamp.cadejo.screens.MainGameScreen;

public class MyGame extends Game{

	/**
	 * Para mostrar la ventana de score se llama a 
	 * mDialogInterface.showScore(score);
	 */
	private DialogInterface mDialogInterface;
	
	public MyGame(DialogInterface pDialogInterface)
	{
		mDialogInterface = pDialogInterface;		
	}
	
	@Override
	public void create() {
		this.setScreen(new MainGameScreen());
	}
}
