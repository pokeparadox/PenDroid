package com.pirategames.pendroid;

public class Timer {
	
	public enum TimerScalers
	{
        //	Various modes that the timer can be put in.
        MILLI_SECONDS,
        CENTI_SECONDS,
        SIXTY_FRAMES,
        FIFTY_FRAMES,
        THIRTY_FRAMES,
        FIFTEEN_FRAMES,
        DECI_SECONDS,
        SECONDS,
        MINUTES,
        HOURS,
        CUSTOM
	}
	
	private long startTicks = 0;
	private long pausedTicks = 0;
	private boolean is_Paused = false;
	private boolean is_Started = false;
	private TimerScalers timeMode = TimerScalers.MILLI_SECONDS;
	private static final int MY_CLOCKS = 1000;
	private float scaler = 1.0f;
	private int offset = 0;
	
	//	Sets the scaling factor based on some predefined scales.
	public void setMode(TimerScalers m){
		timeMode = m;
		scaler = calcScaler(m);
	}
	
	public int getScaledTicks(TimerScalers mode){
		return (int)((float)getTicks() / calcScaler(mode));
	}
	
	public int getScaledTicks(){
		return (int)((float)getTicks() / scaler);
	}
	
	public float getTicks() {
        //If the timer is running
        if(is_Started)
        {
            //If the timer is paused
            if(is_Paused)
            {
                //Return the number of ticks when the the timer was paused
                return pausedTicks;
            }
            else
            {
                //Return the current time minus the start time
            	return System.currentTimeMillis() - startTicks + offset;
            }
        }
        //If the timer isn't running
        return 0;
	}

	public void delay(int sleepTime){
		if (sleepTime > 0) {
			// if sleepTime > 0 we're OK
			try {
				// send the thread to sleep for a short period
				// very useful for battery saving
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
		}
	}
	
	public void start(){
        //Start the timer
        is_Started = true;
        //Unpause the timer
        is_Paused = false;
        //Get the current clock time
        startTicks = System.currentTimeMillis();
	}
	
	public void stop(){
        is_Started = false;
        is_Paused = false;
	}
	
	public void pause(){
        //If the timer is running and isn't already paused
        if(  is_Started && !is_Paused )
        {
            //Pause the timer
            is_Paused = true;
            //Calculate the paused ticks
            pausedTicks = System.currentTimeMillis() -startTicks;
        }		
	}
	
	public void unpause(){
        //If the timer is paused
        if( is_Paused == true )
        {
            //Unpause the timer
            is_Paused = false;
            //Reset the starting ticks
            startTicks = System.currentTimeMillis() - pausedTicks;
           
            //Reset the paused ticks
            pausedTicks = 0;
        }
	}
	
    public boolean isPaused(){return is_Paused;}
    public boolean isStarted(){return is_Started;}
	
    //	Setting custom timer resolution
    public void setScaler(float scale){setMode(TimerScalers.CUSTOM);scaler = scale;}
    public float getScaler(){return scaler;}
    
    
    // Set timerScaler in terms of frames per second
    public void setFramesPerSecond(int fps){setScaler(1000/(float)fps);}    
    
	// Offsetting functions
	public void setOffset(int o){
		offset = (int)(o * scaler);
	}
	
	public void setOffset(int o, TimerScalers mode){
		offset = (int)(o * calcScaler(mode));
	}
	
	public int getOffset(){
		return (int)(offset / scaler);
	}
	
    public int getOffset(TimerScalers mode){
    	return (int)(offset / calcScaler(mode));
    }	
	
	
	// Calculates the scaling factor
	private float calcScaler(TimerScalers mode) {
        if (mode == TimerScalers.FIFTEEN_FRAMES)
            return ((float)MY_CLOCKS / 15.0f);
        else if (mode == TimerScalers.THIRTY_FRAMES)
            return ((float)MY_CLOCKS / 30.0f);
        else if (mode == TimerScalers.FIFTY_FRAMES)
            return ((float)MY_CLOCKS / 50.0f);
        else if (mode == TimerScalers.SIXTY_FRAMES)
            return ((float)MY_CLOCKS / 60.0f);
        else if (mode == TimerScalers.MILLI_SECONDS)
            return (MY_CLOCKS / 1000);
        else if (mode == TimerScalers.CENTI_SECONDS)
            return (MY_CLOCKS / 100);
        else if (mode == TimerScalers.DECI_SECONDS)
            return (MY_CLOCKS / 10);
        else if (mode == TimerScalers.SECONDS)
            return MY_CLOCKS;
        else if (mode == TimerScalers.MINUTES)
            return (MY_CLOCKS * 60);
        else if (mode == TimerScalers.HOURS)
            return (MY_CLOCKS * 60 * 60);
        else
            return MY_CLOCKS;
	}

	public TimerScalers getMode(){
		return timeMode;
	}
	

}
