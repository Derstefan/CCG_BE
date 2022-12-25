package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;


import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.Arrays;

public class AroundCondition extends StatePosCondition{

    private Element[] aroundElements;

    private int number;

    public AroundCondition(int number,Element ... aroundElements) {
        this.aroundElements = aroundElements;
        this.number=number;
    }

    public AroundCondition(Element aroundElement) {
        this.aroundElements = new Element[1];
        aroundElements[0] = aroundElement;
        this.number =1;
    }

    public Element[] getAroundElements() {
        return aroundElements;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean check(CardGameState state) {
        Pos pos = getPos();
        int num=0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                Pos indexPos = pos.plus(new Pos(i,j));
                if(state.isOnMap(indexPos)){
                    Element indexElement = state.getElementAt(indexPos);
                   if(Arrays.stream(aroundElements).anyMatch(element -> element.equals(indexElement))) {
                       num++;
                   }
                }
            }
        }
        if(num>=number){
            return true;
        }
        return false;
    }
}
