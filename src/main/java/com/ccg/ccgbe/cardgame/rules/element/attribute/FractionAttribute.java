package com.ccg.ccgbe.cardgame.rules.element.attribute;

public class FractionAttribute extends Attribute{

    private String fractionName;

    public FractionAttribute(String fractionName) {
        super("fraction");
        this.fractionName = fractionName;
    }

    public String getFractionName() {
        return fractionName;
    }
}
