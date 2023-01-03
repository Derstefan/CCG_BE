package com.ccg.ccgbe.cardgame.draw;


import com.ccg.ccgbe.cardgame.card.Card;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class PlaceCardDraw extends Draw {

    private Card card;

    private Pos pos;

    public PlaceCardDraw(Player player, Card card, Pos pos) {
        super(player);
        this.card = card;
        this.pos = pos;
    }

    public Pos getPos() {
        return pos;
    }

    public Card getCard() {
        return card;
    }



    public String toString(){
        String str = getPlayer().getName() + " plays at" +  pos.toString() + "\n";
        str+=card.toString();
        return str;

    }
    
}
