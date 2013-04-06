package com.devcamp.cadejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AnimationActivity extends Activity {

	private final int CANT_IMAGENES_ANIM = 5;
	private int mPosActualAnim = 0;
	private int[] mImagenesAnim = {R.drawable.anim1,
			R.drawable.anim2,
			R.drawable.anim3,
			R.drawable.anim4,
			R.drawable.anim5 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation);
		setButtonEvents();
	}

	private void setButtonEvents()
	{
		((ImageView)findViewById(R.id.btn_next_menu)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(mPosActualAnim == CANT_IMAGENES_ANIM-1)
				{
					Intent intent = new Intent(AnimationActivity.this, MainActivity.class);
					AnimationActivity.this.startActivity(intent);
					AnimationActivity.this.finish();
				}else
				{
					mPosActualAnim+=1;
					((RelativeLayout)findViewById(R.id.layout_img_anim)).setBackgroundResource(mImagenesAnim[mPosActualAnim]);
				}
			}
		});
	}
}
