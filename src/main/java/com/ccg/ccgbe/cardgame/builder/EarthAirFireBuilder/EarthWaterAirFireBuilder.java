package com.ccg.ccgbe.cardgame.builder.EarthAirFireBuilder;


import com.ccg.ccgbe.cardgame.builder.RulesBuilder;
import com.ccg.ccgbe.cardgame.builder.core.ConditionBuilder;
import com.ccg.ccgbe.cardgame.builder.core.RuleBuilder;
import com.ccg.ccgbe.cardgame.rules.Rules;
import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.stateCondition.StateCondition;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;

import java.util.ArrayList;
import java.util.HashSet;

public class EarthWaterAirFireBuilder extends RulesBuilder {

    private ElementCollector E;
    private ArrayList<Rule> ruleSet = new ArrayList<>();
    private ArrayList<Condition> winnerConds = new ArrayList<>();


    public EarthWaterAirFireBuilder(){
        init();
    }


    public Rules createRules(){
        return new Rules(E,ruleSet,winnerConds);
    }

    private void init(){
        initElements();
        initRuleSet();
        initWinnerConditions();
    }


    private void initElements(){
        ElementCollector E = new ElementCollector();
        E.addTypes("E","W","A","F","T");
        E.finish();
        this.E = E;
    }

    private void initRuleSet(){

        /*RuleBuilder rb = new RuleBuilder(E);
        ConditionBuilder cb = new ConditionBuilder(E);
        rb.create("E"));
        rb.create("W"));
        rb.create("A"));

        rb.create("F","T"));
        rb.create("E","W"));
        rb.create("A","W"));
        rb.create("W","F"));
        rb.create("A","F"));

        rb.create("T", cb.over("E").AND(cb.around("W",1))));*/

    }


    private void initWinnerConditions(){
        //Wincondition for player1
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);

        //Wincondition for player2
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);


        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);
    }

}
