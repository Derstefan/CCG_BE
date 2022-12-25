package com.ccg.ccgbe.cardgame.rules.functions;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.function.Function;

public class Functions {

    public static final PerformEarchRoundFunction getGrowFunction(){



        return new PerformEarchRoundFunction("grow", new Function<StatePosParam, Void>() {
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
}
