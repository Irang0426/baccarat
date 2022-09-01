package kr.irang.baccarat;

import kr.irang.baccarat.old.QuestionProviders;
import kr.irang.baccarat.old.Questions;
import kr.irang.baccarat.questions.BaseQuestionProvider;
import kr.irang.baccarat.questions.Question;
import kr.irang.baccarat.questions.QuestionProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

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

    static class QuestionCode {
        private static final Map<Question, BaseQuestionProvider> questions = new HashMap<>() {{
            AtomicBoolean login = new AtomicBoolean(false);

            put(Question.WELCOME, QuestionProvider.of("어서오세요.", options ->
                    options
                            .option("로그인", () -> get(Question.LOG_IN).run())
                            .option("회원가입", () -> get(Question.NEW_ACCOUNT))
                            .option("종료", () -> {
                                return;
                            })
            ));

            put(Question.LOG_IN, QuestionProvider.of("아이디와 패스워드를 입력해주세요.",
                    "아이디/패스워드",
                    () -> {
                        if (!login.get()) {
                            System.out.println("id?");
                            String id = new Scanner(System.in).nextLine();
                            System.out.println("pw?");
                            String pw = new Scanner(System.in).nextLine();
                            System.out.println("id : " + id);
                            System.out.println("pw : " + pw);
                        }
                        System.out.println("로그인 성공!");
                        login.set(true);
                        get(Question.GAME_START).run();
                    })
            );

            put(Question.GAME_START, QuestionProvider.of("게임이 시작됩니다.", options ->
                    options
                            .option("카드 덱을 섞습니다.", () -> {
                                CardDeck cardDeck = new CardDeck();
                                Banker banker = new Banker();
                                Player player = new Player();
                                Rule rule = new Rule(cardDeck.getDeck(), player, banker);
                            })
            ));

        }};
    }
}
