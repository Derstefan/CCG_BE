package com.ccg.ccgbe.cardgame.builder;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.*;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;

public class ConditionBuilder {

    private ElementCollector E;

    public ConditionBuilder(ElementCollector e) {
        E = e;
    }

    public Condition around(int min, String ... eStr){
        Element[] elements = new Element[eStr.length];
        for (int i = 0; i < eStr.length; i++) {
            elements[i]=E.get(eStr[i]);
        }
        return new AroundCondition(min,elements);
    }

    public Condition exactDirectNeighbours(int num, String ... eStr){
        Element[] elements = new Element[eStr.length];

        for (int i = 0; i < eStr.length; i++) {
            elements[i]=E.get(eStr[i]);
        }

        return new AroundDirectCondition(EComparator.exact,num,elements);
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

    public Condition min2(int n,String Es){
        return around(EComparator.min,2,n,Es);
    }

    public Condition max2(int n,String Es){
        return around(EComparator.max,2,n,Es);
    }

    public Condition exact2(int n,String Es){
        return around(EComparator.exact,2,n,Es);
    }

    public Condition around(EComparator comp,int radius,int n,String Es){
        String[] elementsStrings = Es.split(",\\s*");
        Element[] elements = new Element[elementsStrings.length];

        for (int i = 0; i < elementsStrings.length; i++) {
            elements[i]=E.get(elementsStrings[i]);
        }
        return new AroundCondition(comp,radius,n,elements);
    }

}
