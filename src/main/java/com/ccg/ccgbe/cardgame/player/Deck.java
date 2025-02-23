package com.ccg.ccgbe.cardgame.player;


import com.ccg.ccgbe.library.Config;
import com.ccg.ccgbe.cardgame.card.Card;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(ArrayList<Card> cards) {
        if(Objects.isNull(cards))throw new NullPointerException();
        if(cards.size()< Config.DECK_MIN_SIZE || cards.size()>Config.DECK_MAX_SIZE){
            throw new IllegalArgumentException();
        }
        this.cards = cards;
        shuffle();
    }

    public void shuffle(){
//TODO:
    }

    public Optional<Card> getCard() {
        int size = cards.size()-1;
        if(size!=0){
            Optional<Card> c = Optional.of(cards.get(size-1));
            return c;
        }
        return Optional.empty();
    }

    public boolean removeCard(){
        int size = cards.size()-1;
        if(size!=0){
            cards.remove(size-1);
            return true;
        }
        return false;
    }


    public Deck clone(){
        ArrayList<Card> clone = (ArrayList<Card>) cards.clone();
        return new Deck(clone);
    }
}