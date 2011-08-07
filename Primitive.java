package com.pirategames.pendroid;

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
	
}
