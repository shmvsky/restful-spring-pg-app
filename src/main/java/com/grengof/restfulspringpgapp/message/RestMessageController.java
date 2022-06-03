package com.grengof.restfulspringpgapp.message;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class RestMessageController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/api/messages")
    public Iterable<Message> getMessages() {
        Iterable<Message> messages = messageRepo.findAll();
        return messages;
    }

    @GetMapping("/api/messages/new")
    public Message addMessage(@RequestParam String text) {
        Message message = new Message(text);
        messageRepo.save(message);
        return message;
    }
}