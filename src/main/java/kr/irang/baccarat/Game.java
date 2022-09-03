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

        QuestionCode.start();

//        CardDeck cardDeck = new CardDeck();
//        Banker banker = new Banker();
//        Player player = new Player();
//        Rule rule = new Rule(cardDeck.getDeck(), player, banker);
//
//        int money = 10000;
//
//        Gamer gamer = new Gamer(money);
//
//        for (int i = 0; i < 2; i++) {
//            Card card = rule.draw();
//            banker.receiveCard(card);
//            Card card2 = rule.draw();
//            player.receiveCard(card2);
//        }
//
//
//        int bettingSide = QuestionProviders.of(Questions.Q_BettingSide).runWithResult();
//        System.out.println("현재 게임 머니 : " + gamer.getMoney());
//        Scanner bm = new Scanner(System.in);
//        int bettingMoney = bm.nextInt();
//
//
//        player.showCards("Player");
//        banker.showCards("Banker");
//
//        rule.additionalDraw();
//
//        System.out.println(rule.checkWinner(player, banker));
//
//        if (rule.checkPlayerVictory()) {
//            if(bettingSide == 1) {
//                gamer.setMoney(gamer.getMoney() + bettingMoney);
//                System.out.println(bettingMoney + "원을 획득하였습니다!!");
//            } else {
//                gamer.setMoney(gamer.getMoney() - bettingMoney);
//                System.out.println(bettingMoney + "원을 잃었습니다ㅠㅠ");
//            }
//        } else if (rule.checkBankerVictory()) {
//            if(bettingSide == 1) {
//                gamer.setMoney(gamer.getMoney() - bettingMoney);
//                System.out.println(bettingMoney + "원을 잃었습니다ㅠㅠ");
//            } else {
//                gamer.setMoney(gamer.getMoney() + bettingMoney);
//                System.out.println(bettingMoney + "원을 획득하였습니다!!");
//            }
//        }
//
//        System.out.println("현재 게임 머니 = " + gamer.getMoney() + "\n");
    }

    static class QuestionCode {
        private static final Map<Question, BaseQuestionProvider> questions = new HashMap<>() {{
            AtomicBoolean login = new AtomicBoolean(false);
            AtomicBoolean pick = new AtomicBoolean(false);

            CardDeck cardDeck = new CardDeck();
            Banker banker = new Banker();
            Player player = new Player();
            Rule rule = new Rule(cardDeck.getDeck(), player, banker);

            int money = 10000;

            for (int i = 0; i < 2; i++) {
            Card card = rule.draw();
            banker.receiveCard(card);
            Card card2 = rule.draw();
            player.receiveCard(card2);
            }

            put(Question.WELCOME, QuestionProvider.of("어서오세요.", options ->
                    options
                            .option("로그인", () -> get(Question.LOG_IN).run())
                            .option("회원가입", () -> get(Question.NEW_ACCOUNT))
                            .option("종료", () -> {
                                return;
                            })
            ));

            // TODO : 데이터베이스 연동하여 로그인 기능 구현하기
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
                            System.out.println("로그인 성공!");
                        } else {
                            System.out.println("자동 로그인 성공!");
                        }
                        login.set(true);
                        get(Question.GAME_START).run();
                    })
            );

            put(Question.NEW_ACCOUNT, QuestionProvider.of("회원가입할 아이디와 패스워드를 입력해주세요.", options ->
                    options
                            .option("(미구현)", () -> get(Question.WELCOME).run())
            ));

            // TODO : 게임 구현하기
            put(Question.GAME_START, QuestionProvider.of("게임이 시작됩니다.", options ->
                    options
                            .option("(미구현)", () -> {
                                get(Question.GAME_BETTING).run();
                            })
            ));

            // TODO : 배팅 시스템 구현하기
            put(Question.GAME_BETTING, QuestionProvider.of("배팅 할 곳을 선택하세요.", options ->
                    options
                            .option("Player", () -> {
                                pick.set(true);
                                get(Question.GAME_COMPARE).run();
                            })
                            .option("Banker", () -> {
                                get(Question.GAME_COMPARE).run();
                            })
            ));
            put(Question.GAME_COMPARE, QuestionProvider.of("카드의 합을 비교합니다.", "비교",
                    () -> {
                        System.out.println(rule.checkWinner(player, banker));
                        get(Question.GAME_FINISHED).run();
                    })
            );

            put(Question.GAME_FINISHED, QuestionProvider.of("게임이 끝났습니다. 다시 하시겠습니까?", options ->
                    options
                            .option("YES (처음으로 돌아갑니다)", () -> get(Question.WELCOME).run())
                            .option("NO (게임을 종료합니다.)", () -> {
                                return;
                            })
            ));
        }};

        public static void start() {
            questions.get(Question.WELCOME).run();
        }
    }
}
