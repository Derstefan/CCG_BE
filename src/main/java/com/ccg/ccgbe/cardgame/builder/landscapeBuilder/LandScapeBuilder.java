package com.ccg.ccgbe.cardgame.builder.landscapeBuilder;

import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.builder.core.*;
import com.ccg.ccgbe.cardgame.rules.Rules;
import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.stateCondition.StateCondition;
import com.ccg.ccgbe.cardgame.rules.element.EType;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
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


    public Rules createRules(){
        return new Rules(getE(), manRules, autRules,winnerConds);
    }

    private void init(){
        initElements();
        initManuellRuleSet();
        initAutomaticRuleSet();


        initWinnerConditions();
        areaRules();

        //TODO: ruleSet.checkReachability();
    }


    private void initElements(){
        ElementCollector E = new ElementCollector();


        E.add(new EType("D","#fbc42d","desert",true));
        E.add(new EType("S","#d6cb6e","savanna",true));
        E.add(new EType("G","#9acd32","grassland",true));
        E.add(new EType("s","#f3ffef","snow",true));
        E.add(new EType("L","#0000e6","lake",true));
        E.add(new EType("M","#778577","mountain",false));
        E.add(new EType("H","#AAA5B7","hills",false));
        E.add(new EType("R","#4040B6","river",false));
        E.add(new EType("F","#5aBd27","forest",false));
        E.add(new EType("J","#3a9d37","jungle",false));
        E.add(new EType("O","#8aad27","oase",false));


        E.finish();
        setE(E);
    }

    private void initManuellRuleSet(){

        ArrayList<Rule> manRules = new ArrayList<>();

        manRules.addAll(rule("G","L").when(min(4,"G")).create());
        manRules.addAll(rule("G","s").when(min(3,"D").OR(min(4,"S")).OR(min(3,"G"))).create());
        manRules.addAll(rule("G","S").when(min(4,"s").OR(min(3,"G"))).create());

        manRules.addAll(rule("S","G").when(min(3,"D").OR(min(3,"S"))).create());
        manRules.addAll(rule("S","D").when(min(3,"s").OR(min(3,"S"))).create());
        manRules.addAll(rule("S","L").when(min(4,"S")).create());

        manRules.addAll(rule("D","S").when(min(3,"D")).create());
        manRules.addAll(rule("D","L").when(min(4,"D")).create());

        manRules.addAll(rule("s","G").when(min(3,"s")).create());
        manRules.addAll(rule("s","L").when(min(4,"D")).create());

        manRules.addAll(rule("L","D,S,G,s").when(min(4,"L")).create());

        manRules.addAll(rule("O","D").when(min2(24,"D")).create());





        this.manRules = manRules;
    }

    private void initAutomaticRuleSet(){


        ArrayList<Rule> autRules = new ArrayList<>();


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
        autRules.addAll(rule("R","H,S,G,D,F,J,s").when(exactNeighbours(1,"R").AND(max(1,"R")).AND(max2(3,"R")).AND(wsk(0.3))).create());
        autRules.addAll(rule("R","H,S,G,D,F,J,s").when(exactNeighbours(1,"R").AND(exactNeighbours(1,"L"))).create());

        this.autRules = autRules;
    }


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




        manRules.addAll(ruleSet);
        return ruleSet;
    }

    private ArrayList<Rule> floraRules(){

        ArrayList<Rule> ruleSet = new ArrayList<>();


        ruleSet.addAll(rule("J","S").when(min2(13,"S")).create());
        ruleSet.addAll(rule("F","G").when(min2(13,"G")).create());

        //ruleSet.addAll(rule("F","G").when(min(1,"F").AND(min(1,"L"))).create());


        manRules.addAll(ruleSet);
        return ruleSet;
    }

    private ArrayList<Rule> riverRules(){

        ArrayList<Rule> ruleSet = new ArrayList<>();
        ruleSet.addAll(rule("R","H").when(exactNeighbours(3,"M")).create());

        manRules.addAll(ruleSet);
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
        mb.iterateRules(mountainRuleSet(),3);
        mb.iterateRules(floraRules(),3);
        mb.iterateRules(riverRules(),1);

        return mb.create();
    }


}
