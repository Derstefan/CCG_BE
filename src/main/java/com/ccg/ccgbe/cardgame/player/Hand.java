package com.ccg.ccgbe.cardgame.player;


import com.ccg.ccgbe.cardgame.card.Card;

import java.util.ArrayList;
import java.util.UUID;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index){
        if(cards.isEmpty())return null;
        return cards.get(index);
    }

    public boolean includes(Card card){
        if(cards.indexOf(card)!=-1){
            return true;
        }
        return false;
    }

    public String toString(){
        String str = "";
        for(Card c:cards){
            str+=c.toString()+"\n";
        }
        return str;
    }

    public Card getByUuid(UUID cardId){
        for(Card c:cards){
            if(c.getCardId().equals(cardId)){
                return c;
            }
        }
        return null;
    }

}