package com.ccg.ccgbe.GameManager;


import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.builder.SimpleCardBuilder;
import com.ccg.ccgbe.cardgame.builder.landscapeBuilder.LandScapeBuilder;
import com.ccg.ccgbe.cardgame.player.Deck;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class GameManager {


    private Map<UUID, CardGame> games = new HashMap<>();

    public GameManager() {
        createGame_Test();
    }

    public CardGame createGame_Test() {
        LandScapeBuilder lb = new LandScapeBuilder();
        RuleLibrary ruleLibrary = lb.createRules();
        //log.info(rules.toString());

        SimpleCardBuilder b = new SimpleCardBuilder(ruleLibrary.getE());


        Deck d1 = new Deck(b.getRandomWxHCards(800,3,3));
        Deck d2 = new Deck(b.getRandomWxHCards(800,3,3));
        Deck d3 = new Deck(b.getRandomWxHCards(800,3,3));

        Player p1 = new Player("hans",d1);
        Player p2 = new Player("peter",d2);
        Player p3 = new Player("ursel",d3);


        CardGame game = new CardGame(new ArrayList<>(Arrays.asList(p1,p2,p3)), ruleLibrary,lb.generateMap());
        //log.info(game.toString());

        this.games.put(game.getGameId(), game);
        return game;
    }

    public void clearGames(){
        this.games = new HashMap<>();
    }


    public CardGame getFirstGame() {
        CardGame g = (CardGame)games.values().toArray()[0];
        //log.info(g.toString());
        return (CardGame)games.values().toArray()[0];
    }


    public CardGame getGameById(UUID gameId) {
        return games.get(gameId);
    }
}
