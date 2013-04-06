package com.devcamp.cadejo;

import com.devcamp.cadejo.utils.ManagerSonidos;
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
        
        ManagerSonidos.getInstance().iniciarReproductorFondo(this, R.raw.fondo_menu);
        setContentView(R.layout.menu);
        setButtonEvents();
    }
	
	@Override
	public void onResume()
	{
		super.onPause();
		ManagerSonidos.getInstance().reanudarReproductorFondos();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		ManagerSonidos.getInstance().pausarReproductorFondos();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		ManagerSonidos.getInstance().terminarReprodutorFondo();
		ManagerSonidos.getInstance().onDestroy();
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
