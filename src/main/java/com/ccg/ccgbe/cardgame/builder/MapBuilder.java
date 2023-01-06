package com.ccg.ccgbe.cardgame.builder;

import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Cell;
import com.ccg.ccgbe.cardgame.state.map.Map;
import com.ccg.ccgbe.cardgame.state.map.Pos;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class MapBuilder {

    private ElementCollector E;

    private Map map;

    private long seed;

    public MapBuilder(ElementCollector e) {
        E = e;
    }


    public ElementCollector getE() {
        return E;
    }


    public Map generate(){
        map = generate(Config.DEFAULT_WIDTH,Config.DEFAULT_HEIGHT);
        return map;
    }

    /**
     *     fill map with random basic elements
     */

    public Map generate(int w, int h){
        Cell[][] mapData = new Cell[w][h];
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[0].length; j++) {
                mapData[i][j] = new Cell(E.getRandomBasic(),new Pos(i,j));

            }
        }
        map = new Map(mapData);
        return map;
    }

    public Map iterateRules(ArrayList<Rule> ruleSet, int iterations) {
        CardGameState state = new CardGameState(new RuleLibrary(E, new ArrayList<>(),ruleSet, new ArrayList<>()), map.getWidth(),map.getHeight());
        state.setMap(map);
        for (int index = 0; index < iterations; index++) {
            state.performEndRoundFunctions();
        }
        map = state.getMap();
        return map;
    }

    public Map create(){
        return map;
    }


}
