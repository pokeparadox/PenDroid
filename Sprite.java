package com.pirategames.pendroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Sprite extends Animation{
	private static final String TAG = Sprite.class.getSimpleName();
	protected Colour transparent;
	
	public boolean hitTest(Sprite tester, boolean fullShape){
		// TODO
		return false;
	}
	
	public void setTransparentColour(Colour c){
		GFX gfx = GFX.getInstance();
		if(sheetMode)
	    {
	       bitmap = gfx.applyColourKey(bitmap, c);
	    }
	    else
	    {
	        // we need to run through all individual images and set transparent colour
	        for(int i = surfaces.size()-1; i >=0; --i)
	        {
	        	surfaces.set(i, gfx.applyColourKey(surfaces.get(i), c));
	        }
	    }
	    transparent = c;
	    //region->setNoCollisionColour(transparent);
	}
	
	public void setTransparentColour(Vector2d<Integer> v){
		
	}
}
