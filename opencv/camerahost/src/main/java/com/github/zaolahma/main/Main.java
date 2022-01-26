package com.github.zaolahma.main;

import org.opencv.core.Mat;

import com.github.zaolahma.camera.Camera;
import com.github.zaolahma.camera.CameraImpl;

import nu.pattern.OpenCV;

public class Main 
{
    public static void main( String[] args )
    {
    	OpenCV.loadLocally();
    	
    	Camera cam = new CameraImpl();
    	
    	Mat image = cam.getImage();
    	
    	System.out.println("image: " + image.toString());
    }
}
