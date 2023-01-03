package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;


import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;

public class ElementCondition extends StatePosCondition {

    private Element element;

    public ElementCondition(Element element) {
        this.element = element;
    }



    @Override
    public boolean check(CardGameState state) {
        return element.equals(state.getElementAt(getPos()));
    }

    public static ElementCondition getInstance(Element e){
        return new ElementCondition(e);
    }

    public String toString(){
        return element+"-Cond";
    }
}
