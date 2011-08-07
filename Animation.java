package com.pirategames.pendroid;

import com.pirategames.pendroid.Timer.TimerScalers;

public class Animation extends ImageSheet implements UpdateObject{

	protected int numLoops = -1;       // -1 - loop forever, 0 - don't loop, else - number of loops
	protected int firstLoops = -1;
	protected PlayMode playMode = PlayMode.pmNormal;
	protected boolean hasFinishedVal = false;
	protected boolean reachedEnd = false;        // used for pulse playing mode
	protected Timer timer;
	
	public Animation(){
		timer = new Timer();
		timer.setMode(TimerScalers.THIRTY_FRAMES);
		timer.start();
	}
	
	public enum PlayMode
    {
        pmNormal,     	// Run through frames sequentially
        pmReverse,      // Run through frames in reverse order.
        pmPulse         // Run forward and then back through the frames.
    };
	
	public void setLooping(boolean loop){
		if(loop)
	        numLoops = firstLoops = -1;
	    else
	        numLoops = firstLoops = 0;
	}
	
	public void setLooping(int loops){
		numLoops = firstLoops = (loops-1);
	}
	
	public PlayMode getPlayMode(){
		return playMode;
	}
	
	public void setReversePlay(boolean reverse){
	    if (reverse)
	        playMode = PlayMode.pmReverse;
	    else
	        playMode = PlayMode.pmNormal;
	}
	
	public void setPulsePlay(boolean pulse){
		if (pulse)
	        playMode = PlayMode.pmPulse;
	    else
	        playMode = PlayMode.pmNormal;
	}

	public void setPlayMode(PlayMode m){
	    playMode = m;
	}
	
	public boolean hasFinished(){
		return hasFinishedVal;
	}
	
	public void rewind(){
	    if (playMode == PlayMode.pmReverse)
	        activeImage = size()-1;
	    else
	        activeImage = 0;
	    numLoops = firstLoops;
	    hasFinishedVal = false;
	    reachedEnd = false;
	    timer.start();
	}
	
	@Override
	public void update() {
		if (! hasFinishedVal && timer.getScaledTicks() >= 1) // has the next frame been reached?
	    {
	        timer.start();
	        switch (playMode)
	        {
	            case pmNormal:
	            {
	                ++activeImage;
	                if (activeImage > size()-1)
	                {
	                    if (numLoops != 0) // looping
	                    {
	                        activeImage = 0;
	                        if (numLoops > 0) // don't loop forever
	                            --numLoops;
	                    }
	                    else
	                    {
	                        activeImage = size()-1;
	                        hasFinishedVal = true;
	                    }
	                }
	                break;
	            }
	            case pmReverse:
	            {
	                --activeImage;
	                if (activeImage < 0)
	                {
	                    if (numLoops != 0) // looping
	                    {
	                        activeImage = size()-1;
	                        if (numLoops > 0)
	                            --numLoops;
	                    }
	                    else
	                    {
	                        activeImage = 0;
	                        hasFinishedVal = true;
	                    }
	                }
	                break;
	            }
	            case pmPulse:
	            {
	                if (reachedEnd) // play backwards (phase 2)
	                {
	                    --activeImage;
	                    if (activeImage < 0)
	                    {
	                        if (numLoops != 0)
	                        {
	                            reachedEnd = false;
	                            activeImage = Math.min(1,size()-1); // set to second frame so we don't play the first frame two times in a row
	                            if (numLoops > 0)
	                                --numLoops;
	                        }
	                        else
	                        {
	                            activeImage = 0;
	                            hasFinishedVal = true;
	                            reachedEnd = false;
	                        }
	                    }
	                }
	                else // play forwards (phase 1)
	                {
	                    ++activeImage;
	                    if (activeImage > size()-1)
	                    {
	                        activeImage = Math.max((int)size()-2,0); // set to penultimate frame so we don't play the last one two times in a row
	                        reachedEnd = true;
	                    }
	                }
	                break;
	            }
	        }
	    }		
	}

}
