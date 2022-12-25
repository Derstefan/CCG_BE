package com.ccg.ccgbe.cardgame.rules;


import com.ccg.ccgbe.cardgame.rules.condition.Condition;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.element.ElementCollector;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class Rules {


    private ElementCollector E;
    private ArrayList<Rule> ruleSet = new ArrayList<>();

    private ArrayList<Rule> automaticEuleSet = new ArrayList<>();

    private ArrayList<Condition> winnerCondition;

    public Rules(ElementCollector E, ArrayList<Rule> manuellRuleSet, ArrayList<Condition> winnerCondition) {
        this.E = E;
        this.ruleSet = ruleSet;
        this.winnerCondition = winnerCondition;
        checkConstistence();
    }

    public Rules(ElementCollector E, ArrayList<Rule> manuellEuleSet,ArrayList<Rule> automaticEuleSet, ArrayList<Condition> winnerCondition) {
        this.E = E;
        this.ruleSet = manuellEuleSet;
        this.ruleSet.addAll(automaticEuleSet);
        this.automaticEuleSet = automaticEuleSet;
        this.winnerCondition = winnerCondition;
        checkConstistence();
    }

    private void checkConstistence(){
        //TODO: check if rule constits if the element definitions
    }

    public Optional<Rule> can(Element element, CardGameState state, Pos pos){
        //put it in state

        for(Rule r: ruleSet){
            if(element.equals(r.getElement())){
                if(r.check(state,pos)){
                    return Optional.of(r);
                }
            }
        }
        return Optional.empty();
    }

    public ArrayList<Rule> getAutomaticEuleSet() {
        return automaticEuleSet;
    }

/*    public void put(Element element, CardGameState state, Pos pos){
        //put it in state
        if(can(element,state,pos)){
            state.put(element,pos);
        }
    }

    public void placeCard(Card card, CardGameState state, Pos pos){
        Element[][] o = card.getOperation();
        for(int i=0;i<o.length;i++){
            for(int j=0;j<o[0].length;j++){
                Pos p = new Pos(i,j);
                if(state.isOnMap(p.plus(pos)) && o[i][j]!=null){
                    put(o[i][j], state, p.plus(pos));
                }
            }
        }
    }*/

    public ArrayList<Integer> computeWinner(CardGameState state){
        ArrayList<Integer> winner = new ArrayList<>();
        for(Condition c:winnerCondition){
            if(c.check(state)){
                winner.add(winnerCondition.indexOf(c));
            }
        }
        return winner;
    }

    public ElementCollector getE() {
        return E;
    }
}
