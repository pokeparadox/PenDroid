package com.pirategames.pendroid;

public class Vector1d<T extends Number> {
	protected final Calculator<T> calc;
	
	// Sets the relevant Arithmetic calculator for this type.
	public Vector1d(Calculator<T> c){
		calc = c;
	}
	protected T x;
	
	public void setX(T x){
		this.x = x;
	}
	
	public void setX(float x){
		this.x = calc.convert(x);
	}
	
	public T getX(){
		return x;
	}
	
	public T add(Vector1d<T> v){
		return calc.add(x, v.getX());
	}
	
	public T subtract(Vector1d<T> v){
		return calc.subtract(x, v.getX());
	}	

}
