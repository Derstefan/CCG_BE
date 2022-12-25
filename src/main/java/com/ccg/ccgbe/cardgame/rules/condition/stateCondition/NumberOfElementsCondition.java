package com.ccg.ccgbe.cardgame.rules.condition.stateCondition;


import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.MapUtil;

public class NumberOfElementsCondition extends StateCondition {

    private int number;

    private Element element;


    public NumberOfElementsCondition(int number, Element element) {
        this.number = number;
        this.element = element;
    }

    @Override
    public boolean check(CardGameState state) {
        return MapUtil.getNumberOfElements(state,element)>=number;
    }

    public int getNumber() {
        return number;
    }

    public Element getElement() {
        return element;
    }


}
