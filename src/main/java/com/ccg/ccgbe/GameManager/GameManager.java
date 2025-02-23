package com.ccg.ccgbe.GameManager;


import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.builder.SimpleCardBuilder;
import com.ccg.ccgbe.library.ElementCreator;
import com.ccg.ccgbe.library.LibraryBuilder;
import com.ccg.ccgbe.library.MapCreator;
import com.ccg.ccgbe.cardgame.player.Deck;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.ccg.ccgbe.cardgame.state.map.Map;


import java.util.*;

@Component
@Slf4j
public class GameManager {


    private HashMap<UUID, CardGame> games = new HashMap<>();

    public GameManager() {
        createGame_Test();
    }

    public CardGame createGame_Test() {
        ElementCollector E = new ElementCreator().create();
        RuleLibrary ruleLibrary = new LibraryBuilder(E).createLibrary();
        Map map = new MapCreator(E).generateMap();
        SimpleCardBuilder b = new SimpleCardBuilder(E);

        Deck d1 = new Deck(b.getRandomWxHCards(800,3,3,"Orks"));
        Deck d2 = new Deck(b.getRandomWxHCards(800,3,3,"Human"));
        Deck d3 = new Deck(b.getRandomWxHCards(800,3,3,"elf"));

        Player p1 = new Player("hanspeterOrk",d1);
        Player p2 = new Player("heinrichMensch",d2);
        Player p3 = new Player("elfriede",d3);


        CardGame game = new CardGame(new ArrayList<>(Arrays.asList(p1,p2,p3)), ruleLibrary,map);
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
