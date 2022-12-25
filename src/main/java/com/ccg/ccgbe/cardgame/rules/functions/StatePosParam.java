package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class StatePosParam {

    private CardGameState state;

    private Pos pos;

    public StatePosParam(CardGameState state, Pos pos) {
        this.state = state;
        this.pos = pos;
    }

    public CardGameState getState() {
        return state;
    }

    public Pos getPos() {
        return pos;
    }
}
