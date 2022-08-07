package kr.irang.baccarat.domain;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Rule {
    private List<Card> deck;

    public Rule(List<Card> deck) {
        this.deck = deck;
    }
    private int transNumber(int number) {
        if (number >= 11) {
            return 10;
        }
        return number;
    }

    public Card draw() {
        Random random = new Random();
        int size = deck.size();
        int select = random.nextInt(size);

        Card selectedCard = deck.get(select);
        deck.remove(selectedCard);

        return selectedCard;
    }

    public int sum(Camp camp) {
        List<Card> cards = camp.getCards();
        int[] cardNumbers = new int[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int cardNumber = card.getDenomination();
            cardNumbers[i] = transNumber(cardNumber);
        }
        return IntStream.of(cardNumbers).sum() % 10;
    }

    public void additionalDraw(Player player, Banker banker) {
        int playerSum = sum(player);
        int bankerSum = sum(banker);

        if (0 <= playerSum && playerSum <= 7 && 0 <= bankerSum && bankerSum <= 7) {
            if (playerSum <= 5) {
                Card card = draw();
                player.receiveCard(card);
                int cardNumber = card.getDenomination();
                if (bankerSum <= 2) {
                    banker.receiveCard(draw());
                } else if (bankerSum == 3) {
                    if (cardNumber <= 7 || cardNumber == 9) {
                        banker.receiveCard(draw());
                    }
                } else if (bankerSum == 4) {
                    if (2 <= cardNumber && cardNumber <= 7) {
                        banker.receiveCard(draw());
                    }
                } else if (bankerSum == 5) {
                    if (4 <= cardNumber && cardNumber <= 7) {
                        banker.receiveCard(draw());
                    }
                } else if (bankerSum == 6) {
                    if (6 <= cardNumber && cardNumber <= 7) {
                        banker.receiveCard(draw());
                    }
                }
            }
        }
    }

    public boolean checkPlayerVictory(Player player, Banker banker) {
        int playerSum = sum(player);
        int bankerSum = sum(banker);
        return playerSum > bankerSum;
    }

    public boolean checkBankerVictory(Player player, Banker banker) {
        int playerSum = sum(player);
        int bankerSum = sum(banker);
        return playerSum < bankerSum;
    }

    public boolean checkTie(Player player, Banker banker) {
        int playerSum = sum(player);
        int bankerSum = sum(banker);
        return playerSum == bankerSum;
    }
}
