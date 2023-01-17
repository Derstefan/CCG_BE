package com.ccg.ccgbe.cardgame.rules.condition.statePosConition.fight;

import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public abstract class StateValue {


    public abstract int getActualValue(CardGameState state, Pos pos);

    public OperatorCondition moreOrEqual(StateValue fightValue) {
            return new OperatorCondition(this,">=",fightValue);
    }

}
