package com.ccg.ccgbe.cardgame.rules.rule;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class Rule {

    private Element element;

    private Element beforeElement;

    private Condition condition;

    private String description = "";

    public Rule(Element element,Element beforeElement, Condition condition) {
        this.element = element;
        this.beforeElement = beforeElement;
        this.condition = condition;
    }

    public Element getElement() {
        return element;
    }

    public boolean check(CardGameState state, Pos pos) {
        if(state.getElementAt(pos).equals(beforeElement)){
            condition.setPos(pos);
            return condition.check(state);
        }
        return false;
    }


    public String getDescription() {
        if("".equals(description)){
            description=condition.toString();
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getCondition() {
        return condition;
    }

    public Element getBeforeElement() {
        return beforeElement;
    }

    public String toString(){
        return element.toString() + " if " + condition.toString();
    }
}
