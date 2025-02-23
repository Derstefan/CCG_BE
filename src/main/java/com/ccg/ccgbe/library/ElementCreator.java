package com.ccg.ccgbe.library;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.element.attribute.FractionAttribute;

public class ElementCreator {

    ElementCollector E = new ElementCollector();

    public ElementCollector create(){
        add(new Element("D","#fbc42d","desert",true));
        add(new Element("S","#d6cb6e","savanna",true,fraction("Orks")));
        add(new Element("G","#9acd32","grassland",true,fraction("Human")));
        add(new Element("s","#f3ffef","snow",true));
        add(new Element("L","#0000e6","lake",true));
        add(new Element("M","#778577","mountain",false));
        add(new Element("H","#AAA5B7","hills",false));
        add(new Element("R","#4040B6","river",false));
        add(new Element("F","#5aBd27","forest",false,fraction("elf")));
        add(new Element("J","#3a9d37","jungle",false));
        add(new Element("o","#8aad27","oase",false));
        add(new Element("O","#8a3d27","Orkloch",false,fraction("Orks")));
        add(new Element("B","#8a5d37","Barrack",false,fraction("Orks")));

        add(new Element("E","#3aAd27","Lebensbaum",false,fraction("elf")));
        add(new Element("P","#4a8d37","Bogenbaum",false,fraction("elf")));

        add(new Element("V","#Aa5d77","Village",false,fraction("Human")));
        add(new Element("A","#BaBd37","Acker",false,fraction("Human")));
        add(new Element("K","#8a5d77","Kaserne",false,fraction("Human")));
        return E;
    }

    private void add(Element e){
        E.add(e);
    }




    private FractionAttribute fraction(String name){
        return new FractionAttribute(name);
    }
}
