package com.example.quotesbot;

import com.fasterxml.jackson.annotation.JsonProperty;

class Message {
    @JsonProperty("message_id")
    private long messageId;
    private String text;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    private Chat chat;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", text='" + text + '\'' +
                ", chat=" + chat +
                '}';
    }
}
