package com.pirategames.pendroid;


import java.util.Vector;

import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.view.Display;

//	This is a Singleton Graphics processor

public class GFX {
	private static final String TAG = GFX.class.getSimpleName();
	private static GFX instance;
	private static Canvas canvas;
	private static Paint paint;
	private static Colour clearColour;
	private static Colour drawColour;
	private static int drawWidth = -1;
	private static Vector2d<Integer> dimensions;
	private static Vector<RenderObject> rendObjs;
	
	//	Ensure the existence of only one instance of the GFX object
	public static synchronized GFX getInstance(){
		if (instance == null)
			instance = new GFX();
	    return instance;
	}
	
	private GFX(){
		clearColour = new Colour();
		drawColour = new Colour();
		rendObjs = new Vector<RenderObject>();
		paint = new Paint();
		paint.setStyle(Style.FILL);
		
		/* TODO FIXME
		Display display = getWindowManager().getDefaultDisplay();
		dimensions.setX(display.getWidth());
		dimensions.setY(display.getHeight());*/
	}

	public void setDrawWidth(int w){
		drawWidth = w;
		if(w==-1)
			paint.setStyle(Style.FILL);
		else{
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(w);
		}
	}
	
	//	WARNING this is a destructive function the colour is actively removed from the image.
	public Bitmap applyColourKey(Bitmap b, Colour col){
		// make a mutable copy and a canvas from this mutable bitmap
		Bitmap mb = b.copy(b.getConfig(), true);
		Canvas c = new Canvas(mb);

		// get the int for the colour which needs to be removed
		paint.setColor(col.getIntegerColour());
		//paint.setARGB(col.a, col.r, col.g, col.b); // ARGB for the color, in this case red
		int removeColor = paint.getColor(); // store this color's int for later use

		// Next, set the alpha of the paint to transparent so the color can be removed.
		// This could also be non-transparent and be used to turn one color into another color            
		paint.setAlpha(0);

		// then, set the Xfermode of the pain to AvoidXfermode
		// removeColor is the color that will be replaced with the pain't color
		// 0 is the tolerance (in this case, only the color to be removed is targetted)
		// Mode.TARGET means pixels with color the same as removeColor are drawn on
		paint.setXfermode(new AvoidXfermode(removeColor, 0, AvoidXfermode.Mode.TARGET));

		// draw transparent on the "brown" pixels
		c.drawPaint(paint);

		// mb should now have transparent pixels where they were red before
		return mb;
	}
	
	public int getDrawWidth(){
		return drawWidth;
	}
	
	public Colour getDrawColour(){
		return drawColour;
	}
	
	public void setDrawColour(Colour c){
		if(c != null){
		drawColour = c;
		paint.setColor(c.getIntegerColour());
		}
	}
	
	public void setClearColour(Colour c){
		clearColour = c;
	}
	
	//	Clears screen with the set clear colour
	public void clear(Canvas c){
		c.drawColor(clearColour.getIntegerColour());
	}
	
	//	Clear screen with the temporary pass in colour
	public void clear(Canvas c, Colour col){
		c.drawColor(col.getIntegerColour());
	}
	
	public void drawPixel(Canvas c, Vector2d<Float> point){
		c.drawPoint(point.getX(), point.getY(), paint);
	}
	
	public void drawLine(Canvas c, Vector2d<Float> p1, Vector2d<Float> p2){
		c.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), paint);
	}

	// Primitive drawing
	public void drawRectangle(Canvas c, Rect rect){
        c.drawRect(rect, paint); 
	}
	
	
	public void queueRenderObject(RenderObject obj) {
		if(obj != null)
	    {
			rendObjs.add(obj);
	    }
	}

	public int getWidth() {
		return dimensions.getX();
	}

	public int getHeight() {
		return dimensions.getY();
	}

	public void renderQueue() {
		for(int i = 0; i < rendObjs.size(); ++i)
			rendObjs.get(i).render(canvas);
		rendObjs.clear();
	}
}
