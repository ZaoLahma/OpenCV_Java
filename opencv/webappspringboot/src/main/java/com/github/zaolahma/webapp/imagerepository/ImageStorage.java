package com.github.zaolahma.webapp.imagerepository;

public class ImageStorage {
	private static ImageStorage INSTANCE = null;
	private static Object mSyncObject = new Object();
	
	private byte[] mImageData;
	
	private ImageStorage() {}
	
	public static ImageStorage getApi() {
		synchronized (mSyncObject) {
			if (null == INSTANCE) {
				INSTANCE = new ImageStorage();
			}
		}
		
		return INSTANCE;
	}
	
	public void updateImage(byte[] newImage) {
		synchronized (mSyncObject) {
			mImageData = newImage;
		}
	}
	
	public byte[] getImageData() {
		byte[] retVal;
		
		synchronized (mSyncObject) {
			retVal = mImageData;
		}
		
		return retVal;
	}
}
