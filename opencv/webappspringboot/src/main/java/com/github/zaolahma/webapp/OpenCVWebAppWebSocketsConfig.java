package com.github.zaolahma.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.github.zaolahma.webapp.page.camera.CameraEndpointImpl;
import com.github.zaolahma.webapp.page.camera.CameraTextEndpointImpl;

@Configuration
@EnableWebSocket
public class OpenCVWebAppWebSocketsConfig implements WebSocketConfigurer {
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    	/*
    	 *  Whoa... Configuration without implicit obfuscated magic though XML? 
    	 *  Which alternate reality did I teleport into?!
    	 */
        webSocketHandlerRegistry.addHandler(new CameraTextEndpointImpl(), "/websocket/textcamera");
        webSocketHandlerRegistry.addHandler(new CameraEndpointImpl(), "/websocket/camera");
    }
}
