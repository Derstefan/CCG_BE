package com.ccg.ccgbe.library;

import com.ccg.ccgbe.cardgame.builder.RulesBuilder;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.stateCondition.StateCondition;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.functions.Functions;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;


@Slf4j
public class LibraryBuilder extends RulesBuilder {

    private ArrayList<Rule> manRules = new ArrayList<>();
    private ArrayList<Rule> autRules = new ArrayList<>();
    private ArrayList<Condition> winnerConds = new ArrayList<>();


    public LibraryBuilder(ElementCollector E){
        super();
        setE(E);
        init();
    }


    private void initManuellRuleSet(){

        // Orks Aufbau
        add(rule("O","S").when(min(1,"M").AND(min(2,"S")).OR(min2(1,"B").AND(max(1,"O")))).create());
        add(rule("S","G,D,J,F,s").when(min(1,"O")).create());
        add(rule("B","O").when(min(2,"O")).create());
        // Angriff
        add(rule("S","V,A,K").when(moreThan("B","K",4)).create());
        //area expansion
        getElement("O").addOnPlacementFunction(Functions.putElementsAround("G,D,s","S"));



        // Grünland-Menschen Aufbau
        add(rule("V","G").when(min2(2,"F").AND(min2(1,"M")).OR(min(2,"A"))).create());
        add(rule("G","F,J,S,D,s").when(min(1,"V")).create());
        add(rule("A","G,s,S,D,H").when(min2(1,"V")).create());
        add(rule("K","G").when(min2(2,"V").AND(min(2,"A"))).create());
        // Angriff
        add(rule("G","O,B").when(moreThan("K",4,"B",2)).create());
        //Area expansion
        getElement("V").addOnPlacementFunction(Functions.putElementsAround("S,D,s","G"));


        // elf aufbau
        add(rule("E","F").when(min(4,"F")).create());
        add(rule("P","E").when(min2(18,"F")).create());
        add(rule("F","E,G").when(min(1,"F")).create());

        getElement("P").addOnPlacementFunction(Functions.putElementsAround("E","F",2));
        getElement("E").addOnPlacementFunction(Functions.putElementsAround("G,S,D,s","F"));

    }


 /*   private void initAutomaticRuleSet(){

        ArrayList<Rule> autRules = new ArrayList<>();
        this.autRules = autRules;
    }*/




    private void initWinnerConditions(){
        //Wincondition for player1
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);

        //Wincondition for player2
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);


        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);
    }




    public RuleLibrary createLibrary(){
        return new RuleLibrary(getE(), manRules, autRules,winnerConds);
    }

    private void init(){
        initManuellRuleSet();
        //initAutomaticRuleSet();
        initWinnerConditions();
        //TODO: ruleSet.checkReachability();
    }


    private void add(ArrayList<Rule> rules){
        manRules.addAll(rules);
    }

    private void addAutomatic(ArrayList<Rule> rules){
        autRules.addAll(rules);
    }




}
