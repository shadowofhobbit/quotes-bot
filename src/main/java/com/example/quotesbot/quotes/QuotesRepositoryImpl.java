package com.example.quotesbot.quotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotesRepositoryImpl extends JpaRepository<Quote, Long> {

}
