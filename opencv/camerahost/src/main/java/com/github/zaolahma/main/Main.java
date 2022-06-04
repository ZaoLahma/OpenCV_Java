package com.github.zaolahma.main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.zaolahma.camera.Camera;
import com.github.zaolahma.camera.CameraImpl;

import nu.pattern.OpenCV;

public class Main 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	OpenCV.loadLocally();
    	
    	Camera cam = new CameraImpl();
    	cam.setResolution(320, 180);
    	
    	while (true) {
	    	byte[] image = cam.getImage();
	    	
	    	System.out.println("image: " + image.length);
	    	
	    	URL url = new URL("http://localhost:8080/upload");
	    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setDoOutput(true);
	    	conn.setRequestMethod("POST");
	    	conn.setRequestProperty("Content-Type", "binary/octet-stream");
	    	
	    	try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
	            wr.write(image);
	            wr.flush();
	        }
	
	        int responseCode = conn.getResponseCode();
	        
	        System.out.println("response: " + responseCode);
	        
	        conn.disconnect();
	        
	        Thread.sleep(100);
    	}
    }
}
