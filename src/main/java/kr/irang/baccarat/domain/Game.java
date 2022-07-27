package kr.irang.baccarat.domain;

public class Game {
    public void play() {
        CardDeck cardDeck = new CardDeck();
        Card card = cardDeck.draw();
        System.out.println(card.getPattern() + card.getDenomination());
    }
}
