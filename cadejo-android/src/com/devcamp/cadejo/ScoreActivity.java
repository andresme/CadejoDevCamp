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
		String score = getIntent().getStringExtra(KEY_SCORE);
		((TextView)findViewById(R.id.texto_score)).setText(String.valueOf(score));
	}
}
