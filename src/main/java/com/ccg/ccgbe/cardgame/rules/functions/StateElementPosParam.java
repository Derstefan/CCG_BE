package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class StateElementPosParam {


    private CardGameState state;


    private Element element;
    private Pos pos;


    public StateElementPosParam(CardGameState state, Element element, Pos pos) {
        this.state = state;
        this.element = element;
        this.pos = pos;
    }

    public CardGameState getState() {
        return state;
    }

    public Element getElement() {
        return element;
    }

    public Pos getPos() {
        return pos;
    }
}
