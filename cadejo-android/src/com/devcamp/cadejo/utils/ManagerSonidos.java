package com.devcamp.cadejo.utils;

import android.content.Context;
import android.media.MediaPlayer;


public class ManagerSonidos
{
	private static ManagerSonidos mInstance ;
	
	/**Indica si el usuario activo o desactivo los sonidos de la app*/
	private boolean mSonidoActivado;

	/** Rerpoductor para reproducir sonidos que no se repiten*/
	private MediaPlayer mReproductorSonidos;
	private MediaPlayer mReproductorFondos;

	/** Constructor*/
	private ManagerSonidos()
	{
		mReproductorSonidos = null;
		mReproductorFondos = null;
		mSonidoActivado = true;
	};

	public synchronized static ManagerSonidos getInstance()
	{
		if(mInstance == null)
			mInstance = new ManagerSonidos();
		return mInstance;
	}
	
	/**
	 * 
	 * @param context
	 * @param src
	 * @return
	 */
	public void iniciarReproductorFondo(Context context, int src)
	{	
		if(mReproductorFondos == null)
			mReproductorFondos = new MediaPlayer();
		
		if(mSonidoActivado)
		{
			if(mReproductorFondos.isPlaying())
			{
				mReproductorFondos.stop();
			}
			mReproductorFondos.release();
			mReproductorFondos = MediaPlayer.create(context, src);
			mReproductorFondos.setLooping(true);
			mReproductorFondos.start();
		}
	}
	
	/**
	 * 
	 */
	public synchronized void pausarReproductorFondos()
	{
		if(mSonidoActivado && mReproductorFondos != null)
		{
			if(mReproductorFondos.isPlaying())
				mReproductorFondos.pause();
		}
	}
	
	/**
	 * 
	 */
	public void reanudarReproductorFondos()
	{
		if(mSonidoActivado && mReproductorFondos != null)
		{
			if(!mReproductorFondos.isPlaying())
				mReproductorFondos.start();
		}
	}

	/**
	 * 
	 * @param pReproductor
	 */
	public void terminarReprodutorFondo()
	{
		if(mSonidoActivado && mReproductorFondos != null)
		{
			if(mReproductorFondos.isPlaying())
			{
				mReproductorFondos.stop();
			}
			//mReproductorFondos.release();
		}
	}

	/**
	 * 
	 * @param context
	 * @param src
	 */
	public void reproducirSonido(Context context, int src)
	{
		if(mReproductorSonidos == null)
			mReproductorSonidos = new MediaPlayer();
		
		if(mSonidoActivado)
		{
			mReproductorSonidos.release();
			mReproductorSonidos = MediaPlayer.create(context, src);
			mReproductorSonidos.start();
		}
	}

	/**
	 * GETTES
	 * SETTERS
	 */
	public boolean getSonidoActivado()
	{
		return mSonidoActivado;
	}

	public void setSonidoActivado(boolean pSonidoActivado)
	{
		mSonidoActivado = pSonidoActivado;
	}
	
	public void onDestroy()
	{
		mInstance = null;
	}
}
