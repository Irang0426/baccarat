package kr.irang.baccarat.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CardDeck {
    private final List<Card> deck;

    private static final String[] PATTERNS = {"spade", "heart", "diamond", "club"};
    private static final int CARD_COUNT = 13;

    public CardDeck() {
        deck = this.generateCards();
    }

    public List<Card> getDeck() {
        return deck;
    }

    private List<Card> generateCards() {
        List<Card> cards = new LinkedList<>();

        for (String pattern : PATTERNS) {
            for (int i = 1; i <= CARD_COUNT; i++) {
                Card card = new Card(pattern, i);
                cards.add(card);
            }
        }

        return cards;
    }
}
