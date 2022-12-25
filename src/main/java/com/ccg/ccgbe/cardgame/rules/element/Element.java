package com.ccg.ccgbe.cardgame.rules.element;

import com.ccg.ccgbe.cardgame.rules.functions.FunctionManager;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Element {
    private EType type;

  //TODO: tags for functionsss: swortds, tower, forest,town,smith
//    private ArrayList<Tag> tags;

    private FunctionManager func = new FunctionManager();

    public Element(EType type) {
        this.type = type;
    }

    public Element(String typeName) {
        this.type = new EType(typeName);
    }

    public EType getType() {
        return type;
    }

    public FunctionManager getFunc() {
        return func;
    }

    public boolean performsOnPlacement() {
        return !func.getFunctionsOnPlacement().isEmpty();
    }

    public boolean performsEachRound() {
        return !func.getFunctionsEachRound().isEmpty();
    }

    public boolean performsOnRemove() {
        return !func.getFunctionsOnRemove().isEmpty();
    }

    public void performPlacement(CardGameState state, Element beforeElement, Pos pos) {
        func.performPlacement(state,beforeElement,pos);
    }

    public void performRemove(CardGameState state, Element beforeElement, Pos pos) {
        func.performRemove(state,beforeElement,pos);
    }

    public void performEndRound(CardGameState state, Pos pos) {
        func.performEndRound(state,pos);
    }



    public String toString(){
        return type.toString();
    }

    public boolean equals(Element element){
        if(element==null) return false;
        return type.equals(element.type);

    }
}
