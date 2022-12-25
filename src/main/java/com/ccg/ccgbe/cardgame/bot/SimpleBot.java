package com.ccg.ccgbe.cardgame.bot;

import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.draw.DoNothingDraw;
import com.ccg.ccgbe.cardgame.draw.Draw;
import com.ccg.ccgbe.cardgame.draw.PlaceCardDraw;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class SimpleBot {


    public static void doDrawOn(CardGame game){
        Player turn = game.getTurn();

        //System.out.println(turn.getName() +" can do: \n"+            turn.getHand().toString());
        Draw draw = randomDraw(turn, game.getWidth(), game.getHeight());
        //System.out.println(draw.toString());
        game.doDraw(draw);
        //System.out.println(game.toString());
    }

    public static Draw randomDraw(Player p,int maxW, int maxH){
        if(p.hasEmptyHand()){
            return new DoNothingDraw(p);
        }
        return new PlaceCardDraw(p,p.getHand().getCard(rand(p.getHand().getCards().size())),randomPos(maxW,maxH));
    }
    public static Pos randomPos(int w, int h){
        return new Pos(rand(w),rand(h));
    }

    public static int rand(int l){
        return (int)Math.round(Math.random()*(l-1));
    }
}
