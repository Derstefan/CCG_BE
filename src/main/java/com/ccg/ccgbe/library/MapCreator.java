package com.ccg.ccgbe.library;

import com.ccg.ccgbe.cardgame.builder.MapBuilder;
import com.ccg.ccgbe.cardgame.builder.RulesBuilder;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.map.Map;

import java.util.ArrayList;

public class MapCreator extends RulesBuilder {


    public MapCreator(ElementCollector E) {
        setE(E);
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
