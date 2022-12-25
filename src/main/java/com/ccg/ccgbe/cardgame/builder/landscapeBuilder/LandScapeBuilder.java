package com.ccg.ccgbe.cardgame.builder.landscapeBuilder;

import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.builder.RulesBuilder;
import com.ccg.ccgbe.cardgame.builder.core.ConditionBuilder;
import com.ccg.ccgbe.cardgame.builder.core.MapBuilder;
import com.ccg.ccgbe.cardgame.builder.core.RuleBuilder;
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

    private ElementCollector E;
    private ArrayList<Rule> manuellEuleSet = new ArrayList<>();
    private ArrayList<Rule> automaticEuleSet = new ArrayList<>();
    private ArrayList<Condition> winnerConds = new ArrayList<>();


    public LandScapeBuilder(){
        init();
    }


    public Rules createRules(){
        return new Rules(E,manuellEuleSet,automaticEuleSet,winnerConds);
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


        E.finish();
        this.E = E;
    }

    private void initManuellRuleSet(){

        RuleBuilder rb = new RuleBuilder(E);
        ConditionBuilder cb = new ConditionBuilder(E);

        rb.border("G",3,3,"s");
        rb.border("S",3,3,"G");
        rb.border("D",3,3,"S");
        rb.border("L",4,4,"D");
        rb.border("L",4,4,"S");
        rb.border("L",4,4,"G");
        rb.border("L",4,4,"s");


        rb.expand("G","s",3,"D");
        rb.expand("G","s",4,"S");
        rb.expand("S","G",3,"D");
        rb.expand("G","S",4,"s");
        rb.expand("S","D",3,"s");


        manuellEuleSet = rb.createRuleSet();
    }

    private void initAutomaticRuleSet(){

        RuleBuilder rb = new RuleBuilder(E);


/*        rb.expand("G","s",3,"D");
        rb.expand("G","s",5,"S");

        rb.expand("S","D",3,"s");
        rb.expand("S","D",5,"G");*/

        rb.border("G",5,5,"s");
        rb.border("S",5,5,"G");
        rb.border("S",5,5,"s");
        rb.border("D",5,5,"S");
        rb.border("D",5,5,"G");
        rb.border("D",5,5,"s");
        rb.border("L",5,5,"D");
        rb.border("L",5,5,"S");
        rb.border("L",5,5,"G");
        rb.border("L",5,5,"s");


        rb.expand("G","s",3,"D","S");
        rb.expand("S","D",3,"G","s");
        //rb.expand("G","D",2,"L");

        automaticEuleSet = rb.createRuleSet();
    }


    private ArrayList<Rule> areaRules(){

        RuleBuilder rb = new RuleBuilder(E);

        rb.border("G",4,4,"s");
        rb.border("S",4,4,"G");
        rb.border("S",4,4,"s");
        rb.border("D",4,4,"S");
        rb.border("D",4,4,"G");
        rb.border("D",4,4,"s");
        rb.border("L",4,4,"D");
        rb.border("L",4,4,"S");
        rb.border("L",4,4,"G");
        rb.border("L",4,4,"s");


/*        rb.expand("G","s",3,"D","S");
        rb.expand("S","D",3,"G","s");*/
        //rb.expand("G","D",2,"L");

        return rb.createRuleSet();
    }

    private ArrayList<Rule> mountainRules(){

        RuleBuilder rb = new RuleBuilder(E);
        ConditionBuilder cb = new ConditionBuilder(E);

        rb.rule("M","s",cb.around(3,"D").AND(cb.around(3,"s")));
        rb.rule("M","D",cb.around(4,"D").AND(cb.around(3,"G")));
        rb.rule("M","D",cb.around(4,"L").AND(cb.around(2,"D")));
        rb.rule("M","S",cb.around(3,"s").AND(cb.around(3,"S")));

/*        rb.expand("G","s",3,"D","S");
        rb.expand("S","D",3,"G","s");*/
        //rb.expand("G","D",2,"L");

        return rb.createRuleSet();
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
        MapBuilder mb = new MapBuilder(E);
        mb.generate(w,h);
        mb.iterateRules(areaRules(),4);
        mb.iterateRules(mountainRules(),2);

        return mb.create();
    }


}
