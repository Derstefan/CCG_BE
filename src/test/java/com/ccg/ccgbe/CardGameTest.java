package com.ccg.ccgbe;

import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.bot.SimpleBot;
import com.ccg.ccgbe.cardgame.builder.SimpleCardBuilder;
import com.ccg.ccgbe.library.ElementCreator;
import com.ccg.ccgbe.library.LibraryBuilder;
import com.ccg.ccgbe.library.MapCreator;
import com.ccg.ccgbe.cardgame.player.Deck;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.state.map.Map;
import org.junit.jupiter.api.Test;

public class CardGameTest {

    @Test
    void CardGameTest(){
        ElementCollector E = new ElementCreator().create();
        RuleLibrary ruleLibrary = new LibraryBuilder(E).createLibrary();
        Map map = new MapCreator(E).generateMap();
        SimpleCardBuilder b = new SimpleCardBuilder(E);

        Deck d1 = new Deck(b.getRandomWxHCards(80,4,4));
        Deck d2 = new Deck(b.getRandomWxHCards(80,4,4));
        Deck d3 = new Deck(b.getRandomWxHCards(80,4,4));

        Player p1 = new Player("hans",d1);
        Player p2 = new Player("peter",d2);
        Player p3 = new Player("ursel",d3);



        CardGame game = new CardGame(p1,p2, ruleLibrary,map);

        for(int i=0;i<300;i++){
            SimpleBot.doDrawOn(game);

            if(game.gameEnded()){
                System.out.println("game ended");
                break;
            }
        }



    }
}
