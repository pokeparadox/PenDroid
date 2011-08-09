package com.pirategames.pendroid;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	private static final String TAG = GameView.class.getSimpleName();
	protected GameUpdate gameUpdate;
	
	public GameView(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept event
        getHolder().addCallback(this);
        
        // Create the main game update thread
        gameUpdate = new GameUpdate(getHolder(), this);
        
        // make the GamePanel focusable so it can handle events
        setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// Once we have a surface we set our game updates going.
		gameUpdate.setRunning(true);
		gameUpdate.start();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		StateManager.getInstance().getCurrentState().onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// We want to try and stop the GameUpdate thread.
		Boolean retry = true;
		
		while (retry){
			try {
				gameUpdate.join();
				retry = false;
			}
			catch(InterruptedException e) {
				// Continue trying to close the thread
			}
		}
	}
}
