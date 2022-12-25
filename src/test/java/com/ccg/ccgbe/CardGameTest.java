package com.ccg.ccgbe;

import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.bot.SimpleBot;
import com.ccg.ccgbe.cardgame.builder.EarthAirFireBuilder.EarthWaterAirFireBuilder;
import com.ccg.ccgbe.cardgame.builder.core.MapBuilder;
import com.ccg.ccgbe.cardgame.builder.core.SimpleCardBuilder;
import com.ccg.ccgbe.cardgame.builder.landscapeBuilder.LandScapeBuilder;
import com.ccg.ccgbe.cardgame.player.Deck;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.Rules;
import com.ccg.ccgbe.cardgame.state.map.Map;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CardGameTest {

    @Test
    void CardGameTest(){
        Rules rules = new LandScapeBuilder().createRules();
        SimpleCardBuilder b = new SimpleCardBuilder(rules.getE());
        MapBuilder mb = new MapBuilder(rules.getE());

        Deck d1 = new Deck(b.getRandomWxHCards(80,4,4));
        Deck d2 = new Deck(b.getRandomWxHCards(80,4,4));
        Deck d3 = new Deck(b.getRandomWxHCards(80,4,4));

        Player p1 = new Player("hans",d1);
        Player p2 = new Player("peter",d2);
        Player p3 = new Player("ursel",d3);



        CardGame game = new CardGame(p1,p2,rules,mb.generate());

        for(int i=0;i<300;i++){
            SimpleBot.doDrawOn(game);

            if(game.gameEnded()){
                System.out.println("game ended");
                break;
            }
        }



    }
}
