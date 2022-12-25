package com.ccg.ccgbe.cardgame.builder.core;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.AroundCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.ElementCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.EmptyElementCondition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;

import java.util.ArrayList;
import java.util.Arrays;

public class ConditionBuilder {

    private ElementCollector E;

    public ConditionBuilder(ElementCollector e) {
        E = e;
    }


    public Condition over(String e){
        return new ElementCondition(E.get(e));
    }

    public Condition around(int min, String ... eStr){
        Element[] elements = new Element[eStr.length];

        for (int i = 0; i < eStr.length; i++) {
            elements[i]=E.get(eStr[i]);
        }

        return new AroundCondition(min,elements);
    }

    public EmptyElementCondition canPLaceAnywhere(){
        return new EmptyElementCondition();
    }
}
