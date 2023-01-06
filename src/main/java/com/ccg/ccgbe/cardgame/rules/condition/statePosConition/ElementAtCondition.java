package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;

import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementAtCondition extends StatePosCondition {

    private Element[] elements;

    private Pos vec;

    public ElementAtCondition( Pos vec,Element ... elements) {
        this.elements = elements;
        this.vec = vec;
    }

    @Override
    public boolean check(CardGameState state) {
        Pos pos = getPos();
        Pos indexPos = pos.plus(vec);
        if(!state.isOnMap(indexPos))return false;
        return Arrays.asList(elements).contains(state.getElementAt(indexPos));
    }

    @Override
    public String toString() {
        return null;
    }
}
