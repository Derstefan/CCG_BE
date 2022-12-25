package com.ccg.ccgbe.cardgame.state.map;

import com.ccg.ccgbe.cardgame.rules.element.Element;

public class Cell {

    private Element element;

    private Pos pos;

    public Cell(Element element,Pos pos) {
        this.pos = pos;
        this.element = element;
    }

    public Cell(Pos pos) {
        this.pos=pos;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Pos getPos() {
        return pos;
    }

    public Cell clone(){
        return new Cell(element,pos);
    }
}
