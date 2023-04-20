package com.assessment.fileaggregateconsumer;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topics/greetings")
    public String handleHello(String message) {
        // Handle incoming messages and send a response to subscribed clients
        return "Hello, " + message + "!";
    }
}
