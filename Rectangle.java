package com.pirategames.pendroid;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Rectangle extends Primitive implements DimensionObject{
	protected Rect rect;
	
	
	public Rect getRect(){
		return rect;
	}
	public Rectangle(){
		drawWidth = -1;
		rect = new Rect();
	}
	
	
	
	public void render(Canvas c){ 
        //	We draw the rectangle;
		super.render(c);
        GFX.getInstance().drawRectangle(c, rect);
	}



	@Override
	public Vector2d<Integer> getDimensions() {
		Vector2d<Integer> dim = new Vector2d<Integer>(CalculatorInt.getInstance());
		dim.setX(getWidth());
		dim.setY(getHeight());
		return dim;
	}



	@Override
	public int getHeight() {
		return (int) (rect.bottom-rect.top);
	}



	@Override
	public int getWidth() {
		return (int) (rect.right-rect.left);
	}



	@Override
	public void setDimensions(Vector2d<Integer> dim) {
		setWidth(dim.getX());
		setHeight(dim.getY());
	}



	@Override
	public void setHeight(int h) {
		rect.bottom = (int) (position.getY()+h);
	}



	@Override
	public void setWidth(int w) {
		rect.right = (int) (position.getX()+w);
	}



	@Override
	public Vector2d<Float> getPosition() {
		return position;
	}



	@Override
	public void setPosition(Vector2d<?> p) {
		super.setPosition(p);
		CalculatorFloat c = CalculatorFloat.getInstance();
		rect.left = (Integer)c.toInt((Float) p.getX());
		rect.top = (Integer)c.toInt((Float) p.getY());
	}



	@Override
	public void setX(float x) {
		super.setX(x);
		rect.left = (int) x;
	}



	@Override
	public void setY(float y) {
		super.setY(y);
		rect.top = (int) y;
	}

}
