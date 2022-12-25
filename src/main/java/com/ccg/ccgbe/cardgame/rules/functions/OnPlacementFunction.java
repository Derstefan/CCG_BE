package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.function.Function;

public class OnPlacementFunction {


    private String name;

    //first Placement then apply (Element-param is the old Element on Pos)
    private Function<StateElementPosParam,Void> functionOnPlacement;

    public OnPlacementFunction(String name, Function<StateElementPosParam, Void> functionsOnPlacement) {
        this.name = name;
        this.functionOnPlacement = functionsOnPlacement;
    }

    public Function<StateElementPosParam, Void> getFunctionOnPlacement() {
        return functionOnPlacement;
    }

    public String getName() {
        return name;
    }

    public void apply(CardGameState state, Element beforeElement, Pos pos){
        functionOnPlacement.apply(new StateElementPosParam(state,beforeElement,pos));
    }
}
