package com.github.zaolahma.webapp.page.camera;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * TODO: 
 * Receive images and forward to all connected clients
 * 
 */

@Component
public class CameraTextEndpointImpl extends TextWebSocketHandler implements CameraEndpoint {
	protected List<WebSocketSession> connections = new CopyOnWriteArrayList<WebSocketSession>();
	
	public CameraTextEndpointImpl() {
		super();
		
		CameraStub.getApi().registerFrameHandler(this);
		CameraStub.getApi().start();
	}
	
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	super.handleTextMessage(session, message);
    	
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
            }
        }
  
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            if(!ImageIO.write(img, "png", os)) {
            	System.out.println("Well, that didn't work now did it?");
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
        
        final String base64Image = Base64.getEncoder().encodeToString(os.toByteArray());
        
        session.sendMessage(new TextMessage(base64Image));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        connections.add(session);
        super.afterConnectionEstablished(session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	connections.remove(session);
    	super.afterConnectionClosed(session, status);
	}
    
	@Override
	public void handleFrame() {
		connections.forEach(connection -> {
			try {
				handleTextMessage(connection, new TextMessage(""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
