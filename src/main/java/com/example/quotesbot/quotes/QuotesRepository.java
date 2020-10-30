package com.example.quotesbot.quotes;

public interface QuotesRepository {

    Quote getQuote();

    Quote save(Quote quote);
}
