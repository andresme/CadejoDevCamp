package com.devcamp.cadejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.devcamp.cadejo.utils.ManagerSonidos;

public class AnimationActivity extends Activity {

	private final int CANT_IMAGENES_ANIM = 3;
	private int mPosActualAnim = 0;
	private int[] mImagenesAnim = {R.drawable.anim1, R.drawable.anim2, R.drawable.anim3};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation);
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

	private void setButtonEvents()
	{
		((ImageView)findViewById(R.id.btn_next_menu)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(mPosActualAnim == CANT_IMAGENES_ANIM-1)
				{
					startGame();
				}else
				{
					mPosActualAnim+=1;
					((RelativeLayout)findViewById(R.id.layout_img_anim)).setBackgroundResource(mImagenesAnim[mPosActualAnim]);
				}
			}
		});

		((ImageView)findViewById(R.id.btn_skip)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				startGame();
			}
		});
	}

	private void startGame()
	{
		Intent intent = new Intent(AnimationActivity.this, MainActivity.class);
		AnimationActivity.this.startActivity(intent);
		AnimationActivity.this.finish();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
//		ManagerSonidos.getInstance().terminarReprodutorFondo();
	}
	
}
