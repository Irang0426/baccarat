package kr.irang.baccarat;

import kr.irang.baccarat.domain.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaccaratApplication {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
