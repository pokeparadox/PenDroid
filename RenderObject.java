package com.pirategames.pendroid;

import android.graphics.Canvas;

//	This class is a rendering interface
public class RenderObject implements PositionObject{
	private static final String TAG = RenderObject.class.getSimpleName();
	protected Vector2d<Float> position;
	
	public RenderObject(){
		position = new Vector2d<Float>(CalculatorFloat.getInstance());
	}
	public void render(Canvas canvas){
		
	}
	
	public void queueRender(){
		GFX gfx = GFX.getInstance();
	    Vector2d<Float> p = getPosition();
	    //  Check if screen position is within the resolution
	    //(Allowing for scrolling something in from the left)
	    if(p.getX() < gfx.getWidth() && p.getY() < gfx.getHeight() && p.getX() > -gfx.getWidth() && p.getY() > -gfx.getHeight())
	        gfx.queueRenderObject(this);
	}

	@Override
	public Vector2d<Float> getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2d<?> p) {
		position.setX((Float)p.getX());
		position.setY((Float)p.getY());
	}

	@Override
	public void setX(float x) {
		position.setX(x);
	}

	@Override
	public void setY(float y) {
		position.setY(y);
	}

}
