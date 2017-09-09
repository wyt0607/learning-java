package com.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketManager implements WebSocketConfigurer {

    private String[] allowsOrigins={
            "http://localhost:8888",
            "https://wton.vip",
            "http://wton.vip"
    };

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), "/initWebsocket").addInterceptors(handerInterceptor()).setAllowedOrigins(allowsOrigins);
    }

    @Bean
    public ChatHandler chatHandler() {
        return new ChatHandler();
    }

    @Bean
    public HanderInterceptor handerInterceptor() {
        return new HanderInterceptor();
    }
}
