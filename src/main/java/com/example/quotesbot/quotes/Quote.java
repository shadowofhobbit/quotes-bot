package com.example.quotesbot.quotes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Quote {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    private String source;

    public Quote(String content, String source) {
        this.content = content;
        this.source = source;
    }
}
