package com.ccg.ccgbe.dto;

import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;

import java.util.ArrayList;

public class ElementPortFolioDTO {

    private Element element;

    private ArrayList<Element> canPutOverElements = new ArrayList<>();

    private ArrayList<Rule> rules = new ArrayList<>();

    public ElementPortFolioDTO(RuleLibrary library, Element element) {
        this.element = element;
        library.getRuleSet().forEach((rule -> {
            if(!canPutOverElements.contains(rule.getBeforeElement())){
                if(rule.getElement().equals(element)){
                    canPutOverElements.add(rule.getBeforeElement());
                    rules.add(rule);
                }
            }
        }));
    }

    public Element getElement() {
        return element;
    }

    public ArrayList<Element> getCanPutOverElements() {
        return canPutOverElements;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
}
