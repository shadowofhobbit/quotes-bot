package com.example.quotesbot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Value("${token}")
    private String token;
    private long offset = 0;
    private static final int TIMEOUT = 100;
    private final QuotesRepository quotesRepository;
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String API_BASE_URL = "https://api.telegram.org/bot";
    @Value("${host}")
    private String path;

    @Autowired
    public TelegramService(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @PostConstruct
    public void addWebhook() {
        var httpClient = HttpClient.newHttpClient();
        var uri = URI.create(API_BASE_URL + token
                + "/setWebhook?allowed_updates=message&url=" + path);
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenComplete((stringHttpResponse, throwable) -> {
                    Logger.getGlobal().info(stringHttpResponse.body());
                    if (throwable != null) {
                        Logger.getGlobal().severe("error creating webhook");
                        throwable.printStackTrace();
                    }
                });
    }


    public void checkForUpdates() {
        var httpClient = HttpClient.newHttpClient();
        var uri = URI.create(API_BASE_URL + token
                + "/getUpdates?allowed_updates=message&offset=" + offset
                + "&timeout=" + TIMEOUT);
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        httpClient.sendAsync(request,
                HttpResponse.BodyHandlers.ofString())
                .thenAccept(stringHttpResponse -> {
                    if (stringHttpResponse.statusCode() >= 500) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        parseUpdate(stringHttpResponse.body());
                    }
                })
                .whenCompleteAsync((unused, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                    }
                    this.checkForUpdates();
                });
    }

    private void parseUpdate(String x) {
        try {
            var updates = mapper.readValue(x, Updates.class);
            var result = updates.getResult();
            if (result.length > 0) {
                var lastUpdate = result[result.length - 1];
                offset = lastUpdate.getUpdateId() + 1;
                handleMessages(result);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private void handleMessages(Update[] updates) {
        for (Update update: updates) {
            handleUpdate(update);
        }
    }

    void handleUpdate(Update update) {
        var receivedText = update.getMessage().getText();
        var chatId = update.getMessage().getChat().getId();
        if (receivedText.equals("/quote")) {
            var quote = quotesRepository.getQuote();
            sendMessage(chatId, quote);
        } else if (receivedText.equals("/start")) {
            var greeting = "Hi!";
            sendMessage(chatId, greeting);
        } else {
            sendMessage(chatId, ":-)");
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

