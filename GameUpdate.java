package com.pirategames.pendroid;

import java.text.DecimalFormat;


import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameUpdate extends Thread{
	
	// TAG is a string identifier, generally for debugging purposes.
	private static final String TAG = GameUpdate.class.getSimpleName();
	
	// 	Framerate Settings values
	private final static int	TARGET_FPS = 30;
	private final static int    MAX_FRAMESKIP = 3;
	private final static int    FRAME_DELAY = 1000 / TARGET_FPS;
	
	//	FrameRate Measuring Values
	//	Format to 2 decimal places
	private DecimalFormat deciFormat = new DecimalFormat("0.##");
	//	Do FPS calc every second
	private final static int    SAMPLE_TIME = 1000;
	//	Number of values to average to calc the FPS.
	private final static int    FPS_DATA_VALUES = 10;
	//	The last time data was sampled
	private long lastSample = 0;
	//	Timer for sampling data
	private long sampleTimer = 0;
	//	The total number of frames we have skipped
	private long totalFramesSkipped = 0;
	//	The number of frames skipped in a sample
	private long framesSkippedPerSample = 0;
	//	The number of frames that were drawn per sample
	private int framesDrawnPerSample = 0;
	//	The TOTAL number of frames
	private long totalFrames = 0;
	//	Number of times we read the stats
	private long    statsCount = 0;
	//	Array to store the FPS sample data
	private float fpsData[];
	
	//	Average fps
	private float meanFPS=0;

	
	private boolean 			isRunning;
	private StateManager 		stateMan;
	private ApplicationState 	state;
	private SurfaceHolder 		surfaceHolder;
	
	public GameUpdate(SurfaceHolder sHolder, GameView gameView){
		super();
		surfaceHolder = sHolder;
		///gameView = applicationState;
		stateMan = StateManager.getInstance();
		stateMan.stateManagement();
		state = stateMan.getCurrentState();
	}

	public void setState(ApplicationState s){
		state = s;
	}
	public void setRunning(boolean run){
		isRunning = run;
	}
	
	@Override
	public void run(){
		Log.d(TAG, "Starting PenDroid Game.");
		Canvas canvas;
		long startTime;		//	Ticks at beginning of update
		long difference;	//	Time at end of update
		int sleepTime = 0;	//	Excess time which we can sleep and free cpu
		int skippedFrames;	//	Number of blits we can skip
		
		initFPSCalc();	//	Sets up an array to sample FPS data
		
		// We run the gameloop
		while(isRunning && !state.getShouldQuit()){
			canvas = null;
			//	We lock the Canvas to edit the pixels
			try{
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					startTime = System.currentTimeMillis();
					skippedFrames = 0;	// resetting the frames skipped
					 //  Then we check states each update loop
		            stateMan.stateManagement();
					ApplicationState state = stateMan.getCurrentState();
					// update game state
					state.update();
					// render state to the screen
					// draws the canvas on the panel
					state.render(canvas);
					//	Render queued items
					GFX.getInstance().renderQueue();
					// calculate how long did the cycle take
					difference = System.currentTimeMillis() - startTime;
					// calculate sleep time
					sleepTime = (int)(FRAME_DELAY - difference);

					if (sleepTime > 0) {
						// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period
							// very useful for battery saving
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {}
					}

					while (sleepTime < 0 && skippedFrames < MAX_FRAMESKIP) {
						// we need to catch up
						// update without rendering
						state.update();
						// add frame period to check if in next frame
						sleepTime += FRAME_DELAY;
						++skippedFrames;
					}
					
					//	Report the number of skipped frames to log.
					if (skippedFrames > 0) 
						Log.d(TAG, "Skipped:" + skippedFrames);
					//	Collect the number of frames we skipped in this cycle
					framesSkippedPerSample += skippedFrames;
					//	Update the stats
					updateFPSCalc();
				}
			}
			finally{
				//	Don't leave Canvas in inconsistent state!
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	
	
	/**
	 * The statistics - it is called every cycle, it checks if time since last
	 * store is greater than the statistics gathering period (1 sec) and if so
	 * it calculates the FPS for the last period and stores it.
	 *
	 *  It tracks the number of frames per period. The number of frames since
	 *  the start of the period are summed up and the calculation takes part
	 *  only if the next period and the frame count is reset to 0.
	 */
	private void updateFPSCalc() {
		++framesDrawnPerSample;
		++totalFrames;

		// check the actual time
		sampleTimer += (System.currentTimeMillis() - sampleTimer);

		if (sampleTimer >= lastSample + SAMPLE_TIME) {
			// calculate the actual frames pers status check interval
			float actualFps = (float)(framesDrawnPerSample / (SAMPLE_TIME / 1000));

			//stores the latest fps in the array
			fpsData[(int) statsCount % FPS_DATA_VALUES] = actualFps;

			// increase the number of times statistics was calculated
			++statsCount;

			float totalFps = 0;
			// sum up the stored fps values
			for (int i = 0; i < FPS_DATA_VALUES; ++i) {
				totalFps += fpsData[i];
			}

			// obtain the average
			if (statsCount < FPS_DATA_VALUES) {
				// in case of the first 10 triggers
				meanFPS = totalFps / statsCount;
			} else {
				meanFPS = totalFps / FPS_DATA_VALUES;
			}
			// saving the number of total frames skipped
			totalFramesSkipped += framesSkippedPerSample;
			// resetting the counters after a status record (1 sec)
			framesSkippedPerSample = 0;
			sampleTimer = 0;
			framesDrawnPerSample = 0;

			sampleTimer = System.currentTimeMillis();
			lastSample = sampleTimer;
//			Log.d(TAG, "Average FPS:" + df.format(averageFps));
			//state.setMeanFps("FPS: " + deciFormat.format(meanFPS));
		}
	}
	
	private void initFPSCalc(){
		fpsData = new float[FPS_DATA_VALUES];
		for (int i = 0; i < FPS_DATA_VALUES; ++i)
			fpsData[i]=0;
		Log.d(TAG + ".initFPSCalc()", "Initialised FPS Calculation Array.");
	}
	
}
