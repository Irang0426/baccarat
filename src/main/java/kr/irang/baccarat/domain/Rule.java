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

    public int sum(Side camp) {
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
                System.out.println("Player측 카드를 한장 더 뽑습니다.");
                int cardNumber = card.getDenomination();
                if (bankerSum <= 2) {
                    banker.receiveCard(draw());
                } else if (bankerSum == 3) {
                    if (cardNumber <= 7 || cardNumber == 9) {
                        banker.receiveCard(draw());
                        System.out.println("Banker측 카드를 한장 더 뽑습니다.");
                    }
                } else if (bankerSum == 4) {
                    if (2 <= cardNumber && cardNumber <= 7) {
                        banker.receiveCard(draw());
                        System.out.println("Banker측 카드를 한장 더 뽑습니다.");
                    }
                } else if (bankerSum == 5) {
                    if (4 <= cardNumber && cardNumber <= 7) {
                        banker.receiveCard(draw());
                        System.out.println("Banker측 카드를 한장 더 뽑습니다.");
                    }
                } else if (bankerSum == 6) {
                    if (6 <= cardNumber && cardNumber <= 7) {
                        banker.receiveCard(draw());
                        System.out.println("Banker측 카드를 한장 더 뽑습니다.");
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

    public String showSum(Player player, Banker banker) {
        return "Player의 합 = " + sum(player) + ", Banker의 합 = " + sum(banker);
    }

    public String checkWinner(Player player, Banker banker) {
        if (checkPlayerVictory(player, banker)) {
            return showSum(player, banker) + " 결과 : 플레이어측의 승리";
        } else if (checkBankerVictory(player, banker)) {
            return showSum(player, banker) + " 결과 : 뱅커측의 승리";
        } else {
            return showSum(player, banker) + " 결과 : 무승부";
        }
    }
}
