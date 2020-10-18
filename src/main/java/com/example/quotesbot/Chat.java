package com.example.quotesbot;

public class Chat {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                '}';
    }
}
