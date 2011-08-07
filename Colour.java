package com.pirategames.pendroid;

public class Colour{
    public enum COLOURS
    {
        BLACK,
        RED,
        LIGHT_RED,
        GREEN,
        LIGHT_GREEN,
        BLUE,
        LIGHT_BLUE,
        YELLOW,
        PURPLE,
        MAGENTA,
        CYAN,
        ORANGE,
        BROWN,
        DARK_GREY,
        GREY,
        LIGHT_GREY,
        WHITE
    };
	public int r;
	public int g;
	public int b;
	public int a;
	
	// Colour objects default to white
	public Colour(){
		r = g = b = a =  255; 
	}
	
	public Colour(int r, int g, int b) {
		if(r>255)
			r = 255;
		if(g>255)
			g=255;
		if(b>255)
			b=255;
		if(a>255)
			a=255;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 255;
	}

	public Colour(COLOURS c) {
		set(c);
	}

	//	Set this Colour to the same values of another Colour
	public void set(Colour c){
		r = c.r;
		g = c.g;
		b = c.b;
		a = c.a;
	}
	
	//	Set this Colour to a predefined Colour
	public void set(COLOURS c){
		if(c == COLOURS.RED)
		{
			r =  255;
			g = 0;
			b = 0;
		}
	    else if(c == COLOURS.LIGHT_RED)
		{
			r = 255;
			g = 128;
			b = 128;
		}
		else if(c == COLOURS.GREEN)
		{
			r = 0;
			g = 255;
			b = 0;
		}
	    else if(c == COLOURS.LIGHT_GREEN)
		{
			r = 128;
			g = 255;
			b = 128;
		}
		else if(c == COLOURS.BLUE)
		{
			r = 0;
			g = 0;
			b = 255;
		}
	    else if(c == COLOURS.LIGHT_BLUE)
		{
			r = 128;
			g = 128;
			b = 255;
		}
		else if(c == COLOURS.YELLOW)
		{
			r = 255;
			g = 255;
			b = 0;
		}
		else if(c == COLOURS.WHITE)
		{
			r = 255;
			g = 255;
			b = 255;
		}
	    else if(c == COLOURS.LIGHT_GREY)
		{
			r = 192;
			g = 192;
			b = 192;
		}
	    else if(c == COLOURS.GREY)
		{
			r = 128;
			g = 128;
			b = 128;
		}
	    else if(c == COLOURS.DARK_GREY)
		{
			r = 64;
			g = 64;
			b = 64;
		}
		else if(c == COLOURS.BLACK)
		{
			r = 0;
			g = 0;
			b = 0;
		}
		else if(c == COLOURS.PURPLE)
		{
			r = 128;
			g = 0;
			b = 128;
		}
		else if(c == COLOURS.MAGENTA)
		{
			r = 255;
			g = 0;
			b = 255;
		}
		else if (c == COLOURS.CYAN)
		{
		    r = 0;
		    g = 255;
		    b = 255;
	    }
		else if(c == COLOURS.ORANGE)
		{
			r = 255;
			g = 128;
			b = 0;
		}
		else if (c == COLOURS.BROWN)
		{
			r = 128;
			g = 64;
			b = 0;
		}
		a = 255;
	}
	
	//	Get the Greyscale version of this Colour
	public Colour getGreyScale(){
		int t = (int)((int)(r + g + b)*0.33f);
        return new Colour(t,t,t);
	}
	
	public void toGreyScale(){
		Colour t = getGreyScale();
		r = t.r;
		g = t.g;
		b = t.b;
		a = t.a;
	}
	
	//	Converts rgba component colour to single integer, which Android likes.
	public int getIntegerColour(){
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

}
