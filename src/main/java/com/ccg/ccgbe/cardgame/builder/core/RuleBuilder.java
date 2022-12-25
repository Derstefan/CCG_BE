package com.ccg.ccgbe.cardgame.builder.core;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.ElementCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.EmptyElementCondition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;

import java.util.ArrayList;
import java.util.HashSet;

public class RuleBuilder {

    private ElementCollector E;

    private ConditionBuilder cb;

    private ArrayList<Rule> ruleSet = new ArrayList<>();

    public RuleBuilder(ElementCollector E) {
        this.E = E;
        this.cb =  new ConditionBuilder(E);
    }


    /**
     * Expand with condition of min of expanding Elements
     * @param A expander
     * @param B defender
     * @param minA min number of A elements around the expanding B-cell
     */
    public void expand(String A, String B,int minA){
        rule(A,B,cb.around(minA,A));
    }

    /**
     * Expand with condition of min third Elements
     * @param A expander
     * @param B defender
     * @param minC min number of necessary third Elements
     * @param C third element
     */
    public void expand(String A, String B,int minC,String ... C){

        rule(A,B,cb.around(minC,C));
    }

    /**
     * Expand with condition of min third Elements
     * @param A expander
     * @param B defender
     * @param minC min number of necessary third Elements
     * @param C third element
     */
    public void expand(String A, String B,int minC,String C){
        String[] elements = new String[1];
        elements[0] = C;
        rule(A,B,cb.around(minC,elements));
    }


    /**
     *     border between two elements
     *     A needs minA As to expand on B and
     *     B needs minB Bs to expand on A
     *
     *
     *     e.g.
     *     A,4,4,B ~ stabil border
     *     A,3,4,B ~ A expandive towards B
     *     A,3,3,B ~ dynamic border
     *
     * @param A first Element
     * @param minA min number of As around to expand on B
     * @param minB min number of Bs around to expand on A
     * @param B second Element
     */
    public void border(String A,int minA,int minB, String B){
        rule(A,B,cb.around(minA,A));
        rule(B,A,cb.around(minB,B));
    }

    public void border(String A, String B){
        // A around> B around
//        new Rule("A",)
    }


    public void rule(String A, String B){
        if(!E.containsElement(A) || !E.containsElement(B)){
            throw new IllegalArgumentException();
        }
        Element e1 = E.get(A);
        Element e2 = E.get(B);
        ruleSet.add(new Rule(e1,new ElementCondition(e2)));
    }

    public void rule(String A, String B, Condition when){
        if(!E.containsElement(A) || !E.containsElement(B)){
            throw new IllegalArgumentException();
        }
        Element e1 = E.get(A);
        Element e2 = E.get(B);
        ruleSet.add(new Rule(e1,new ElementCondition(e2).AND(when)));
    }

    public void rule(String A, Condition condition){
        if(!E.containsElement(A)){
            throw new IllegalArgumentException();
        }
        Element e = E.get(A);
        ruleSet.add(new Rule(e,condition));
    }



    //can put over empty cell
    public void rule(String A){
        Element e = E.get(A);
        if(!E.containsElement(A)){
            throw new IllegalArgumentException();
        }
        ruleSet.add( new Rule(e, new EmptyElementCondition()));
    }


    public ArrayList<Rule> createRuleSet(){
        return ruleSet;
    }
}
