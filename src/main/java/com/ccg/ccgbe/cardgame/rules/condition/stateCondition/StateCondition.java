package com.ccg.ccgbe.cardgame.rules.condition.stateCondition;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;

public abstract class StateCondition extends Condition {

    public static final GameEndedCondition GAME_ENDED_CONDITION = new GameEndedCondition();

    public static final MoreElementsCondition getMoreElementsCondition(Element e1, Element e2){
        return new MoreElementsCondition(e1,e2);
    }

    public static final NumberOfElementsCondition getNumberOfElementsCondition(int number,Element e){
        return new NumberOfElementsCondition(number,e);
    }
}
