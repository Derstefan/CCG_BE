package com.ccg.ccgbe;

import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.bot.SimpleBot;
import com.ccg.ccgbe.cardgame.builder.MapBuilder;
import com.ccg.ccgbe.cardgame.builder.SimpleCardBuilder;
import com.ccg.ccgbe.cardgame.builder.landscapeBuilder.LandScapeBuilder;
import com.ccg.ccgbe.cardgame.player.Deck;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import org.junit.jupiter.api.Test;

public class CardGameTest {

    @Test
    void CardGameTest(){
        RuleLibrary ruleLibrary = new LandScapeBuilder().createRules();
        SimpleCardBuilder b = new SimpleCardBuilder(ruleLibrary.getE());
        MapBuilder mb = new MapBuilder(ruleLibrary.getE());

        Deck d1 = new Deck(b.getRandomWxHCards(80,4,4));
        Deck d2 = new Deck(b.getRandomWxHCards(80,4,4));
        Deck d3 = new Deck(b.getRandomWxHCards(80,4,4));

        Player p1 = new Player("hans",d1);
        Player p2 = new Player("peter",d2);
        Player p3 = new Player("ursel",d3);



        CardGame game = new CardGame(p1,p2, ruleLibrary,mb.generate());

        for(int i=0;i<300;i++){
            SimpleBot.doDrawOn(game);

            if(game.gameEnded()){
                System.out.println("game ended");
                break;
            }
        }



    }
}
