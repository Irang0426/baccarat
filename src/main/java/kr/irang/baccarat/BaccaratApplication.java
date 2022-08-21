package kr.irang.baccarat;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BaccaratApplication {
    public static void main(String[] args) {
//        SpringApplication.run(BaccaratApplication.class, args);
        Game game = new Game();
        Gamer gamer = new Gamer(10000);

        game.play(gamer);


//        QuestionProvider.provide(
//                "계속 하시겠습니까?", //question
//                (answer) -> {
//                    answer.setAnswer("Yes");
//                    answer.setAction(() -> {/*do Something*/});
//                },
//                (answer) -> {
//                    answer.setAnswer("No");
//                    answer.setAction(() -> {/*do Something*/});
//                }
//        );

        int i = 0;
        while (i == 0) {
            int result = QuestionProviders.of(Questions.Q_Continue).runWithResult();

            if (result == 1) {
                game.play(gamer);
            } else if (gamer.getMoney() == 0) {
                System.out.println("게임을 종료합니다.");
                i++;
            } else {
                System.out.println("게임을 종료합니다.");
                i++;
            }
        }
    }
}
