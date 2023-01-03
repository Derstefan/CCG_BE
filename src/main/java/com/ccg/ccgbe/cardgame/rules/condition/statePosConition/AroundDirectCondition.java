package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.Arrays;

public class AroundDirectCondition extends StatePosCondition{
    private Element[] aroundElements;

    private int number;

    private EComparator comp;

    public AroundDirectCondition(EComparator comp, int number, Element ... aroundElements) {
        this.aroundElements = aroundElements;
        this.number=number;
        this.comp=comp;
    }

    public AroundDirectCondition(Element aroundElement) {
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
        Pos[] ps = new Pos[]{new Pos(-1,0),new Pos(0,-1),new Pos(1,0),new Pos(0,1)};
        for(Pos p:ps){
            Pos indexPos = pos.plus(p);
               if(state.isOnMap(indexPos)){
                   Element indexElement = state.getElementAt(indexPos);
                   if(Arrays.stream(aroundElements).anyMatch(element -> element.equals(indexElement))) {
                       num++;
                   }
            }
        }

        if(comp.compare(number,num)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
