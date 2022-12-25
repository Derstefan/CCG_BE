package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.function.Function;

public class OnRemovingFunction {

    private String name;

    //first Replace then apply (Element-param is the old Element on Pos)
    private Function<StateElementPosParam,Void> functionOnRemove;

    public OnRemovingFunction(String name, Function<StateElementPosParam, Void> functionsOnRemove) {
        this.name = name;
        this.functionOnRemove = functionsOnRemove;
    }

    public String getName() {
        return name;
    }

    public Function<StateElementPosParam, Void> getFunctionOnRemove() {
        return functionOnRemove;
    }

    public void apply(CardGameState state, Element beforeElement, Pos pos){
        functionOnRemove.apply(new StateElementPosParam(state,beforeElement,pos));
    }
}
