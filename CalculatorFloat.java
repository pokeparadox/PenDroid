package com.pirategames.pendroid;

public class CalculatorFloat implements Calculator<Float> {

private static CalculatorFloat instance;
	
	public static CalculatorFloat getInstance(){
		if (instance == null)
			instance = new CalculatorFloat();
	    return instance;
	}
	
	private CalculatorFloat(){
		
	}
	@Override
	public Float add(Float a, Float b) {
		return a + b;
	}

	@Override
	public Float subtract(Float a, Float b) {
		return a - b;
	}

	@Override
	public Float divide(Float a, Float b) {
		return a/b;
	}

	@Override
	public Float multiply(Float a, Float b) {
		return a*b;
	}

	@Override
	public Float multiply(Float a, float b) {
		return a*b;
	}

	@Override
	public Float convert(Float a) {
		return a;
	}

	@Override
	public Float convert(int i) {
		return (float)i+0.5f;
	}

	@Override
	public float toFloat(Float a) {
		return a;
	}

	@Override
	public int toInt(Float a) {
		return a.intValue();
	}

	@Override
	public Float divide(Float a, float b) {
		return a/b;
	}

	@Override
	public boolean greater(Float a, Float b) {
		return a>b;
	}

	@Override
	public boolean lesser(Float a, Float b) {
		return a<b;
	}

	@Override
	public Float convert(float x) {
		return x;
	}

}
