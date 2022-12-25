package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;


import com.ccg.ccgbe.cardgame.state.CardGameState;

public class EmptyElementCondition extends StatePosCondition {
    @Override
    public boolean check(CardGameState state) {
        return state.getElementAt(getPos())==null;
    }
}
