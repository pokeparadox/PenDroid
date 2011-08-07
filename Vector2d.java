package com.pirategames.pendroid;

public class Vector2d<T extends Number> extends Vector1d<T>{
	protected T y;
	
	public Vector2d(Calculator<T> c) {
		super(c);
		x = calc.convert(0);
		y = calc.convert(0);
	}

	public Vector2d(T x, T y, Calculator<T> calc) {
		super(calc);
		this.x = x;
		this.y = y;
	}
	
	public Vector2d(int x, int y, Calculator<T> calc) {
		super(calc);
		this.x = calc.convert(x);
		this.y = calc.convert(y);
	}

	public void set(Vector2d<T> v){
		x = v.x;
		y = v.y;
	}
	
	public void setY(T y){
		this.y = y;
	}
	
	public T getY(){
		return y;
	}
	
	public Vector2d<T> unit(){
		float len = length();
        if(len == 0)
        {
        	return new Vector2d<T>(calc.convert(0),calc.convert(1),calc);
        }
        float temp = 1/len;
        return new Vector2d<T>(calc.multiply(x, temp),calc.multiply(x, temp), calc);
	}
	
	public void normalise(){
		set(unit());
	}
	
	public float lengthSquared(){
		return calc.toFloat((calc.add(calc.multiply(x, x), calc.multiply(y,y))));
	}
	
	public float length(){
		return (float) Math.sqrt(lengthSquared());
	}
	
	public float dot(Vector2d<T> v){
		return calc.toFloat((calc.add(calc.multiply(x, v.getX()), calc.multiply(y,v.getY()))));
	}
	
	public boolean equals(Vector2d<T> v){
		return (x==v.x && y ==v.y);
	}
	
	public Vector2d<T> add(Vector2d<T> v){
		Vector2d<T> out;
		out = new Vector2d<T>(calc);
		out.setX(calc.add(x, v.getX()));
		out.setY(calc.add(y, v.getY()));
		return out;
	}

	public Vector2d<T> subtract(Vector2d<T> v){
		Vector2d<T> out;
		out = new Vector2d<T>(calc);
		out.setX(calc.subtract(x, v.getX()));
		out.setY(calc.subtract(y, v.getY()));
		return out;
	}
	
	public Vector2d<T> multiply(Vector2d<T> v){
		Vector2d<T> out;
		out = new Vector2d<T>(calc);
		out.setX(calc.multiply(x, v.getX()));
		out.setY(calc.multiply(y, v.getY()));
		return out;
	}
	
	public Vector2d<T> multiply(float n){
		Vector2d<T> out;
		out = new Vector2d<T>(calc);
		out.setX(calc.multiply(x, n));
		out.setY(calc.multiply(y, n));
		return out;
	}

	public Vector2d<T> divide(Vector2d<T> v){
		Vector2d<T> out;
		out = new Vector2d<T>(calc);
		out.setX(calc.divide(x, v.getX()));
		out.setY(calc.divide(y, v.getY()));
		return out;
	}
	
	public Vector2d<T> divide(float n){
		Vector2d<T> out;
		out = new Vector2d<T>(calc);
		out.setX(calc.divide(x, n));
		out.setY(calc.divide(y, n));
		return out;
	}
	
	public Vector2d<T> addAssign(Vector2d<T> v){
		x = calc.add(x, v.getX());
		y = calc.add(y, v.getY());
		return this;
	}
	
	public Vector2d<T> subtractAssign(Vector2d<T> v){
		x = calc.subtract(x, v.getX());
		y = calc.subtract(y, v.getY());
		return this;
	}
	
	public Vector2d<T> multiplyAssign(Vector2d<T> v){
		x = calc.multiply(x, v.getX());
		y = calc.multiply(y, v.getY());
		return this;
	}
	
	public Vector2d<T> divideAssign(Vector2d<T> v){
		x = calc.divide(x, v.getX());
		y = calc.divide(y, v.getY());
		return this;
	}
	
	
	
	public boolean greater(Vector2d<T> v){
		return calc.greater(x, v.x) && calc.greater(y, v.y);
	}
	
	public boolean lesser(Vector2d<T> v){
		return calc.lesser(x, v.x) && calc.lesser(y, v.y);
	}
	
	public boolean greaterEqual(Vector2d<T> v){
		return(greater(v) || equals(v));
	}
	
	public boolean lesserEqual(Vector2d<T> v){
		return(lesser(v) || equals(v));
	}

	public void setY(int i) {
		y = calc.convert(i);
	}
}
