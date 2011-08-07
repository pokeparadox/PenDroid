package com.pirategames.pendroid;

public interface PositionObject {
	
	public void setPosition(Vector2d<Float> p);
	
	public void setX(float x);
	
	public void setY(float y);
	
	public Vector2d<Float> getPosition();
}
