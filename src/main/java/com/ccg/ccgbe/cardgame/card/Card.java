package com.ccg.ccgbe.cardgame.card;

import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.HashMap;
import java.util.UUID;

public class Card {

    private UUID cardId;
    private Element[][] operation;

    public Card(int w, int h, HashMap<Pos,Element> operations) {
        this.cardId =UUID.randomUUID();
        operation = new Element[w][h];
        for(Pos pos: operations.keySet()){
            operation[pos.getX()][pos.getY()]=operations.get(pos);
        }
    }

    public Card(Element e) {
        this.cardId =UUID.randomUUID();
        operation = new Element[1][1];
        operation[0][0] = e;
    }


    public Element[][] getOperation() {
        return operation;
    }

    public  String toString(){
        String str = "";
        for(int i=0;i<operation.length;i++){
            for(int j=0;j<operation[0].length;j++){
                str+=operation[i][j]!=null?" "+operation[i][j]+" ":" - ";
            }
            str+="\n";
        }
        return str;
    }


    public UUID getCardId() {
        return cardId;
    }

    public boolean equals(Card card){
        return cardId.equals(card.getCardId());
    }
}
