package com.ccg.ccgbe.cardgame.events;

import com.ccg.ccgbe.cardgame.draw.Draw;

public class DrawEvent extends Event {
    private Draw draw;

    public DrawEvent(Draw draw) {
        super();
        this.draw = draw;
    }

    public Draw getDraw() {
        return draw;
    }

    @Override
    public String toString() {
        return draw.toString() + " at " + getDate().toString();
    }
}
