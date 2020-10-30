package com.example.quotesbot.quotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBQuotesRepository extends JpaRepository<Quote, Long> {

}
