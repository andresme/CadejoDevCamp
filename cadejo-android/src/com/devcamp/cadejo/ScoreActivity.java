package com.devcamp.cadejo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.devcamp.cadejo.utils.IConstants;
import com.parse.Parse;

public class ScoreActivity extends Activity implements IConstants{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.score);
		setButtonEvents();
		showScore();
		
		Parse.initialize(this, "Q0UT8TWKK3rq7OmkuEgOH5DfBLBKuo7T9SlzI8eq", "gLvGCCY2joP6ktILOmM9bHfJhaYpLxBcrBLGwdBS");
	}

	public void showScore()
	{
		String score = getIntent().getStringExtra(KEY_SCORE);
		((TextView)findViewById(R.id.texto_score)).setText(String.valueOf(score));
	}

	private void setButtonEvents()
	{
		((ImageView)findViewById(R.id.btn_menu_score)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		((ImageView)findViewById(R.id.btn_jugar_de_nuevo_menu)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_JUGAR_DE_NUEVO);
				finish();
			}
		});
	}
}
