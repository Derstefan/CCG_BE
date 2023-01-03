package com.ccg.ccgbe.cardgame.rules.condition.operations;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class OrCondition extends Condition {

    private Condition cond1;
    private Condition cond2;

    public OrCondition(Condition cond1, Condition cond2){
        this.cond1 = cond1;
        this.cond2 = cond2;
    }

    public Condition getCond1() {
        return cond1;
    }

    public Condition getCond2() {
        return cond2;
    }

    @Override
    public boolean check(CardGameState state) {
        return cond1.check(state) || cond2.check(state);
    }

    @Override
    public void setPos(Pos pos){
        cond1.setPos(pos);
        cond2.setPos(pos);
    }

    @Override
    public String toString(){
        return "("+cond1.toString() + " OR " + cond2.toString()+")";
    }
}
