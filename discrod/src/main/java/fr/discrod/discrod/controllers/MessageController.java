package fr.discrod.discrod.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
    @GetMapping("/{id}")
    public String getMessageByUserId(@PathVariable Long id) {
        return "message";
    }
}
