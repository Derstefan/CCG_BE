package com.ccg.ccgbe.cardgame.rules.rule;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public abstract class PerformingRule extends Rule{


    public PerformingRule(Element element, Condition condition) {
        super(element, condition);
    }

    public abstract void perform(CardGameState state, Pos pos);

}

