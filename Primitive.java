package com.pirategames.pendroid;

import android.graphics.Canvas;

//	Base class for geometric shapes, etc.
//	Not used directly.


public class Primitive extends RenderObject{
	protected int drawWidth;
	protected Colour colour;
	
	public void setColour(Colour c){
		colour = c;
	}
	
	public Colour getColour(){
		return colour;
	}
	
	public void setDrawWidth(int w){
		drawWidth = w;
	}
	
	public void render(Canvas c){
		GFX gfx = GFX.getInstance();
        gfx.setDrawColour(colour);
        gfx.setDrawWidth(drawWidth);
	}
	
}
