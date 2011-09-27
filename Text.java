package com.pirategames.pendroid;

import android.graphics.Canvas;
import android.graphics.Paint;

/* This class is to display Text... funnily enough.*/

public class Text extends Rectangle {
	protected Paint paint;
	protected RENDER_MODES renderMode;
	protected ALIGNMENT alignment;
	public enum ALIGNMENT
    {
        LEFT_JUSTIFIED,
        CENTRED,
        RIGHT_JUSTIFIED
    }
    public enum RENDER_MODES
    {
    	QUICK,  //  fast with colourkey
        BOXED,  //  antialiased against bg box.
        NICE    //  antialiased fully with alpha channel SLOW
    }
    
	public Text(){
		paint = new Paint();
		setRenderMode(RENDER_MODES.QUICK);
		alignment = ALIGNMENT.CENTRED;
	}
	
	public void setFontSize(float size){
		paint.setTextSize(size);
	}
	
	void setRenderMode(RENDER_MODES m){
		renderMode = m;
		/*BOXED is supposed to be a fast mode... 
		TODO find a way to implement this which will retain the speed of this mode.*/
		if(m == RENDER_MODES.NICE || m == RENDER_MODES.BOXED)
			paint.setAntiAlias(true);
		else
			paint.setAntiAlias(false);
	}
	
	public void print(Canvas c, String text){
		paint.setColor(colour.getIntegerColour());
		c.drawText(text, position.getX(), position.getY(), paint);
	}
}
