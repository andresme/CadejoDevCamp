package com.devcamp.cadejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivityCreditos extends Activity 
{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		/**Se coloca la ventana que muestra el logo del tec*/
		setContentView(R.layout.creditos);

		/**Se manda a iniciar la ventana principal que contiene los botones de interaccion del usuario*/
		iniciarActivityPrincipal();
	}

	private void iniciarActivityPrincipal()
	{
		/**
		 * Que es un Handler?
		 */
		new Handler().postDelayed(new Runnable(){
			public void run() {
				Intent appInitIntent = new Intent(ActivityCreditos.this, MenuActivity.class);
				appInitIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(appInitIntent);
				finish();
			}
		}, 2000);
	}

}
