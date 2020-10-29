package com.example.quotesbot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Message {
    @JsonProperty("message_id")
    private long messageId;
    private String text;
    private Chat chat;

}
