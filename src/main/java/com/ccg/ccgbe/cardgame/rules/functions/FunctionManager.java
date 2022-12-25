package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.function.Function;

public class FunctionManager {


    //first Placement then apply (Element-param is the old Element on Pos)
    private ArrayList<OnPlacementFunction> functionsOnPlacement = new ArrayList<>();
    //first Replace then apply (Element-param is the old Element on Pos)
    private ArrayList<OnRemovingFunction> functionsOnRemove = new ArrayList<>();

    private ArrayList<PerformEarchRoundFunction> functionsEachRound = new ArrayList<>();


    public ArrayList<OnPlacementFunction> getFunctionsOnPlacement() {
        return functionsOnPlacement;
    }

    public ArrayList<OnRemovingFunction> getFunctionsOnRemove() {
        return functionsOnRemove;
    }

    public ArrayList<PerformEarchRoundFunction> getFunctionsEachRound() {
        return functionsEachRound;
    }


    public void performPlacement(CardGameState state, Element beforeElement, Pos pos) {
        for(OnPlacementFunction f: functionsOnPlacement){
            f.apply(state,beforeElement,pos);
        }
    }

    public void performRemove(CardGameState state, Element beforeElement, Pos pos) {
        for(OnRemovingFunction f: functionsOnRemove){
            f.apply(state,beforeElement,pos);
        }
    }

    public void performEndRound(CardGameState state, Pos pos) {
        for(PerformEarchRoundFunction f: functionsEachRound){
            f.apply(state,pos);
        }
    }

    public void add(OnPlacementFunction f){
        functionsOnPlacement.add(f);
    }

    public void add(OnRemovingFunction f){
        functionsOnRemove.add(f);
    }

    public void add(PerformEarchRoundFunction f){
        functionsEachRound.add(f);
    }

}
