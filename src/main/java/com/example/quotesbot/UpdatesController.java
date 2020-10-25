package com.example.quotesbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatesController {
    private final TelegramService telegramService;

    @Autowired
    public UpdatesController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @PostMapping
    void handleUpdate(@RequestBody Update update) {
        telegramService.handleUpdate(update);
    }
}
