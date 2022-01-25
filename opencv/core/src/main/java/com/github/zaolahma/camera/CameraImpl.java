package com.github.zaolahma.camera;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CameraImpl implements Camera{
	private VideoCapture mCapture;

	public CameraImpl() {
		mCapture = new VideoCapture(0);
	}
	
	@Override
	public Mat getImage() {
		Mat retVal = new Mat();
		
		mCapture.read(retVal);
		
		return retVal;
	}

}
