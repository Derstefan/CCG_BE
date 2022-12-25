package com.ccg.ccgbe.GameManager;


import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.builder.EarthAirFireBuilder.EarthWaterAirFireBuilder;
import com.ccg.ccgbe.cardgame.builder.core.MapBuilder;
import com.ccg.ccgbe.cardgame.builder.core.SimpleCardBuilder;
import com.ccg.ccgbe.cardgame.builder.landscapeBuilder.LandScapeBuilder;
import com.ccg.ccgbe.cardgame.card.Card;
import com.ccg.ccgbe.cardgame.player.Deck;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.Rules;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class GameManager {


    private Map<UUID, CardGame> games = new HashMap<>();

    public GameManager() {
        createGame_Test();
    }

    public CardGame createGame_Test() {
        LandScapeBuilder lb = new LandScapeBuilder();
        Rules rules = lb.createRules();

        SimpleCardBuilder b = new SimpleCardBuilder(rules.getE());


        Deck d1 = new Deck(b.getRandomWxHCards(800,2,2));
        Deck d2 = new Deck(b.getRandomWxHCards(800,2,2));

        Player p1 = new Player("hans",d1);
        Player p2 = new Player("peter",d2);


        CardGame game = new CardGame(p1,p2,rules,lb.generateMap());
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
