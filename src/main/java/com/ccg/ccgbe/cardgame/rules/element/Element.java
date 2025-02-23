package com.ccg.ccgbe.cardgame.rules.element;

import com.ccg.ccgbe.cardgame.rules.element.attribute.Attribute;
import com.ccg.ccgbe.cardgame.rules.functions.FunctionManager;
import com.ccg.ccgbe.cardgame.rules.functions.OnEndroundFunction;
import com.ccg.ccgbe.cardgame.rules.functions.OnEventFunction;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.HashSet;

public class Element {
    private String id;
    private String description;
    private String color = null;
    private boolean isBasic = false;
    private Attribute[] attributes = new Attribute[0];
    private FunctionManager func = new FunctionManager();

    public Element(String id, String color,String description, boolean isBasic) {
        this.id = id;
        this.color = color;
        this.description = description;
        this.isBasic = isBasic;

    }
    public Element(String id, String color,String description,boolean isBasic, Attribute ... attribute) {
        this.id = id;
        this.color = color;
        this.description = description;
        this.attributes = attribute;
        this.isBasic = isBasic;
    }



    public void addOnPlacementFunction(OnEventFunction f){
        func.addOnPlacementFunction(f);
    }

    public void addOnRemovingFunction(OnEventFunction f){
        func.addOnRemoveFunction(f);
    }

    public void addOnEndroundFunction(OnEndroundFunction f){
        func.add(f);
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
        return id;
    }

    public boolean equals(Element element){
        if(element==null) return false;
        return id.equals(element.getId());
    }



//-------------- GETTER ------------------

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public FunctionManager getFunc() {
        return func;
    }





    // ------------------------------ STATIC ------------------------------
    public static Element[] get(ElementCollector E,String Es){
        String[] elementsStrings = Es.split(",\\s*");
        Element[] elements = new Element[elementsStrings.length];

        for (int i = 0; i < elementsStrings.length; i++) {
            elements[i]=E.get(elementsStrings[i]);
        }
        return elements;
    }




}
