package com.devcamp.cadejo;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.devcamp.cadejo.utils.IConstants;
import com.devcamp.cadejo.utils.ManagerSonidos;

public class MainActivity extends AndroidApplication implements DialogInterface, IConstants {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;
		cfg.useWakelock = true;

		initialize(new MyGame(this), cfg);
		
		//Inicia la musica
		ManagerSonidos.getInstance().iniciarReproductorFondo(this, R.raw.fondo_menu);

	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		ManagerSonidos.getInstance().pausarReproductorFondos();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		ManagerSonidos.getInstance().reanudarReproductorFondos();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
//		ManagerSonidos.getInstance().terminarReprodutorFondo();
	}

	@Override
	public void showScore(String pScore) {
		Intent intent = new Intent(this, ScoreActivity.class);
		intent.putExtra(KEY_SCORE, pScore);
		startActivityForResult(intent, REQUEST_ACTIVITY_SCORE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == REQUEST_ACTIVITY_SCORE) {
			if(resultCode == RESULT_JUGAR_DE_NUEVO)
			{
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
			finish();
		}
	}
}