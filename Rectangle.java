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
        GFX gfx = GFX.getInstance();
        gfx.setDrawColour(colour);
        gfx.setDrawWidth(drawWidth);
        gfx.drawRectangle(c, rect);
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
	public void setPosition(Vector2d<Float> p) {
		position = p;
		CalculatorInt calc = CalculatorInt.getInstance();
		rect.left = calc.convert(p.getX());
		rect.top = calc.convert(p.getY());	
	}



	@Override
	public void setX(float x) {
		position.setX(x);
		rect.left = (int) x;
	}



	@Override
	public void setY(float y) {
		position.setY(y);
		rect.top = (int) y;
	}

}
