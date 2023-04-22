package com.assessment.fileaggregateconsumer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {


    private @Value("${socket.simplebroker.destination}") String salesWebSocketDestination;

    private @Value("${socket.prefix}") String webSocketPrefix;

    private @Value("${socket.stomp.endpoint}") String stompEndpoint;


    @Autowired
    WebsocketConfiguration() {
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(salesWebSocketDestination);
        registry.setApplicationDestinationPrefixes(webSocketPrefix);
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(stompEndpoint)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }


}
