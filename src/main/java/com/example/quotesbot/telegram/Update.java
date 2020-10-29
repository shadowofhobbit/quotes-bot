package com.example.quotesbot.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Update {
    @JsonProperty("update_id")
    private long updateId;
    private Message message;
}
