package com.ccg.ccgbe.cardgame.rules.condition.statePosConition;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public enum EComparator {
    min((i,c)-> i<=c),
    max((i,c)-> i>=c),
    exact((i,c)-> i==c);

    private BiFunction<Integer,Integer,Boolean> comparator;

    EComparator(BiFunction<Integer,Integer,Boolean> comparator){
        this.comparator=comparator;
    }

    public boolean compare(int number,int comp){
        return comparator.apply(number,comp);
    }


}
