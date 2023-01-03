package com.ccg.ccgbe.cardgame.rules.condition;


import com.ccg.ccgbe.cardgame.rules.condition.operations.AndCondition;
import com.ccg.ccgbe.cardgame.rules.condition.operations.NotCondition;
import com.ccg.ccgbe.cardgame.rules.condition.operations.OrCondition;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public abstract class Condition {

    public abstract boolean check(CardGameState state);

    public AndCondition AND(Condition cond2){
        return new AndCondition(this,cond2);
    }

    public OrCondition OR(Condition cond2){
        return new OrCondition(this,cond2);
    }

    public NotCondition NOT(){
        return new NotCondition(this);
    }

    public void setPos(Pos pos){
        //do nothing by default
    }

    public Pos getPos(){
        //do nothing by default
        return null;
    }


    public abstract String toString();

}
