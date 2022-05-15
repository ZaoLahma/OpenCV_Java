package com.github.zaolahma.page.control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/helloName")
public class ControlEndpoint {
	
    @OnMessage
    public String sayHello(String name) {
        System.out.println("Say hello to '" + name + "'");
        
        BufferedImage img = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
        
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 200; x++) {
                int a = (int)(Math.random()*256);
                int r = (int)(Math.random()*256);
                int g = (int)(Math.random()*256); 
                int b = (int)(Math.random()*256); 
  
                  //pixel
                int p = (a<<24) | (r<<16) | (g<<8) | b; 
  
                img.setRGB(x, y, p);
                
                //System.out.println("Set: " + x + ", " + y + " to " + p);
            }
        }
  
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            if(!ImageIO.write(img, "png", os)) {
            	System.out.println("Well shit");
            }
            os.flush();
        } catch (IOException e)
        {
        	System.out.println("Exception! " + e.getMessage());
        }
        finally {
            if (null != os) {
                try {
					os.close();
				} catch (IOException e) {
				}
            }
        }
        
        System.out.println("os: " + os.size());
        
        final String base64Image = Base64.getEncoder().encodeToString(os.toByteArray());
        
        System.out.println("base64Image: " + base64Image);
        
        return base64Image;
        //return ("Hello " + name + " from websocket endpoint");
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void helloOnClose(CloseReason reason) {
        System.out.println("WebSocket connection closed with CloseCode: " + reason.getCloseCode());
    }
    
}
