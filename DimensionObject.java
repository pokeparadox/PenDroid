package com.pirategames.pendroid;

public interface DimensionObject {
	public void setDimensions(Vector2d<Integer> dim);
	
	public Vector2d<Integer> getDimensions();
	
	public void setHeight(int h);
	
	public void setWidth(int w);
	
	public int getWidth();
	
	public int getHeight();
}
