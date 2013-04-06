package com.devcamp.cadejo;


public class ScoreManager {
	
	private boolean mPlaying;
	private Thread mScoreThread;
	private int mActualScore;
	private int mScoreIncrement;
	private int mDelayBetweenIncrements;

	public ScoreManager()
	{
		mPlaying = false;
		mActualScore = 0;
		mScoreIncrement = 50;
		mDelayBetweenIncrements = 500;
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
				while(mPlaying)
				{
					mActualScore += mScoreIncrement;
					try {
						Thread.sleep(mDelayBetweenIncrements);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		mScoreThread.start();
	}

	public String getScore() {
		return String.valueOf(mActualScore);
	}
}
