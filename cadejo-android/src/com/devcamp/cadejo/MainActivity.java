package com.devcamp.cadejo;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.devcamp.cadejo.utils.IConstants;

public class MainActivity extends AndroidApplication implements DialogInterface, IConstants {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new MyGame(this), cfg);
    }

	@Override
	public void showScore(String pScore) {
		Intent intent = new Intent(this, ScoreActivity.class);
		intent.putExtra(KEY_SCORE, pScore);
		startActivity(intent);
	}
}