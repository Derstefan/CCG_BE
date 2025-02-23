package com.ccg.ccgbe.cardgame.state.map;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

@Slf4j
public class Cell {

    private Element element;
    private Pos pos;

    private HashSet<String> possibleChanges = new HashSet<>();

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
        clearPossibleChanges();
        this.element = element;
    }

    public Pos getPos() {
        return pos;
    }

    public Cell clone(){
        return new Cell(element,pos);
    }

    public HashSet<String> getPossibleChanges() {
        return possibleChanges;
    }

    public void addPossibleChanges(String elementId) {
        if(elementId.equals(element.getId())){
            log.error("same elemets " + elementId.toString() + " and " +element.toString());
        }
        this.possibleChanges.add(elementId);
    }

    public void clearPossibleChanges(){
        this.possibleChanges.clear();
    }
}
