package com.ccg.ccgbe.cardgame.builder.shearRockPaper;



import com.ccg.ccgbe.cardgame.rules.Rules;
import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.stateCondition.MoreElementsCondition;
import com.ccg.ccgbe.cardgame.rules.condition.stateCondition.StateCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.ElementCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.EmptyElementCondition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;

import java.util.ArrayList;
import java.util.HashSet;

public class ShearRockPaperBuilder {


    public static ElementCollector shearRockStoneElements(){
        ElementCollector col = new ElementCollector();
        col.add("S");
        col.add("R");
        col.add(new Element("P")); // Elementdefinition possible
        return col;
    }

    public static final Rules getRules(){

        ElementCollector E = shearRockStoneElements();

        ArrayList<Rule> ruleSet = new ArrayList<>();
        ruleSet.add(canPut(E.get("S")));
        ruleSet.add(canPut(E.get("R")));
        ruleSet.add(canPut(E.get("P")));
        ruleSet.add(canPutOver(E.get("R"),E.get("S")));
        ruleSet.add(canPutOver(E.get("P"),E.get("R")));
        ruleSet.add(canPutOver(E.get("S"),E.get("P")));



        ArrayList<Condition> winnerConds = new ArrayList<>();
        //Wincondition for player1
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION.AND(new MoreElementsCondition(E.get("S"),E.get("R"))));

        //Wincondition for player2
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION.AND(new MoreElementsCondition(E.get("R"),E.get("S"))));

       return new Rules(E,ruleSet,winnerConds);
    }




    private static Rule canPutOver(Element newElement, Element oldElement){
        return new Rule(newElement,new ElementCondition(oldElement));
    }

    private static Rule canPut(Element newElement){
        return new Rule(newElement,new EmptyElementCondition());
    }
}
