package iuliia.quotesbot.quotes;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuotesService {
    private final QuotesRepository repository;

    Quote addQuote(Quote quote) {
        return repository.save(quote);
    }


    @Transactional
    public Quote getRandomQuote() {
        var count = repository.count();
        Random random = new Random();
        var index = random.nextInt((int) count);
        Pageable pageable = PageRequest.of(index, 1);
        return repository.findAll(pageable).getContent().get(0);
    }

}
