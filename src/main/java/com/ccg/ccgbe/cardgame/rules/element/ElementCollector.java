package com.ccg.ccgbe.cardgame.rules.element;

import com.ccg.ccgbe.cardgame.rules.element.attribute.FractionAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;

public class ElementCollector {

    private ArrayList<Element> elements = new ArrayList<>();

    private Random r;

    public ElementCollector() {
        r=new Random();
    }

    public ElementCollector(long seed) {
        this.r=new Random(seed);
    }




    public ArrayList<Element> getAllElements() {
        return elements;
    }



    public void add(Element element) {
        if(containsElement(element.getId())){
            return;
        }
        elements.add(element);
    }


    public boolean containsElement(String id){
        for(Element e: elements){
            if(id.equals(e.getId())){
                return true;
            }
        }
        return false;
    }


    public Element get(String id){
        for(Element e: elements){
            if(id.equals(e.getId())){
                return e;
            }
        }
        throw new IllegalArgumentException("No such Element exists: " + id);

    }



    public ArrayList<Element> getElements(Predicate<Element> predicate){
        ArrayList<Element> elements = new ArrayList<>();
        for(Element e: this.elements){
            if(predicate.test(e)){
                elements.add(e);
            }
        }
        return elements;
    }

    public Element getRandom(){
        return elements.get(r.nextInt(elements.size()));
    }


    public Element getRandomFromFraction(String fraction){
        ArrayList<Element> fromElements = getCardsFrom(fraction);
        if(fromElements.size()==0)throw new IllegalArgumentException("No such Fraction found: "+fraction);
        return fromElements.get(r.nextInt(fromElements.size()));
    }

    public ArrayList<Element> getCardsFrom(String fraction){
        ArrayList<Element> fromElements = new ArrayList<>();
        for(Element e:elements){
            if(Arrays.stream(e.getAttributes()).anyMatch(attribute -> {
                if(attribute instanceof FractionAttribute){
                    return ((FractionAttribute) attribute).getFractionName().equals(fraction);
                }
                return false;
            })){
                fromElements.add(e);
            }
        }
        return fromElements;
    }



    public ArrayList<Element> getBasicElements(){

        ArrayList<Element> basicElements = new ArrayList<>();
        for(Element e:elements){
            if(e.isBasic()){
                basicElements.add(e);
            }
        }
        return basicElements;
        //        return elements.stream().filter(element -> element.getType().isBasic());
    }

    public Element getRandomBasic(){
        ArrayList<Element> basicElements = getBasicElements();
        return basicElements.get(r.nextInt(basicElements.size()));
    }





    public String toString(){
        String str = "";
        for(Element e:elements){
            str+=e.toString();
        }
        return str;
    }
}
