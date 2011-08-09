package com.pirategames.pendroid;

public class StateManager {
	private static StateManager instance;
	private ApplicationState current;
	private ApplicationState next;
	
	private StateManager(){}
	
	public static StateManager getInstance(){
		if( instance == null )
	    {
	        instance = new StateManager();
	    }
	    return instance;
	}
	
	public void setNextState(ApplicationState s){
		next = s;
	}
	
	public ApplicationState getCurrentState(){
		return current;
	}
	
	//	We just swap the states over currently
	public void stateManagement(){
		if(next != null && next != current)
		{
			current = next;
			next = null;
		}
	}
}
