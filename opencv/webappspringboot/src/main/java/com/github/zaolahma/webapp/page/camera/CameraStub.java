package com.github.zaolahma.webapp.page.camera;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CameraStub implements Runnable {
	private static CameraStub INSTANCE = null;
	private static Object mSyncObject = new Object();
	
	private static boolean mStarted = false;
	
	protected List<CameraEndpoint> mHandlers = new CopyOnWriteArrayList<CameraEndpoint>();
	protected boolean mRunning;
	protected Thread mThread;
	
	private CameraStub() {}
	
	public static CameraStub getApi() {
		synchronized (mSyncObject) {
			if (null == INSTANCE) {
				INSTANCE = new CameraStub();
			}
		}
		
		return INSTANCE;
	}
	
	public void start() {
		synchronized (mSyncObject) {
			if (false == mStarted) {
				mRunning = true;
				mStarted = true;
				
				mThread = new Thread(this);
				mThread.start();
			}
		}
	}
	
	public void stop() {
		mRunning = false;
	}
	
	public void registerFrameHandler(CameraEndpoint handler) {
		mHandlers.add(handler);
	}
	
	public void deregisterFrameHandler(CameraEndpoint handler) {
		mHandlers.remove(handler);
	}

	@Override
	public void run() {
		System.out.println("Camera stub started");
		
		while (mRunning) {
			mHandlers.forEach(handler -> {
				handler.handleFrame();
			});
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				mRunning = false;
			}
		}
	}
}
