package com.pirategames.pendroid;

//	This interface is a helper to Generic types to allow arithmatic operations.

public interface Calculator<T extends Number> {
    
	//	Just infers type to a value
	public T convert(T a);
	public T convert(float x);
	public T convert(int i);
	
	//	Converts to specific type.
	public float toFloat(T a);
	public int toInt(T a);
    
	public T add(T a, T b);
    public T subtract(T a, T b);
    public T multiply(T a, T b);
    public T multiply(T a, float b);
    public T divide(T a, T b);
    public T divide(T a, float b);
	
	
	public boolean greater(T a, T b);
	public boolean lesser(T a, T b);
	
}

