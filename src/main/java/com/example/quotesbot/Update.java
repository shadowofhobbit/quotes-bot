package com.example.quotesbot;

import com.fasterxml.jackson.annotation.JsonProperty;

class Update {
    @JsonProperty("update_id")
    private long updateId;
    private Message message;

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Update{" +
                "updateId=" + updateId +
                ", message=" + message +
                '}';
    }
}
