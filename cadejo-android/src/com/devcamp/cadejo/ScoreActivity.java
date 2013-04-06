package com.devcamp.cadejo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.devcamp.cadejo.utils.IConstants;

public class ScoreActivity extends Activity implements IConstants{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.score);
		setButtonEvents();
		showScore();
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
