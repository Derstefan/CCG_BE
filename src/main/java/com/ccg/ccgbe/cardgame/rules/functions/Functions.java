package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.builder.Helper;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.Arrays;
import java.util.function.Function;

public class Functions {

    public static final OnEndroundFunction getGrowFunction(){



        return new OnEndroundFunction("grow", new Function<StatePosParam, Void>() {
            @Override
            public Void apply(StatePosParam statePosParam) {
                CardGameState state = statePosParam.getState();
                Pos pos = statePosParam.getPos();
                Element e = state.getElementAt(pos);

                for(int i=-1;i<=1;i++){
                    for(int j=-1;j<=1;j++){
                        Pos indexPos = pos.plus(new Pos(i,j));
                        if(state.isOnMap(indexPos)){


                            //TODO: bedingung sollte schärfer sein
                            state.placeElementAndUpdate(e,indexPos);
                        }

                    }
                }

                return null;
            }
        });
    }

    //like battle or burning forest
    public static final OnEndroundFunction after1RoundChangeTo(String newElementString){
        return new OnEndroundFunction("after1RoundChangeTo" + newElementString, new Function<StatePosParam, Void>() {
            @Override
            public Void apply(StatePosParam statePosParam) {
                CardGameState state = statePosParam.getState();
                Element newElement = state.getRules().getE().get(newElementString);
                Pos pos = statePosParam.getPos();
                if(state.isOnMap(pos)){
                    state.placeElementWithoutRuleAndUpdate(newElement,pos);
                }
                return null;
            }
        });
    }

    //like battle or burning forest TODO: to ElementFunctions
    public static final OnEventFunction putElementsAround(String Es, String to, int radius){
        return new OnEventFunction("morphAroundElements " + Es + " to "+ to, new Function<StateElementPosParam, Void>() {
            @Override
            public Void apply(StateElementPosParam stateElementPosParam) {
                CardGameState state = stateElementPosParam.getState();
                Element newElement = state.getRules().getE().get(to);
                Element[] aroundElements = Helper.getElementsFromString(state.getRules().getE(), Es);
                Pos pos = stateElementPosParam.getPos();
                for(int i=-1*radius;i<=1*radius;i++){
                    for(int j=-1*radius;j<=1*radius;j++){
                        Pos indexPos = pos.plus(new Pos(i,j));
                        if(state.isOnMap(indexPos)){
                            Element indexElement = state.getElementAt(indexPos);
                            if(Arrays.stream(aroundElements).anyMatch(element -> element.equals(indexElement))) {
                                state.placeElementWithoutRuleAndUpdate(newElement,indexPos);
                            }
                        }
                    }
                }
                return null;
            }
        });
    }

    public static final OnEventFunction putElementsAround(String Es, String to){
        return putElementsAround(Es,to,1);
    }


    //like battle or burning forest TODO: to ElementFunctions
    public static final OnEventFunction putRandomElementsAround(String Es, String to, int radius, int number){
        return new OnEventFunction("put "+ number+" RandomElements of " + Es + " to "+ to + " ", new Function<StateElementPosParam, Void>() {
            @Override
            public Void apply(StateElementPosParam stateElementPosParam) {
                CardGameState state = stateElementPosParam.getState();
                Element newElement = state.getRules().getE().get(to);
                Element[] aroundElements = Helper.getElementsFromString(state.getRules().getE(), Es);
                Pos pos = stateElementPosParam.getPos();
                for(int i=-1*radius;i<=1*radius;i++){
                    for(int j=-1*radius;j<=1*radius;j++){
                        Pos indexPos = pos.plus(new Pos(i,j));
                        if(state.isOnMap(indexPos)){
                            Element indexElement = state.getElementAt(indexPos);
                            if(Arrays.stream(aroundElements).anyMatch(element -> element.equals(indexElement))) {
                                state.placeElementWithoutRuleAndUpdate(newElement,indexPos);
                            }
                        }
                    }
                }
                return null;
            }
        });
    }
}
