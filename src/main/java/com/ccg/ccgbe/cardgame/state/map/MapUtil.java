package com.ccg.ccgbe.cardgame.state.map;


import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;

public class MapUtil {

    public static final int getNumberOfElements(CardGameState state, Element e){
        Cell[][] map = state.getMapData();
        int count =0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){

                if(e.equals(map[i][j].getElement())){
                    count++;
                }
            }
        }
        return count;
    }
}
