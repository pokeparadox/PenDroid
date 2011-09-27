package com.pirategames.pendroid;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class ImageSheet extends Image{
	private static final String TAG = ImageSheet.class.getSimpleName();
	protected boolean sheetMode = false;
	protected int activeImage = 0;
	protected Vector<Bitmap> 	surfaces;	// 	Store the BitMap per frame.
	protected Vector<Integer>	surfaceIDs;	//	Store the ID of the BitMaps
	protected Vector<Rectangle> clipAreas;	//	Store any clipping areas for TielSheets.
	
	public ImageSheet(){
		surfaces = new Vector<Bitmap>();
		surfaceIDs = new Vector<Integer>();
		clipAreas = new Vector<Rectangle>();
	}
	
	//	Load a single frame into the ImageSheet
	public void load(Context c, int resourceID){
		//	We load the image using the parent code
		super.load(c, resourceID);
		//	if image exist then we add it as a frame
		if(bitmap!= null)
		{
			surfaces.add(bitmap);
			surfaceIDs.add(resourceID);
//			We clear out the image to be able to continue loading frames.
			bitmap = null;
		}
	}

	
	public Vector2d<Integer> getDimensions(int subImage)
	{
		CalculatorInt calc = CalculatorInt.getInstance();
	    // SheetMode means only one image
	    if(sheetMode)
	    {
	        // This means we check clipAreas for the dimensions.
	        if(subImage < clipAreas.size())
	            return new Vector2d<Integer>(clipAreas.get(subImage).getWidth(), clipAreas.get(subImage).getHeight(),calc);
	        // No clipAreas means no dimensions!
	        return new Vector2d<Integer>(0,0,calc);
	    }
	    else
	    {
	        // This means we check surfaces for the dimensions.
	        if(subImage < surfaces.size())
	            return new Vector2d<Integer>(surfaces.get(subImage).getWidth(), surfaces.get(subImage).getHeight(),calc);
	    }

	    // If we get to here... then we have not found any dimensions!
	    return new Vector2d<Integer>(0,0,calc);
	}	
	
	//	Load a tilesheet from the single image resource.
	public void load(Context c, int resourceID, int xTiles, int yTiles){
		super.load(c, resourceID);
		if(bitmap!=null)
			assignClipAreas(xTiles,yTiles,0,0);
	}
	
	private void assignClipAreas(int xTiles, int yTiles, int skipTiles, int p_numTiles) {
		// TODO Auto-generated method stub
		int numTiles = p_numTiles;
	    //  now we have to build the clipping areas based on the tile info we have.
	    //second the width and height per tile needs to be calculated
	    int width = bitmap.getWidth();
	    int height = bitmap.getHeight();

	    int xTileWidth = width/xTiles;
	    int yTileWidth = height/yTiles;

	    if (numTiles == 0) {
	        numTiles = (xTiles * yTiles) - skipTiles;
	    }

	    //  Accomodate clip areas
	    clipAreas.setSize(numTiles);
	    for(int i = 0; i < numTiles;++i)
	    	clipAreas.set(i, new Rectangle());

	    int currTile = 0;
	    //  Run along then down

	    for(int y = 0; y < height; y+=yTileWidth)
	    {
	        for(int x = 0; x < width; x+=xTileWidth)
	        {
	            if (currTile >= skipTiles && (currTile-skipTiles)<numTiles) {
	                //  and set the values for the clip plane
	            	clipAreas.get(currTile-skipTiles).setX(x);
	            	clipAreas.get(currTile-skipTiles).setY(y);
	            	clipAreas.get(currTile-skipTiles).setWidth(xTileWidth);
	            	clipAreas.get(currTile-skipTiles).setHeight(yTileWidth);
	            }
	            ++currTile;
	        }
	    }
	    sheetMode = true;	
	}

	//	Set the image to be displayed from the tilesheets/frames
	public void setActiveImage(int a){
		if(sheetMode)
	    {
	        if(a<clipAreas.size())
	            activeImage = a;
	    }
	    else
	    {
	        if(a<surfaces.size())
	            activeImage = a;
	    }
	}
	
	//	Render the currently active Image to the screen.
	public void render(Canvas c){
		// where to draw the sprite
		CalculatorInt ci = CalculatorInt.getInstance();
		if(sheetMode)
		{
			Rect destRect = new Rect(ci.convert(position.getX()), ci.convert(position.getY()), 
					ci.convert(position.getX() + clipAreas.get(activeImage).getWidth()), 
					ci.convert(position.getY() + clipAreas.get(activeImage).getHeight()));
			
			c.drawBitmap(bitmap, clipAreas.get(activeImage).getRect(), destRect, null);		
		}
		else
		{
			Rect destRect = new Rect(ci.convert(position.getX()), ci.convert(position.getY()), 
					ci.convert(position.getX() + surfaces.get(activeImage).getWidth()), 
					ci.convert(position.getY() + surfaces.get(activeImage).getHeight()));
			
			c.drawBitmap(surfaces.get(activeImage), null, destRect, null);		
		}
	}
	
	//	return the number of subImages the sheet contains.
	public int size(){
		if(sheetMode)
	        return clipAreas.size();
	    else
	    {
	        return surfaces.size();
	    }
	}
	
	//	Clear out all images and clipAreas.
	public void clear(){
		bitmapID = 0;
		super.clear();
		surfaces.clear();
		clipAreas.clear();
		surfaceIDs.clear();
	}
	
}
