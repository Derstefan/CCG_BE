package com.ccg.ccgbe.cardgame.rules.condition.operations;

import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.state.CardGameState;

import java.util.Random;

public class PropabilityCondition extends Condition {

    private Random rand;

    /**
     * in percent
     */
    private double probability;


    public PropabilityCondition(double probability) {
        this.rand = new Random();
        this.probability = probability;
    }

    public PropabilityCondition(Random rand, double probability) {
        this.rand = rand;
        this.probability = probability;
    }

    @Override
    public boolean check(CardGameState state) {
        return rand.nextDouble()<probability;
    }

    @Override
    public String toString() {
        return null;
    }
}
