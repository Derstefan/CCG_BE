package com.ccg.ccgbe.cardgame.rules.condition.statePosConition.fight;

import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class AttackValue extends StateValue {

    private String fractionTag;

    public AttackValue(String fractionTag) {
        this.fractionTag = fractionTag;
    }

  public int getActualValue(CardGameState state, Pos pos){
      return 123;//TODO:state.getValue(pos, fractionTag);
  }

}
