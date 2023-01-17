package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.function.Function;

public class OnEndroundFunction {

    private String name;

    private Function<StatePosParam,Void> endroundFunction;

    public OnEndroundFunction(String name, Function<StatePosParam, Void> functionsEachRound) {
        this.name = name;
        this.endroundFunction = functionsEachRound;
    }

    public String getName() {
        return name;
    }

    public Function<StatePosParam, Void> getEndroundFunction() {
        return endroundFunction;
    }

    public void apply(CardGameState state, Pos pos){
        endroundFunction.apply(new StatePosParam(state,pos));
    }
}
