package kr.irang.baccarat.controller;

import kr.irang.baccarat.domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BaccaratController {

    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public Map<String, Object> draw() {
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

        rule.additionalDraw(player, banker);

        Map<String, Object> map = new HashMap<>();
        map.put("Player", player.getCards());
        map.put("Banker", banker.getCards());
        map.put("Winner", rule.checkWinner(player, banker));
        return map;
    }
}
