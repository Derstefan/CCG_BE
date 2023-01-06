package com.ccg.ccgbe.cardgame.builder;

import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.operations.AlwaysTrueCondition;
import com.ccg.ccgbe.cardgame.rules.condition.operations.PropabilityCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.*;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.map.Pos;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
public class RulesBuilder {

    private ElementCollector E;

    private Element A;

    private Element[] elements;

    private Condition[] conditions;

    private Random rand;

    public RulesBuilder() {
        rand=new Random();
    }

    public RulesBuilder(Random rand) {
        this.rand = rand;
    }

    public RulesBuilder rule(String A, String on){
        this.A=E.get(A);
        String[] eStr = on.split(",\\s*");
        this.elements = new Element[eStr.length];
        this.conditions = new Condition[eStr.length];
        for (int i = 0; i < eStr.length; i++) {
            elements[i]=E.get(eStr[i]);
            if(elements[i]==null)throw new IllegalArgumentException(eStr[i] +" is no Element");
        }
        return this;
    }

    public ArrayList<Rule> border(String A,int minA,int minB, String B){
        ArrayList<Rule> ruleSet = new ArrayList<>();
        ruleSet.addAll(rule(A,B).when(min(minA,A)).create());
        ruleSet.addAll(rule(B,A).when(min(minB,B)).create());
        return ruleSet;
    }



    public RulesBuilder when(Condition condition){

        for (int i = 0; i < conditions.length; i++) {
            conditions[i] = condition;
        }

        return this;
    }

    //Conditions
    public Condition min(int n,String Es){
        return around(EComparator.min,1,n,Es);
    }

    public Condition max(int n,String Es){
        return around(EComparator.max,1,n,Es);
    }

    public Condition exact(int n,String Es){
        return around(EComparator.exact,1,n,Es);
    }

    public Condition exactNeighbours(int n,String Es){
        return aroundNeighbours(EComparator.exact,n,Es);
    }

    public Condition minNeighbours(int n,String Es){
        return aroundNeighbours(EComparator.min,n,Es);
    }

    public Condition maxNeighbours(int n,String Es){
        return aroundNeighbours(EComparator.max,n,Es);
    }

    public Condition min2(int n,String Es){
        return around(EComparator.min,2,n,Es);
    }

    public Condition max2(int n,String Es){
        return around(EComparator.max,2,n,Es);
    }

    public Condition exact2(int n,String Es){
        return around(EComparator.exact,2,n,Es);
    }

/*    public Condition between(String A,String B){
        return
    }*/

    public Condition at(Pos vec, String A){

        return new ElementAtCondition(vec,E.get(A));
    }

    public Condition wsk(double probability){
        return new PropabilityCondition(rand,probability);
    }

    public Condition around(String Es){
        String[] elementsStrings = Es.split(",\\s*");
        if(elementsStrings.length!=8)throw new IllegalArgumentException(Es.length() + "is not correct aroundString");

        Condition c = new AlwaysTrueCondition();
        for (int i = 0; i < elementsStrings.length; i++) {
            if (elementsStrings[i].equals(Config.NO_ELEMENT))continue;
            c=c.AND(at(Pos.IPos(i),elementsStrings[i]));
        }
        return c;
    }

    public Condition around(EComparator comp,int radius,int n,String Es){
        Element[] elements = Helper.getElementsFromString(E,Es);
        return new AroundCondition(comp,radius,n,elements);
    }

    public Condition aroundNeighbours(EComparator comp,int n,String Es){
        String[] elementsStrings = Es.split(",\\s*");
        Element[] elements = new Element[elementsStrings.length];

        for (int i = 0; i < elementsStrings.length; i++) {
            elements[i]=E.get(elementsStrings[i]);
        }
        return new AroundDirectCondition(comp,n,elements);
    }

    public ArrayList<Rule> create(){
        ArrayList<Rule> ruleSet = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            Rule r = new Rule(A,elements[i],conditions[i]);
            ruleSet.add(r);
//            log.info(conditions[i].toString());
        }
        return ruleSet;
    }


    public Element getElement(String A){
        return E.get(A);
    }

    public Element[] getElements(String Es){
        return Helper.getElementsFromString(E,Es);
    }

    public void setE(ElementCollector e) {
        E = e;
    }

    public ElementCollector getE() {
        return E;
    }
}
