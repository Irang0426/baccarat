package kr.irang.baccarat.domain;

import java.util.Arrays;
import java.util.List;

public class Game {
    public void play() {
        CardDeck cardDeck = new CardDeck();
        Banker banker = new Banker();
        Player player = new Player();

        Card card = cardDeck.draw();
        banker.receiveCard(card);
        Card card2 = cardDeck.draw();
        banker.receiveCard(card2);

    }
}
