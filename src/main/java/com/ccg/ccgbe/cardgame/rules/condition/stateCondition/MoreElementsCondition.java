package com.ccg.ccgbe.cardgame.rules.condition.stateCondition;


import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.MapUtil;

public class MoreElementsCondition extends StateCondition {

    private Element more;

    private Element than;

    public MoreElementsCondition(Element more, Element than) {
        this.more = more;
        this.than = than;
    }

    @Override
    public boolean check(CardGameState state) {
        int n1 = MapUtil.getNumberOfElements(state,more);
        int n2 = MapUtil.getNumberOfElements(state,than);
        return n1>n2;
    }

    @Override
    public String toString() {
        return null;
    }
}
