package com.devcamp.cadejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MenuActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.menu);
        setButtonEvents();
    }
	
	private void setButtonEvents()
	{
		((ImageView)findViewById(R.id.btn_play_menu)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, AnimationActivity.class);
				MenuActivity.this.startActivity(intent);
			}
		});
	}
}
