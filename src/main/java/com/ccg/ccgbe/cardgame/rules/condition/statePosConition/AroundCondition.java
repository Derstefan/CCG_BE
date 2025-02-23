package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;


import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AroundCondition extends StatePosCondition{

    private Element[] aroundElements;

    private int number;

    private int radius;

    private EComparator comp;

    public AroundCondition(EComparator comp,int radius,int number,Element ... aroundElements) {
        this.aroundElements = aroundElements;
        this.comp = comp;
        this.number=number;
        this.radius = radius;
    }

    public AroundCondition(EComparator comp,int number,Element ... aroundElements) {
        this.aroundElements = aroundElements;
        this.comp = comp;
        this.number=number;
        this.radius = 1;
    }

    public AroundCondition(int min,int radius,Element ... aroundElements) {
        this.aroundElements = aroundElements;
        this.comp = EComparator.min;
        this.number=min;
        this.radius = radius;
    }

    public AroundCondition(int min,Element ... aroundElements) {
        this.aroundElements = aroundElements;
        this.comp = EComparator.min;
        this.number=min;
        this.radius = 1;
    }

    public AroundCondition(Element ... aroundElement) {
        this.aroundElements = aroundElements;
        this.comp = EComparator.min;
        this.number =1;
        this.radius = 1;
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
        for(int i=-1*radius;i<=1*radius;i++){
            for(int j=-1*radius;j<=1*radius;j++){
                if(i==j && i==0)continue;
                Pos indexPos = pos.plus(new Pos(i,j));
                if(state.isOnMap(indexPos)){
                    Element indexElement = state.getElementAt(indexPos);
                   if(Arrays.stream(aroundElements).anyMatch(element -> element.equals(indexElement))) {
                       num++;
                   }
                }
            }
        }
        if(comp.compare(number,num)){
            return true;
        }
        return false;
    }



    @Override
    public String toString(){
        String str = "( around ";
        StringBuilder sb = new StringBuilder("");
        Arrays.stream(aroundElements).forEach(s->sb.append(","+s));
        if(radius>1)str+= "in radius of " + radius +" ";
        str+= " " + comp + " ";
        str+= number + " ";
        str += sb.substring(1) +")";
        return  str;
    }
}
