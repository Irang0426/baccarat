package kr.irang.baccarat;

import java.util.Scanner;

public class Game {
    public void play(Gamer gamer) {

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


        int bettingSide = QuestionProviders.of(Questions.Q_BettingSide).runWithResult();
        System.out.println("현재 게임 머니 : " + gamer.getMoney());
        Scanner bm = new Scanner(System.in);
        int bettingMoney = bm.nextInt();


        player.showCards("Player");
        banker.showCards("Banker");

        rule.additionalDraw(player, banker);

        System.out.println(rule.checkWinner(player, banker));

        if (rule.checkPlayerVictory(player, banker)) {
            if(bettingSide == 1) {
                gamer.setMoney(gamer.getMoney() + bettingMoney);
                System.out.println(bettingMoney + "원을 획득하였습니다!!");
            } else {
                gamer.setMoney(gamer.getMoney() - bettingMoney);
                System.out.println(bettingMoney + "원을 잃었습니다ㅠㅠ");
            }
        } else if (rule.checkBankerVictory(player, banker)) {
            if(bettingSide == 1) {
                gamer.setMoney(gamer.getMoney() - bettingMoney);
                System.out.println(bettingMoney + "원을 잃었습니다ㅠㅠ");
            } else {
                gamer.setMoney(gamer.getMoney() + bettingMoney);
                System.out.println(bettingMoney + "원을 획득하였습니다!!");
            }
        }

        System.out.println("현재 게임 머니 = " + gamer.getMoney() + "\n");
    }
}
