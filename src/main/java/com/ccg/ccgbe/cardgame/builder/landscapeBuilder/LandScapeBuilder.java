package com.ccg.ccgbe.cardgame.builder.landscapeBuilder;

import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.builder.MapBuilder;
import com.ccg.ccgbe.cardgame.builder.RulesBuilder;
import com.ccg.ccgbe.cardgame.builder.landscapeBuilder.functions.LandscapeFunctions;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.stateCondition.StateCondition;
import com.ccg.ccgbe.cardgame.rules.element.EType;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.functions.Functions;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.map.Map;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class LandScapeBuilder extends RulesBuilder {

    private ArrayList<Rule> manRules = new ArrayList<>();
    private ArrayList<Rule> autRules = new ArrayList<>();
    private ArrayList<Condition> winnerConds = new ArrayList<>();


    public LandScapeBuilder(){
        super();
        init();
    }


    public RuleLibrary createRules(){
        return new RuleLibrary(getE(), manRules, autRules,winnerConds);
    }

    private void init(){
        initElements();
        addElementFunctions();
        initManuellRuleSet();
        initAutomaticRuleSet();


        initWinnerConditions();
        areaRules();
        //TODO: ruleSet.checkReachability();
    }


    private void initElements(){
        ElementCollector E = new ElementCollector();

        E.add(new EType("D","#fbc42d","desert",true));
        E.add(new EType("S","#d6cb6e","savanna",true,"Orks"));
        E.add(new EType("G","#9acd32","grassland",true,"Human"));
        E.add(new EType("s","#f3ffef","snow",true));
        E.add(new EType("L","#0000e6","lake",true));
        E.add(new EType("M","#778577","mountain",false));
        E.add(new EType("H","#AAA5B7","hills",false));
        E.add(new EType("R","#4040B6","river",false));
        E.add(new EType("F","#5aBd27","forest",false,"elf"));
        E.add(new EType("J","#3a9d37","jungle",false));
        E.add(new EType("o","#8aad27","oase",false));

        E.add(new EType("O","#8a3d27","Orkloch",false,"Orks"));
        E.add(new EType("B","#8a5d37","Barrack",false,"Orks"));

        E.add(new EType("E","#3aAd27","Lebensbaum",false,"elf"));
        E.add(new EType("P","#4a8d37","Bogenbaum",false,"elf"));

        E.add(new EType("V","#Aa5d77","Village",false,"Human"));
        E.add(new EType("A","#BaBd37","Acker",false,"Human"));
        E.add(new EType("K","#8a5d77","Kaserne",false,"Human"));

        setE(E);
    }

    private void initManuellRuleSet(){

        // Orks Aufbau
        manRules.addAll(rule("O","S").when(min(1,"M").AND(min(2,"S")).OR(min2(1,"B").AND(max(1,"O")))).create());
        manRules.addAll(rule("S","G,D,J,F,s").when(min(1,"O")).create());
        manRules.addAll(rule("B","O").when(min(2,"O")).create());
        // Angriff
        manRules.addAll(rule("S","V,A,K").when(moreThan("B","K",4)).create());
        //area expansion
        getElement("O").addOnPlacementFunction(Functions.putElementsAround("G,D,s","S"));



        // Grünland-Menschen Aufbau
        manRules.addAll(rule("V","G").when(min2(2,"F").AND(min2(1,"M")).OR(min(2,"A"))).create());
        manRules.addAll(rule("G","F,J,S,D,s").when(min(1,"V")).create());
        manRules.addAll(rule("A","G,s,S,D,H").when(min2(1,"V")).create());
        manRules.addAll(rule("K","G").when(min2(2,"V").AND(min(2,"A"))).create());
        // Angriff
        manRules.addAll(rule("G","O,B").when(moreThan("K",4,"B",2)).create());
        //Area expansion
        getElement("V").addOnPlacementFunction(Functions.putElementsAround("S,D,s","G"));


        // elf aufbau
        manRules.addAll(rule("E","F").when(min(4,"F")).create());
        manRules.addAll(rule("P","E").when(min2(18,"F")).create());
        manRules.addAll(rule("F","E,G").when(min(1,"F")).create());

        getElement("P").addOnPlacementFunction(Functions.putElementsAround("E","F",2));
        getElement("E").addOnPlacementFunction(Functions.putElementsAround("G,S,D,s","F"));

    }








    private void addElementFunctions(){
        LandscapeFunctions f = new LandscapeFunctions(getE());

        getElement("R").getFunc().add(f.RIVER_FLOW_FUNCTION(getElement("R"),getElements("G,S,H,D,s"),getElements("L")));

    }





    private void initAutomaticRuleSet(){


        ArrayList<Rule> autRules = new ArrayList<>();

/*
        autRules.addAll(rule("S","D").when(min(3,"s").OR(min(5,"G"))).create());

        autRules.addAll(border("G",5,5,"s"));
        autRules.addAll(border("S",5,5,"G"));
        autRules.addAll(border("S",5,5,"s"));
        autRules.addAll(border("D",5,5,"S"));
        autRules.addAll(border("D",5,5,"G"));
        autRules.addAll(border("D",5,5,"s"));
        autRules.addAll(border("L",5,5,"D"));
        autRules.addAll(border("L",5,5,"S"));
        autRules.addAll(border("L",5,5,"G"));
        autRules.addAll(border("L",5,5,"s"));


        autRules.addAll(rule("G","s").when(min(3,"D,S")).create());
        autRules.addAll(rule("S","D").when(min(3,"G,s").OR(min(2,"L"))).create());
*/
        this.autRules = autRules;
    }




    //MAPGENERATION RULES-----------------------------------------------------
    private ArrayList<Rule> areaRules(){

        ArrayList<Rule> ruleSet = new ArrayList<>();

        ruleSet.addAll(border("G",4,4,"s"));
        ruleSet.addAll(border("S",4,4,"G"));
        ruleSet.addAll(border("S",4,4,"s"));
        ruleSet.addAll(border("D",4,4,"S"));
        ruleSet.addAll(border("D",4,4,"G"));
        ruleSet.addAll(border("D",4,4,"s"));
        ruleSet.addAll(border("L",4,4,"D"));
        ruleSet.addAll(border("L",4,4,"S"));
        ruleSet.addAll(border("L",4,4,"G"));
        ruleSet.addAll(border("L",4,4,"s"));

        return ruleSet;
    }

    private ArrayList<Rule> mountainRuleSet(){

        ArrayList<Rule> ruleSet = new ArrayList<>();

        ruleSet.addAll(rule("M","s").when(min(3,"D").AND(min(3,"s"))).create());
        ruleSet.addAll(rule("M","D").when(min(4,"D").AND(min(3,"G"))).create());
        ruleSet.addAll(rule("M","D").when(min(4,"L").AND(min(3,"D"))).create());
        ruleSet.addAll(rule("M","S").when(min(3,"s").AND(min(3,"S"))).create());
        ruleSet.addAll(rule("M","H").when(min(4,"H").AND(min(2,"M"))).create());
        ruleSet.addAll(rule("H","s,D").when(min(2,"M")).create());
        ruleSet.addAll(rule("H","G,S,D,s").when(min(3,"H").AND(min(1,"M"))).create());
        ruleSet.addAll(rule("H","G,S,D,s").when(min2(1,"H").AND(min3(1,"M")).AND(wsk(0.05))).create());


       // manRules.addAll(ruleSet);
        return ruleSet;
    }

    private ArrayList<Rule> floraRules(){

        ArrayList<Rule> ruleSet = new ArrayList<>();


        ruleSet.addAll(rule("J","S").when(min2(15,"S").AND(min2(2,"L").OR(min(2,"J")))).create());
        ruleSet.addAll(rule("F","G").when(min2(18,"G")).create());

        //ruleSet.addAll(rule("F","G").when(min(1,"F").AND(min(1,"L"))).create());


       // manRules.addAll(ruleSet);
        return ruleSet;
    }

    private ArrayList<Rule> riverRules(){

        ArrayList<Rule> ruleSet = new ArrayList<>();
        ruleSet.addAll(rule("R","H").when(exactNeighbours(3,"M")).create());

        //manRules.addAll(ruleSet);
        return ruleSet;
    }




    private void initWinnerConditions(){
        //Wincondition for player1
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);

        //Wincondition for player2
        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);


        winnerConds.add(StateCondition.GAME_ENDED_CONDITION);
    }


    public Map generateMap(){
        Map map = generateMap(Config.DEFAULT_WIDTH,Config.DEFAULT_HEIGHT);
       // log.info(map.toString());
        return map;
    }
    public Map generateMap(int w, int h) {
        MapBuilder mb = new MapBuilder(getE());
        mb.generate(w,h);
        mb.iterateRules(areaRules(),8);
        mb.iterateRules(mountainRuleSet(),2);
        mb.iterateRules(floraRules(),3);
        //mb.iterateRules(riverRules(),30);

        return mb.create();
    }


}
