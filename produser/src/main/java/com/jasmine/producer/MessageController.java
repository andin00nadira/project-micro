package com.jasmine.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/send")
    public String send(@RequestParam String msg) {
        producerService.sendMessage(msg);
        return "Message sent: " + msg;
    }
}