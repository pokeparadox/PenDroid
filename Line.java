package com.pirategames.pendroid;

import android.graphics.Canvas;

public class Line extends Primitive{
	protected Vector2d<Float> end;
	
	public Line(){
		drawWidth = 1;
		end = new Vector2d<Float>(10,10,CalculatorFloat.getInstance());
	}
	public void setEndPosition(Vector2d<?> e){
		end.setX((Float) e.getX());
		end.setY((Float) e.getY());
	}
	
	public void render(Canvas c){
        //	We draw the Line
		super.render(c);
        GFX.getInstance().drawLine(c, position, end);
	}
}
