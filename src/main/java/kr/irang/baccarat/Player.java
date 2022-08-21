package kr.irang.baccarat;

import java.util.List;

public class Player extends Side {
    public Player() {
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
