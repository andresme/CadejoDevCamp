package com.devcamp.cadejo;

import com.devcamp.cadejo.utils.IConstants;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends Activity implements IConstants{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);     
        showScore();
    }
	
	public void showScore()
	{
		int score = getIntent().getIntExtra(KEY_SCORE, 0);
		((TextView)findViewById(R.id.texto_score)).setText(String.valueOf(score));
	}
	
}
