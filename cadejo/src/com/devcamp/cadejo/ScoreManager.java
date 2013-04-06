package com.devcamp.cadejo;

import com.badlogic.gdx.Gdx;

public class ScoreManager {

	private ScoreListener mListener;
	private boolean mPlaying;
	private Thread mScoreThread;
	private int mActualScore;
	private int mScoreIncrement;
	private int mDelayBetweenIncrements;

	public ScoreManager()
	{
		mListener = null;
		mPlaying = false;
		mActualScore = 0;
		mScoreIncrement = 50;
		mDelayBetweenIncrements = 500;
	}

	public void setScoreListener(ScoreListener pListener)
	{
		mListener = pListener;
	}

	public void startGame()
	{
		mPlaying = true;
		mActualScore = 0;
		startScoreThread();

	}

	public void stopGame()
	{
		mPlaying = false;
	}

	private void startScoreThread()
	{
		mScoreThread = new Thread(new Runnable() {
			@Override
			public void run() 
			{			      
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {

						while(mPlaying)
						{
							mActualScore += mScoreIncrement;
							if(mListener != null)
								mListener.onScoreChanged(mActualScore);
							try {
								Thread.sleep(mDelayBetweenIncrements);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
			}
		});
		mScoreThread.start();
	}
}
