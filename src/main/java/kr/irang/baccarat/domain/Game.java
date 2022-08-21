package kr.irang.baccarat.domain;

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

        // ----------------------------------------------------------------------
        System.out.println("배탕 할 곳을 선택하세요.");
        System.out.println("Player - 1");
        System.out.println("Banker - 2");
        Scanner st = new Scanner(System.in);
        gamer.setSelectTeam(st.nextInt());
        System.out.println("배팅 할 금액을 입력하세요.");
        Scanner bm = new Scanner(System.in);
        int bettingMoney = bm.nextInt();
        // ----------------------------------------------------------------------

        player.showCards("Player");
        banker.showCards("Banker");

        rule.additionalDraw(player, banker);

        player.showCards("Player");
        banker.showCards("Banker");

        System.out.println();
        if (rule.checkPlayerVictory(player, banker)) {
            System.out.println("플레이어측의 승리");
            if(gamer.getSelectTeam() == 1) {
                gamer.setMoney(gamer.getMoney() + bettingMoney);
            } else {
                gamer.setMoney(gamer.getMoney() - bettingMoney);
            }
        } else if (rule.checkBankerVictory(player, banker)) {
            System.out.println("뱅커측의 승리");
            if(gamer.getSelectTeam() == 1) {
                gamer.setMoney(gamer.getMoney() - bettingMoney);
            } else {
                gamer.setMoney(gamer.getMoney() + bettingMoney);
            }
        } else if (rule.checkTie(player, banker)) {
            System.out.println("무승부");
        }

        System.out.println("현재 게임 머니 = " + gamer.getMoney() + "\n");
    }
}
