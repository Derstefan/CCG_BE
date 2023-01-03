package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;

import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class ElementAtCondition extends Condition {

    private Element element;

    private Pos vec;

    public ElementAtCondition(Element element, Pos vec) {
        this.element = element;
        this.vec = vec;
    }

    @Override
    public boolean check(CardGameState state) {
        Pos pos = getPos();
        Pos indexPos = pos.plus(vec);
        return element.equals(state.getElementAt(indexPos));
    }

    @Override
    public String toString() {
        return null;
    }
}
