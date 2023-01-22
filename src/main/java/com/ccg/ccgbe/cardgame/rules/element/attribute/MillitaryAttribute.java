package com.ccg.ccgbe.cardgame.rules.element.attribute;

public class MillitaryAttribute extends Attribute {

    private int auraPowerLevel;
    private int range;

    public MillitaryAttribute(int auraPowerLevel, int range) {
        super("military");
        this.auraPowerLevel = auraPowerLevel;
        this.range = range;
    }

    public int getAuraPowerLevel() {
        return auraPowerLevel;
    }

    public int getRange() {
        return range;
    }
}
