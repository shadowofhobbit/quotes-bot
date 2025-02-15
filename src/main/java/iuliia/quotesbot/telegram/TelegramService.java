package iuliia.quotesbot.telegram;

import iuliia.quotesbot.quotes.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;


@Service
public class TelegramService {
    public static final String QUOTE_COMMAND = "/quote";
    public static final String START_COMMAND = "/start";
    public static final String DEFAULT_ANSWER = ":-)";
    @Value("${token}")
    private String token;
    private final QuotesService quotesService;
    private static final String API_BASE_URL = "https://api.telegram.org/bot";
    @Value("${host}")
    private String host;
    public static final String GREETING = "Hi!";

    @Autowired
    public TelegramService(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @PostConstruct
    public void addWebhook() {
        var httpClient = HttpClient.newHttpClient();
        var uri = URI.create(API_BASE_URL + token
                + "/setWebhook?allowed_updates=message&url=" + host + "/updates");
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenComplete((stringHttpResponse, throwable) -> {
                    Logger.getGlobal().info(stringHttpResponse.body());
                    if (throwable != null) {
                        Logger.getGlobal().severe("Error creating webhook");
                        throwable.printStackTrace();
                    }
                });
    }


    void handleUpdate(Update update) {
        var receivedText = update.getMessage().getText();
        var chatId = update.getMessage().getChat().getId();
        if (QUOTE_COMMAND.equals(receivedText)) {
            var quote = quotesService.getRandomQuote();
            var quoteText = quote.getContent() + " (" + quote.getSource() + ")";
            sendMessage(chatId, quoteText);
        } else if (START_COMMAND.equals(receivedText)) {
            sendMessage(chatId, GREETING);
        } else {
            sendMessage(chatId, DEFAULT_ANSWER);
        }
    }

    private void sendMessage(long chatId, String message) {
        var httpClient = HttpClient.newHttpClient();
        var uri = URI.create(API_BASE_URL + token
                + "/sendMessage?chat_id=" + chatId
                + "&text=" + UriUtils.encodeQuery(message, StandardCharsets.UTF_8));
        var request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .join();
    }
}

