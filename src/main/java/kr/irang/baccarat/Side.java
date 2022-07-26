package kr.irang.baccarat;

import java.util.LinkedList;
import java.util.List;

public abstract class Side {
    protected final List<Card> cards;

    public Side() {
        this.cards = new LinkedList<>();
    }

    abstract void receiveCard(Card card);

    public void showCards(String sideName) {
        System.out.println(sideName + "의 카드");
        for (Card card : cards) {
            int cardNumber = card.getDenomination();
            String cardLetter;
            if (cardNumber == 1) {
                cardLetter = "A";
            } else if (2 <= cardNumber && cardNumber <= 10) {
                cardLetter = String.valueOf(cardNumber);
            } else if (cardNumber == 11) {
                cardLetter = "J";
            } else if (cardNumber == 12) {
                cardLetter = "Q";
            } else {
                cardLetter = "K";
            }
            System.out.print(card.getSpecialCharacter(card.getPattern()) + cardLetter + " ");
        }
        System.out.println();
    }

    abstract List<Card> getCards();
}
