package kr.irang.baccarat.domain;

public class Game {
    public void play() {
        CardDeck cardDeck = new CardDeck();
        Banker banker = new Banker();
        Player player = new Player();
        Rule rule = new Rule(cardDeck.getDeck());

        for (int i = 0; i < 2; i++) {
            Card card = rule.draw();
            banker.receiveCard(card);
            Card card2 = rule.draw();
            player.receiveCard(card2);
        }

        player.showCards("Player");
        banker.showCards("Banker");

        rule.additionalDraw(player, banker);

        player.showCards("Player");
        banker.showCards("Banker");

        System.out.println();
        if (rule.checkPlayerVictory(player, banker)) {
            System.out.println("플레이어측의 승리");
        } else if (rule.checkBankerVictory(player, banker)) {
            System.out.println("뱅커측의 승리");
        } else if (rule.checkTie(player, banker)) {
            System.out.println("무승부");
        }
    }
}
