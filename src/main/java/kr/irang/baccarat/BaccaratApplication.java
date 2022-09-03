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
    }
}
