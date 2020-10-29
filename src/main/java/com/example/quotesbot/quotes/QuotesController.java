package com.example.quotesbot.quotes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuotesController {
    private final QuotesService quotesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Quote addQuote(@RequestBody Quote quote) {
        return quotesService.addQuote(quote);
    }

}
