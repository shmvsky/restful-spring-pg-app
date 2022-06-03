package com.grengof.restfulspringpgapp.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/messages")
    public String messages(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "messages";
    }

    @GetMapping("/messages/new")
    public String getMsgCreationForm() {
        return "newMessage";
    }

    @PostMapping("/messages/new")
    public RedirectView postMsgCreationForm(@RequestParam String text) {
        Message message = new Message(text);
        messageRepo.save(message);
        return new RedirectView("/messages");
    }

    @GetMapping("/messages/edit/{id}")
    public String getMsgEditForm(@PathVariable Integer id, Map<String, Object> model) {
        Message message = messageRepo.findById(id).get();
        model.put("message", message);
        return "editMessage";
    }

    @PostMapping("/messages/edit/{id}")
    public RedirectView postMsgEditForm(@PathVariable Integer id, @RequestParam String text, Map<String, Object> model) {
        Message message = messageRepo.findById(id).get();
        message.setText(text);
        messageRepo.save(message);
        model.put("message", message);
        return new RedirectView("/messages");
    }

    @GetMapping("/messages/delete/{id}")
    public RedirectView deleteMsgEditForm(@PathVariable Integer id) {
        messageRepo.deleteById(id);
        return new RedirectView("/messages");
    }
}