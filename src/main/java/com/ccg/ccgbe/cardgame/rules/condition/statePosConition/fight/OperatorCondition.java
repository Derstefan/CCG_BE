package com.ccg.ccgbe.cardgame.rules.condition.statePosConition.fight;

import com.ccg.ccgbe.cardgame.rules.condition.statePosConition.StatePosCondition;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

public class OperatorCondition extends StatePosCondition {

    private StateValue a;
    private StateValue b;
    private String operator;

    public OperatorCondition(StateValue a, String operator, StateValue b) {
        this.a = a;
        this.b = b;
        this.operator =operator;
    }

    @Override
    public boolean check(CardGameState state) {
        int valueA = a.getActualValue(state, getPos());
        int valueB = b.getActualValue(state, getPos());
        return check(valueA, operator, valueB);
    }




    private boolean check(int value1, String operator, int value2) {
        switch (operator) {
            case "==":
                return value1 == value2;
            case "!=":
                return value1 != value2;
            case ">":
                return value1 > value2;
            case "<":
                return value1 < value2;
            case ">=":
                return value1 >= value2;
            case "<=":
                return value1 <= value2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    @Override
    public String toString() {
        return "a >= b";
    }


}
