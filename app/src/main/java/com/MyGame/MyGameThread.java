package com.MyGame;
/**
 * Created by Srikanth Adapa
 */
public class MyGameThread extends Thread {
	
	volatile boolean running = false;
	
	MyGameSurfaceView parent;
	long sleepTime;
	
	MyGameThread(MyGameSurfaceView sv, long st){
		super();
		parent = sv;
		sleepTime = st;
	}
	
	public void setRunning(boolean r){
		running = r;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){

			try {
				sleep(sleepTime);
				parent.updateSurfaceView();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
