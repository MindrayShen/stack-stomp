package io.cjf.teststomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/greeting")
//    @SendTo("/mytopic/greetings")
//    @SendToUser
    public String greeting(String message, Principal principal) throws Exception {
        String name = principal.getName();
        simpMessagingTemplate.convertAndSendToUser(name,"/mytopic/greetings", message);
        return "发送成功";
    }

}
