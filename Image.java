package com.pirategames.pendroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Image extends Rectangle{
	private static final String TAG = Image.class.getSimpleName();
	protected Bitmap bitmap;
	protected int bitmapID=0;
	
	public Image(){
		//	Create a standard 2d vector at 0,0
		setPosition(new Vector2d<Float>(CalculatorFloat.getInstance()));	
	}

	
	public void load(Context c, int resourceID){
		//	We decode the image	
		bitmap = BitmapFactory.decodeResource(c.getResources(), resourceID);
		//	Save the resource ID should we require it.
		
		if(bitmap != null)
		{
			bitmapID=resourceID;
			setPosition(new Vector2d<Float>(0,0,CalculatorFloat.getInstance()));
			setWidth(bitmap.getWidth());
			setHeight(bitmap.getHeight());
		}
		else
			Log.d(TAG, "Error loading Image" + resourceID);
	}
	
	//	Unload any load image.
	public void clear(){
		bitmap.recycle();
		bitmapID=0;
	}
	
	public void render(Canvas c){
		c.drawBitmap(bitmap, position.getX(), position.getY(), null);
	}
}
