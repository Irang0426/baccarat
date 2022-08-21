package kr.irang.baccarat;

import java.util.Scanner;

public class Game {
    public void play() {

        CardDeck cardDeck = new CardDeck();
        Banker banker = new Banker();
        Player player = new Player();
        Rule rule = new Rule(cardDeck.getDeck(), player, banker);

        int money = 10000;

        Gamer gamer = new Gamer(money);

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

        rule.additionalDraw();

        System.out.println(rule.checkWinner(player, banker));

        if (rule.checkPlayerVictory()) {
            if(bettingSide == 1) {
                gamer.setMoney(gamer.getMoney() + bettingMoney);
                System.out.println(bettingMoney + "원을 획득하였습니다!!");
            } else {
                gamer.setMoney(gamer.getMoney() - bettingMoney);
                System.out.println(bettingMoney + "원을 잃었습니다ㅠㅠ");
            }
        } else if (rule.checkBankerVictory()) {
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
