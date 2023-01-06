package com.ccg.ccgbe.cardgame.builder;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;

import java.util.ArrayList;
import java.util.Arrays;

public class RuleBuilder {

    private ElementCollector E;

    private ConditionBuilder cb;

    private ArrayList<Rule> ruleSet = new ArrayList<>();

    public RuleBuilder(ElementCollector E) {
        this.E = E;
        this.cb =  new ConditionBuilder(E);
    }



    /**
     * Expand with condition of min third Elements
     * @param A expander
     * @param B defender
     * @param minC min number of necessary third Elements
     * @param C third element
     */
    public void expand(String A, String B,int minC,String ... C){

        Rule r = rule(A,B,cb.around(minC,C));
        r.setDescription(A + " needs " + minC + " " + Arrays.stream(C).map(s -> " "+ s +" ") + "s to put over" + B);
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
        Rule r = rule(A,B,cb.around(minC,elements));
        r.setDescription(A + " needs " + minC + " " + C + "s to put over" + B);
    }

    public void exactDirectNeighbour(String A, String B,int numC,String C){
        String[] elements = new String[1];
        elements[0] = C;
        Rule r = rule(A,B,cb.exactDirectNeighbours(numC,elements));
        r.setDescription(A + " needs exact " + numC + " " + C + "s to put over" + B);
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




    public Rule rule(String A, String B, Condition when){
        if(!E.containsElement(A) || !E.containsElement(B)){
            throw new IllegalArgumentException();
        }
        Element e1 = E.get(A);
        Element e2 = E.get(B);
        Rule rule =new Rule(e1,e2,when);
        ruleSet.add(rule);
        return rule;
    }



    public void ruleSet(String A,String Es,Condition when){
        String[] elements = Es.split(",\\s*");
        for (String s:elements){
            rule(A,s,when);
        }
    }







    public ArrayList<Rule> createRuleSet(){
        return ruleSet;
    }
}
