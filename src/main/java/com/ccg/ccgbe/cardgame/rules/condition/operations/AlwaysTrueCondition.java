package com.ccg.ccgbe.cardgame.rules.condition.operations;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.state.CardGameState;

public class AlwaysTrueCondition extends Condition {


    @Override
    public boolean check(CardGameState state) {
        return true;
    }

    @Override
    public String toString() {
        return "true";
    }
}
