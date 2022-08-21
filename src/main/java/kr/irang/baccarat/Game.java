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


        System.out.println("배팅 할 곳을 선택하세요.");
        System.out.println("Player - 1");
        System.out.println("Banker - 2");
        Scanner st = new Scanner(System.in);
        gamer.setSelectTeam(st.nextInt());
        System.out.println("배팅 할 금액을 입력하세요. - 현재 게임 머니 : " + gamer.getMoney());
        Scanner bm = new Scanner(System.in);
        int bettingMoney = bm.nextInt();


        player.showCards("Player");
        banker.showCards("Banker");

        rule.additionalDraw(player, banker);

        System.out.println(rule.checkWinner(player, banker));

        if (rule.checkPlayerVictory(player, banker)) {
            if(gamer.getSelectTeam() == 1) {
                gamer.setMoney(gamer.getMoney() + bettingMoney);
                System.out.println(bettingMoney + "원을 획득하였습니다!!");
            } else {
                gamer.setMoney(gamer.getMoney() - bettingMoney);
                System.out.println(bettingMoney + "원을 잃었습니다ㅠㅠ");
            }
        } else if (rule.checkBankerVictory(player, banker)) {
            if(gamer.getSelectTeam() == 1) {
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
