package com.example.quotesbot.quotes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuotesService {
    private final QuotesRepository quotesRepository;

    Quote addQuote(Quote quote) {
        return quotesRepository.add(quote);
    }

    public Quote getRandomQuote() {
        return quotesRepository.getQuote();
    }

}
