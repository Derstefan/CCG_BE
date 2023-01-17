package com.ccg.ccgbe.cardgame.rules.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;

public class ElementCollector {


    //TODO: überarbeiten!!! optimieren type vs element....
    private ArrayList<Element> elements = new ArrayList<>();

    private ArrayList<EType> types = new ArrayList<>();

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

    public void addTypes(String ... typeNames){
        for(String typeName:typeNames){
            if(!typeAlreadyExists(typeName)){

                types.add(new EType(typeName));
            }
        }
    }


    public void add(EType type) {
        if(typeAlreadyExists(type.getName())){
            return;
        }
        types.add(type);
    }

    public void add(Element element) {
        if(containsElement(element.getType().getName())){
            return;
        }
        types.add(element.getType());
        elements.add(element);
    }
    public void add(String typeName) {
        if(typeAlreadyExists(typeName)){
            return;
        }
        EType type = new EType(typeName);
        types.add(type);
        elements.add(new Element(type));
    }

    private boolean typeAlreadyExists(String typeName){
        for(EType t: types){
            if(typeName.equals(t.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean containsElement(String typeName){
        for(Element e: elements){
            if(typeName.equals(e.getType().getName())){
                return true;
            }
        }
        return false;
    }

    public Element get(EType type){
        for(Element e: elements){
            if(type.equals(e.getType())){
                return e;
            }
        }
        return null;
    }

    public Element get(String typeName){
        for(Element e: elements){
            if(e.getType().getName().equals(typeName)){
                return e;
            }
        }
    throw new IllegalArgumentException("No such Element exists: " + typeName);
    }

    public EType TYPE(String typeName){
        for(EType t: types){
            if(typeName.equals(t.getName())){
                return t;
            }
        }
        return null;
    }

    public Element getRandom(){
        return elements.get(r.nextInt(elements.size()));
    }


    public Element getRandomFrom(String from){
        ArrayList<Element> fromElements = getCardsFrom(from);
        if(fromElements.size()==0)throw new IllegalArgumentException("No such Cardtype found: "+from);
        return fromElements.get(r.nextInt(fromElements.size()));
    }

    public ArrayList<Element> getCardsFrom(String from){
        ArrayList<Element> fromElements = new ArrayList<>();
        for(Element e:elements){
            if(Arrays.stream(e.getType().getTags()).anyMatch(tag -> from.equals(tag))){
                fromElements.add(e);
            }
        }
        return fromElements;
    }

    public ArrayList<Element> getBasicElements(){

        ArrayList<Element> basicElements = new ArrayList<>();
        for(Element e:elements){
            if(e.getType().isBasic()){
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

    public void finish(){
        for(EType t:types){

            if(!containsElement(t.getName())){
                elements.add(new Element(t));
            }
        }
    }



    public String toString(){
        String str = "";
        for(Element e:elements){
            str+=e.toString();
        }
        return str;
    }
}
