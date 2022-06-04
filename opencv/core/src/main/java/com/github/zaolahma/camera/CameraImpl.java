package com.github.zaolahma.camera;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class CameraImpl implements Camera{
	private VideoCapture mCapture;

	public CameraImpl() {
		mCapture = new VideoCapture(0);
	}
	
	@Override
	public byte[] getImage() {
		Mat retVal = new Mat();

		mCapture.read(retVal);
		
		System.out.println("Before: " + retVal.cols() + ", " + retVal.rows());
		Imgproc.cvtColor(retVal, retVal, Imgproc.COLOR_BGR2RGB);
		System.out.println("After: " + retVal.cols() + ", " + retVal.rows());
		
        byte[] b = new byte[retVal.channels() * retVal.cols() * retVal.rows()];
        retVal.get(0, 0, b);
		
		return b;
	}

	@Override
	public void setResolution(int x, int y) {
		mCapture.set(3, x);
		mCapture.set(4, y);
	}

}
