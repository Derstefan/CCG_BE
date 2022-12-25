package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public abstract class StatePosCondition extends Condition {

    private Pos pos;


    @Override
    public void setPos(Pos pos) {
        this.pos = pos;
    }

    @Override
    public Pos getPos() {
        return pos;
    }


    //pos needs to be set before
    public abstract boolean check(CardGameState state);
}
