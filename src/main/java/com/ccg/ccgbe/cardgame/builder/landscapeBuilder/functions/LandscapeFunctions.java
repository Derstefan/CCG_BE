package com.ccg.ccgbe.cardgame.builder.landscapeBuilder.functions;

import com.ccg.ccgbe.cardgame.builder.Helper;
import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.condition.operations.AndCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.AroundCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.AroundDirectCondition;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.EComparator;
import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.ElementAtCondition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.functions.PerformEarchRoundFunction;
import com.ccg.ccgbe.cardgame.rules.functions.StatePosParam;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class LandscapeFunctions {

    private ElementCollector E;

    public LandscapeFunctions(ElementCollector e) {
        E = e;
    }

    public PerformEarchRoundFunction RIVER_FLOW_FUNCTION(Element flowElement, Element[] flowOverElements,Element[] stopElements) {
        return new PerformEarchRoundFunction("Riverflow", new Function<>() {
            @Override
            public Void apply(StatePosParam statePosParam) {
                CardGameState state = statePosParam.getState();
                Pos pos = statePosParam.getPos();

                ArrayList<Pos> possibleFlows = new ArrayList<>();

                AroundDirectCondition aroundDirectIsMaxOneRiver = new AroundDirectCondition(EComparator.max,1,flowElement);
                AroundCondition aroundMax2River = new AroundCondition(EComparator.max,2,flowElement);
                AroundCondition aroundLake = new AroundCondition(EComparator.min,1,stopElements);
                aroundDirectIsMaxOneRiver.setPos(pos);
                aroundMax2River.setPos(pos);
                aroundLake.setPos(pos);
                if(!aroundDirectIsMaxOneRiver.check(state) || !aroundMax2River.check(state) || aroundLake.check(state)){
                    return null;
                }

                Condition c1,c2,c3,c;
                if(state.isOnMap(pos.left())) {
                    c1 = new ElementAtCondition(Pos.LEFT, flowOverElements);
                    c2 = new ElementAtCondition(Pos.TOP_LEFT, flowElement).NOT();
                    c3 = new ElementAtCondition(Pos.BOTTOM_LEFT, flowElement).NOT();
                    c = c1.AND(c2).AND(c3);
                    c.setPos(pos);
                    if (c.check(state)) {
                        possibleFlows.add(Pos.LEFT);
                    }
                }

                if(state.isOnMap(pos.top())){
                    c1 = new ElementAtCondition(Pos.TOP,flowOverElements);
                    c2 = new ElementAtCondition(Pos.TOP_LEFT,flowElement).NOT();
                    c3 = new ElementAtCondition(Pos.TOP_RIGHT,flowElement).NOT();
                    c = c1.AND(c2).AND(c3);
                    c.setPos(pos);
                    if(c.check(state)){
                        possibleFlows.add(Pos.TOP);
                    }
                }

                if(state.isOnMap(pos.right())) {
                    c1 = new ElementAtCondition(Pos.RIGHT, flowOverElements);
                    c2 = new ElementAtCondition(Pos.TOP_RIGHT, flowElement).NOT();
                    c3 = new ElementAtCondition(Pos.BOTTOM_RIGHT, flowElement).NOT();
                    c = c1.AND(c2).AND(c3);
                    c.setPos(pos);
                    if (c.check(state)) {
                        possibleFlows.add(Pos.RIGHT);
                    }
                }

                if(state.isOnMap(pos.bottom())) {
                    c1 = new ElementAtCondition(Pos.BOTTOM, flowOverElements);
                    c2 = new ElementAtCondition(Pos.BOTTOM_LEFT, flowElement).NOT();
                    c3 = new ElementAtCondition(Pos.BOTTOM_RIGHT, flowElement).NOT();
                    c = c1.AND(c2).AND(c3);
                    c.setPos(pos);
                    if (c.check(state) && state.isOnMap(pos.bottom())) {
                        possibleFlows.add(Pos.BOTTOM);
                    }
                }

                if(possibleFlows.isEmpty())return null;

                Random r = new Random();



                Pos randPos = possibleFlows.get(r.nextInt(possibleFlows.size()));
                state.placeElementWithoutRuleAndUpdate(flowElement,pos.plus(randPos));
                //System.out.println("landscapefunction: new River at "+pos.plus(randPos) );
                return null; //TODO: Void vs void?


            }
        });

    }
}
