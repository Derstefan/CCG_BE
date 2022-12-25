package com.ccg.ccgbe.dto;

import com.ccg.ccgbe.cardgame.player.Hand;
import com.ccg.ccgbe.cardgame.state.map.Map;

public class GameDataDTO {

    private Map map;
    private Hand hand;

    private PlayerDTO turn;

    public GameDataDTO(Map map,Hand hand,PlayerDTO turn) {
        this.map = map;
        this.hand = hand;
        this.turn = turn;
    }

    public Map getMap() {
        return map;
    }

    public Hand getHand() {
        return hand;
    }

    public PlayerDTO getTurn() {
        return turn;
    }
}
