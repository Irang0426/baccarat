package kr.irang.baccarat.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banker {
    private List<Card> cards;

    public Banker() {
        cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
        System.out.println(card.getPattern() + card.getDenomination());
    }

    public List<Card> openCards() {
        return this.cards;
    }
}
