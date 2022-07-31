package kr.irang.baccarat.domain;

public class Game {
    public void play() {
        CardDeck cardDeck = new CardDeck();
        Banker banker = new Banker();
        Player player = new Player();

        for (int i = 0; i < 2; i++) {
            Card card = cardDeck.draw();
            banker.receiveCard(card);
            Card card2 = cardDeck.draw();
            player.receiveCard(card2);
        }

        player.showCards("Player");
        banker.showCards("Banker");

        cardDeck.additionalDraw(player, banker);

        player.showCards("Player");
        banker.showCards("Banker");

        System.out.println();
        if (cardDeck.checkPlayerVictory(player, banker)) {
            System.out.println("플레이어측의 승리");
        } else if (cardDeck.checkBankerVictory(player, banker)) {
            System.out.println("뱅커측의 승리");
        } else if (cardDeck.checkTie(player, banker)) {
            System.out.println("무승부");
        }
    }
}
