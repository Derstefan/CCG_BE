package com.ccg.ccgbe.cardgame.builder.core;

import com.ccg.ccgbe.cardgame.card.Card;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimpleCardBuilder {

    private ElementCollector E;

    public SimpleCardBuilder(ElementCollector e) {
        E = e;
    }


    public Card getRandomCard(int w, int h){

        HashMap<Pos, Element> map = new HashMap<>();

        int maxNumber = w*h;
        Random rand = new Random();

        for(int i=0;i<maxNumber;i++){
            int r = rand.nextInt(maxNumber);
            map.put(new Pos(r/h,r%h), E.getRandom());
        }

        Card card = new Card(w,h,map);
        return card;
    }

    public Card getRandomCard2x2(){
        return getRandomCard(2,2);
    }

    public  Card getRandomCard1x1(){
        return getRandomCard(1,1);
    }



    public ArrayList<Card> getRandom1x1Cards(int number){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<number;i++){
            cards.add(getRandomCard1x1());
        }
        return cards;
    }

    public ArrayList<Card> getRandom2x2Cards(int number){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<number;i++){
            cards.add(getRandomCard2x2());
        }
        return cards;
    }

    public ArrayList<Card> getRandom3x3Cards(int number){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<number;i++){
            cards.add(getRandomCard(3,3));
        }
        return cards;
    }

    public ArrayList<Card> getRandomWxHCards(int number,int w, int h){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<number;i++){
            cards.add(getRandomCard(rand1(w),rand1(h)));
        }
        return cards;
    }

    public static int rand1(int l){
        if(l==1)return 1;
        return new Random().nextInt(l-1)+1;
    }

}
