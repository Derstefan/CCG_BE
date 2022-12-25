package com.ccg.ccgbe.cardgame.rules.condition.operations;

import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class NotCondition extends Condition {

    private Condition cond;

    public NotCondition(Condition cond){
        this.cond = cond;

    }

    public Condition getCond() {
        return cond;
    }

    @Override
    public boolean check(CardGameState state) {
        return !cond.check(state);
    }

    @Override
    public void setPos(Pos pos){
        cond.setPos(pos);
    }
}
