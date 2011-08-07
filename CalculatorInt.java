package com.pirategames.pendroid;

public class CalculatorInt implements Calculator<Integer> {

	private static CalculatorInt instance;
	
	public static CalculatorInt getInstance(){
		if (instance == null)
			instance = new CalculatorInt();
	    return instance;
	}
	
	private CalculatorInt(){
		
	}
	
	
	@Override
	public Integer add(Integer a, Integer b) {
		return a + b;
	}
	
	public Integer subtract(Integer a, Integer b){
		return a-b;
	}

	@Override
	public Integer divide(Integer a, Integer b) {
		return a/b;
	}

	@Override
	public Integer multiply(Integer a, Integer b) {
		return a*b;
	}

	@Override
	public Integer multiply(Integer a, float b) {
		return (int) ((a*b)+0.5f);
	}

	@Override
	public Integer convert(Integer a) {
		return a;
	}

	@Override
	public Integer convert(int i) {
		return i;
	}

	@Override
	public float toFloat(Integer a) {
		return a.floatValue();
	}

	@Override
	public int toInt(Integer a) {
		return a;
	}

	@Override
	public Integer divide(Integer a, float b) {
		return (int) ((a/b)+0.5f);
	}

	@Override
	public boolean greater(Integer a, Integer b) {
		return a>b;
	}

	@Override
	public boolean lesser(Integer a, Integer b) {
		return a<b;
	}

	@Override
	public Integer convert(float x) {
		return (int) x;
	}

}
