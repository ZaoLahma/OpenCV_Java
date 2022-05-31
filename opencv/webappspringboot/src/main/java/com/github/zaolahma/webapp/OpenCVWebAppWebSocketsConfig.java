package com.github.zaolahma.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.github.zaolahma.webapp.page.camera.CameraEndpoint;
import com.github.zaolahma.webapp.page.camera.CameraTextEndpoint;

@Configuration
@EnableWebSocket
public class OpenCVWebAppWebSocketsConfig implements WebSocketConfigurer {
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    	/*
    	 *  Whoa... Configuration without implicit obfuscated magic though XML? 
    	 *  Which alternate reality did I teleport into?!
    	 */
        webSocketHandlerRegistry.addHandler(new CameraTextEndpoint(), "/websocket/textcamera");
        webSocketHandlerRegistry.addHandler(new CameraEndpoint(), "/websocket/camera");
    }
}
