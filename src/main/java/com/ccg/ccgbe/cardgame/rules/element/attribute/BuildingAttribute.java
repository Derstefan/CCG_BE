package com.ccg.ccgbe.cardgame.rules.element.attribute;

public class BuildingAttribute extends Attribute{

    private int powerLevel;

    public BuildingAttribute(int powerLevel) {
        super("building");
        this.powerLevel = powerLevel;
    }

    public int getPowerLevel() {
        return powerLevel;
    }
}
