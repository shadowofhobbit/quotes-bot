package com.example.quotesbot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Quote {
    private long id;
    private final String content;
    private final String source;
}
