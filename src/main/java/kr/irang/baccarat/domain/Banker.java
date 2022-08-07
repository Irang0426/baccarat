package kr.irang.baccarat.domain;

import java.util.List;

public class Banker extends Camp {
    public Banker() {
        super();
    }

    @Override
    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }
}
