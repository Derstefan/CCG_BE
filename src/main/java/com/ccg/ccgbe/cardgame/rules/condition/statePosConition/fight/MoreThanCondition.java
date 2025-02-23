package com.ccg.ccgbe.cardgame.rules.condition.statePosConition.fight;

import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.StatePosCondition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.Arrays;

public class MoreThanCondition extends StatePosCondition {

    private Element[] As;

    private Element[] Bs;

    private int radiusA = 1;
    private int radiusB = 1;

    public MoreThanCondition(Element[] As,Element[] Bs) {
        this.As = As;
        this.Bs = Bs;
    }

    public MoreThanCondition(Element[] As,Element[] Bs, int radius) {
        this.As = As;
        this.Bs = Bs;
        this.radiusA = radius;
        this.radiusB =radius;
    }

    public MoreThanCondition(Element[] As,int radiusA,Element[] Bs, int radiusB) {
        this.As = As;
        this.Bs = Bs;
        this.radiusA = radiusA;
        this.radiusB =radiusB;
    }

    @Override
    public boolean check(CardGameState state) {
        Pos pos = getPos();
        int numA=0;
        int numB =0;
        for(int i=-1*radiusA;i<=1*radiusA;i++) {
            for (int j = -1 * radiusA; j <= 1 * radiusA; j++) {
                Pos indexPos = pos.plus(new Pos(i, j));
                if (state.isOnMap(indexPos)) {
                    Element indexElement = state.getElementAt(indexPos);
                    if (Arrays.stream(As).anyMatch(A -> A.equals(indexElement))) {
                        numA++;
                    }
                }
            }
        }
        for(int i=-1*radiusB;i<=1*radiusB;i++){
            for(int j=-1*radiusB;j<=1*radiusB;j++){
                Pos indexPos = pos.plus(new Pos(i,j));
                if(state.isOnMap(indexPos)){
                    Element indexElement = state.getElementAt(indexPos);
                    if(Arrays.stream(Bs).anyMatch(A -> A.equals(indexElement))){
                        numB++;
                    }

                }
            }
        }
        return numA>numB;

    }

    @Override
    public String toString() {
        String arStr = "";
        String brStr = "";
        if(radiusA>1)arStr+= " in radius " + radiusA;
        if(radiusB>1)brStr+= " in radius " + radiusB;
        StringBuilder Asb = new StringBuilder("");
        StringBuilder Bsb = new StringBuilder("");
        Arrays.stream(As).forEach(s->Asb.append(","+s));
        Arrays.stream(Bs).forEach(s->Bsb.append(","+s));
        return " around more of "+ Asb.substring(1) + arStr  + " than " + Bsb.substring(1) + brStr;
    }

}
