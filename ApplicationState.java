package com.pirategames.pendroid;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class ApplicationState implements UpdateObject{
	private static final String TAG = ApplicationState.class.getSimpleName();
	protected boolean shouldQuit = false;
	
	
	
	public ApplicationState() {
	}
	
	public boolean getShouldQuit(){
		return shouldQuit;
	}
	
	public void setShouldQuit(boolean b){
		shouldQuit = b;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void render(Canvas c) {
		// TODO Auto-generated method stub
		
	}
	
	public void onTouchEvent(MotionEvent event) {
		
	}
	

}
