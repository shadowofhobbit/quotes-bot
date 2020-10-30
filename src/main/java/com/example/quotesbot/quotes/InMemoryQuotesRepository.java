package com.example.quotesbot.quotes;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class InMemoryQuotesRepository implements QuotesRepository {
    private final List<Quote> quotes = new CopyOnWriteArrayList<>(
            List.of(
                    new Quote("Никогда не знаешь, где тебе повезет.", "Макс Фрай"),
                    new Quote("Чувства нужны, потому что их интересно испытывать, а не затем, чтобы нами повелевать.", "Макс Фрай"),
                    new Quote("...быть обаятельным он долгие годы учился у всех знакомых котов...", "Макс Фрай"),
                    new Quote("В городе Вильнюсе живут дурацкие сны. Снятся кому попало, дразнят, хамят, и всегда обрываются на самом интересном месте, чтобы не успеть получить в глаз.", "Макс Фрай"),
                    new Quote("Родился я таким хилым, что врачи дали мне сроку жизни – сутки, и таким уродливым, что пятилетний братец мой Николай закричал, увидев меня: «Выкиньте его в окно!»", "Феликс Юсупов"),
                    new Quote("Физическое страдание - это испытание. Моральное страдание - это выбор.", "Эрик-Эмманюэль Шмитт"),
                    new Quote("Джексон ловил любопытных кальмаров, прозванных так за любопытство, которое было присуще им ровно в той же мере, что и кальмарность. То есть их любопытство и было в них самым любопытным.", "Терри Пратчетт"),
                    new Quote("...ваша совершенно невинная и вполне понятная мысль о том, что вы нормальный, – это одно из наиболее разрушительных предположений на переговорах.", "Крис Восс")
            )
    );

    @Override
    public Quote getQuote() {
        Random random = new Random();
        var index = random.nextInt(quotes.size());
        return quotes.get(index);
    }

    @Override
    public Quote save(Quote quote) {
        quotes.add(quote);
        return quote;
    }
}
