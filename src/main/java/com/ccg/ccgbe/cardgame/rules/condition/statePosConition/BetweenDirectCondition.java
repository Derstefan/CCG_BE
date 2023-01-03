package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;

import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class BetweenDirectCondition extends Condition {

    private Element A;

    private Element B;

//TODO:


    public BetweenDirectCondition(Element a, Element b) {
        A = a;
        B = b;
    }

    @Override
    public boolean check(CardGameState state) {

        Pos[] ps = new Pos[]{new Pos(-1,0),new Pos(0,-1),new Pos(1,0),new Pos(0,1)};
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
