package kr.irang.baccarat;

import kr.irang.baccarat.old.QuestionProviders;
import kr.irang.baccarat.old.Questions;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaccaratApplication {
    public static void main(String[] args) {
//        SpringApplication.run(BaccaratApplication.class, args);
        Game game = new Game();

        game.play();

        int i = 0;
        while (i == 0) {
            int result = QuestionProviders.of(Questions.Q_Continue).runWithResult();
            if (result == 1) {
                game.play();
            } else if (result == 2) {
                i++;
            }
        }
    }
}
