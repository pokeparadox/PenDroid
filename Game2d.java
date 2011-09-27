package com.pirategames.pendroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Game2d extends Activity{
	private static final String TAG = Game2d.class.getSimpleName();
	private static Activity self;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable Title box for more space
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Make a fullscreen display
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        self = this;
    }
    
    protected void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "PenDroid shutting down...");
    }
    
    protected void onStop(){
    	super.onStop();
    	Log.d(TAG, "PenDroid stopping...");
    }
    
    public static Context getContext(){
    	return (Context)self;
    }
}
