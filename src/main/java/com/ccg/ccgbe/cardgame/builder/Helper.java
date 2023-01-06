package com.ccg.ccgbe.cardgame.builder;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;

public class Helper {



    public static Element[] getElementsFromString(ElementCollector E,String Es){
        String[] elementsStrings = Es.split(",\\s*");
        Element[] elements = new Element[elementsStrings.length];

        for (int i = 0; i < elementsStrings.length; i++) {
            elements[i]=E.get(elementsStrings[i]);
        }
        return elements;
    }
}
