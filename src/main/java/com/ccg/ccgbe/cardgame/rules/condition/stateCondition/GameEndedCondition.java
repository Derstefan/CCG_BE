package com.ccg.ccgbe.cardgame.rules.condition.stateCondition;


import com.ccg.ccgbe.cardgame.state.CardGameState;

public class GameEndedCondition extends StateCondition {

    @Override
    public boolean check(CardGameState state) {
        return state.isGameEnded();
    }

    @Override
    public String toString() {
        return null;
    }
}
