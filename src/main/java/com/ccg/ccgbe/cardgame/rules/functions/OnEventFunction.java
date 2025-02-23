package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.function.Function;

public class OnEventFunction {


    private String name;

    //first Event then apply (Element-param is the old Element on Pos)
    private Function<StateElementPosParam,Void> func;

    public OnEventFunction(String name, Function<StateElementPosParam, Void> func) {
        this.name = name;
        this.func = func;
    }

    public Function<StateElementPosParam, Void> getFunctionOnPlacement() {
        return func;
    }

    public String getName() {
        return name;
    }

    public void apply(CardGameState state, Element beforeElement, Pos pos){
        func.apply(new StateElementPosParam(state,beforeElement,pos));
    }
}
