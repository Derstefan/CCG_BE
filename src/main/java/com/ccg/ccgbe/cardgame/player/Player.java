package com.ccg.ccgbe.cardgame.player;


import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.card.Card;
import com.ccg.ccgbe.dto.PlayerDTO;

import java.util.Optional;

public class Player {

    private String name;
    private Hand hand = new Hand();

    private Deck deck;

    public Player(String name,Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean hasEmptyHand(){
        return hand.getCards().isEmpty();
    }

    public void drawNewCard(){
        if(hand.size()>= Config.HAND_MAX_SIZE){
            return;
        }
        Optional<Card> card = deck.getCard();
        if(card.isPresent()){
            deck.removeCard();
            hand.addCard(card.get());
        }
    }

    public PlayerDTO getDTO(){
        return new PlayerDTO(name);
    }
}
