package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.function.Function;

public class PerformEarchRoundFunction {

    private String name;

    private Function<StatePosParam,Void> functionEachRound;

    public PerformEarchRoundFunction(String name, Function<StatePosParam, Void> functionsEachRound) {
        this.name = name;
        this.functionEachRound = functionsEachRound;
    }

    public String getName() {
        return name;
    }

    public Function<StatePosParam, Void> getFunctionEachRound() {
        return functionEachRound;
    }

    public void apply(CardGameState state, Pos pos){
        functionEachRound.apply(new StatePosParam(state,pos));
    }
}
