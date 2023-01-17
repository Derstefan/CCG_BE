package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;

public class FunctionManager {


    //first Placement then apply (Element-param is the old Element on Pos)
    private ArrayList<OnEventFunction> functionsOnPlacement = new ArrayList<>();
    //first Replace then apply (Element-param is the old Element on Pos)
    private ArrayList<OnEventFunction> functionsOnRemove = new ArrayList<>();

    private ArrayList<OnEndroundFunction> functionsEachRound = new ArrayList<>();


    public ArrayList<OnEventFunction> getFunctionsOnPlacement() {
        return functionsOnPlacement;
    }

    public ArrayList<OnEventFunction> getFunctionsOnRemove() {
        return functionsOnRemove;
    }

    public ArrayList<OnEndroundFunction> getFunctionsEachRound() {
        return functionsEachRound;
    }


    public void performPlacement(CardGameState state, Element beforeElement, Pos pos) {
        for(OnEventFunction f: functionsOnPlacement){
            f.apply(state,beforeElement,pos);
        }
    }

    public void performRemove(CardGameState state, Element beforeElement, Pos pos) {
        for(OnEventFunction f: functionsOnRemove){
            f.apply(state,beforeElement,pos);
        }
    }

    public void performEndRound(CardGameState state, Pos pos) {
        for(OnEndroundFunction f: functionsEachRound){
            f.apply(state,pos);
        }
    }

    public void addOnPlacementFunction(OnEventFunction f){
        functionsOnPlacement.add(f);
    }

    public void addOnRemoveFunction(OnEventFunction f){
        functionsOnRemove.add(f);
    }

    public void add(OnEndroundFunction f){
        functionsEachRound.add(f);
    }

}
