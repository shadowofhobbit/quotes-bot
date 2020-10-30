package iuliia.quotesbot.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
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
