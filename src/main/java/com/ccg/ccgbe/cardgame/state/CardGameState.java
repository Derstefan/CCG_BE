package com.ccg.ccgbe.cardgame.state;



import com.ccg.ccgbe.cardgame.card.Card;
import com.ccg.ccgbe.cardgame.events.EventHistroy;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.rule.PerformingRule;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.map.Cell;
import com.ccg.ccgbe.cardgame.state.map.Map;
import com.ccg.ccgbe.cardgame.state.map.Pos;
import lombok.extern.slf4j.Slf4j;


import java.util.Optional;

@Slf4j
public class CardGameState {


    private Map map;

    private Map tempMap;

    private RuleLibrary ruleLibrary;

    //toEstage
    private boolean gameEnded = false;

    private EventHistroy eventHistroy = new EventHistroy();

    public CardGameState(RuleLibrary ruleLibrary, int w, int h) {
        this.ruleLibrary = ruleLibrary;
        //map = new Element[Config.DEFAULT_WIDTH][Config.DEFAULT_HEIGHT];
        this.map = new Map(w,h);
        tempMap = map.clone();
    }

    public CardGameState(RuleLibrary ruleLibrary, Map map) {
        this.ruleLibrary = ruleLibrary;
        //map = new Element[Config.DEFAULT_WIDTH][Config.DEFAULT_HEIGHT];
        this.map = map;
        tempMap = map.clone();
    }

    //manuell
    //card -> zwischenspeicher
    //automatic -> zwischenspeicher


    /**
     * Manuell placement works direct on Map
     * @param element
     * @param pos
     */
    public void placeElementAndUpdate(Element element, Pos pos){
        placeElement(element,pos);
        updateMap();
    }

    /**
     * Put Element at position without eny neccesary Rule
     * @param element
     * @param pos
     */
    public void placeElementWithoutRuleAndUpdate(Element element, Pos pos){
        put(element,pos);
        updateMap();
    }

    public void updateMap(){
        this.map = tempMap.clone();
        tempMap = map.clone();
    }

    private void placeElement(Element element, Pos pos){
        Optional<Rule> opt = ruleLibrary.can(element,this,pos);

        if(opt.isPresent()){

            Element beforeElement =getElementAt(pos);
            Rule rule= opt.get();

            if(rule.check(this,pos)){
                tempMap.put(element,pos);

                if(rule instanceof PerformingRule){
                    ((PerformingRule) rule).perform(this,pos);
                }

                if(element.performsOnPlacement()){
                    element.performPlacement(this,beforeElement,pos);
                }
            }
        }
    }

    public void placeCard(Card card, Pos pos) {
        Element[][] o = card.getOperation();
        for (int i = 0; i < o.length; i++) {
            for (int j = 0; j < o[0].length; j++) {
                Pos p = new Pos(i, j);
                if (isOnMap(p.plus(pos)) && o[i][j] != null) {
                    placeElement(o[i][j], p.plus(pos));
                    log.info("Placing {} at {}", o[i][j], p.plus(pos));
                }
            }
        }
        updateMap();
        log.info("updated");
    }


    public void performEndRoundFunctions(){
        for(int i=0;i<map.getWidth();i++){
            for(int j=0;j<map.getHeight();j++){
                if(map.getElement(i,j)!=null){

                    Element element = map.getElement(i,j);
                    Pos pos = new Pos(i,j);
                    boolean cellUpdated = false;

                    for(Rule automaticRule: ruleLibrary.getAutomaticRuleSet()){
                        if(automaticRule.check(this,pos)){
                            Element newElement = automaticRule.getElement();

                            tempMap.put(newElement,pos);
                            cellUpdated=true;


                            if(automaticRule instanceof PerformingRule){
                                ((PerformingRule) automaticRule).perform(this,pos);
                            }

                            if(newElement.performsOnPlacement()){
                                newElement.performPlacement(this,element,pos);
                            }
                            break;
                        }
                    }

                    if(!cellUpdated && element.performsEachRound()){
                        element.performEndRound(this,pos);
                    }
                }
            }
        }
        updateMap();
    }


    public void computePossibleChanges() {
        for(int i=0;i<map.getWidth();i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Pos pos = new Pos(i,j);
                map.getCell(pos).clearPossibleChanges();
                for(Rule r: ruleLibrary.getRulesWithpossibleChange(this,pos)){
                    map.getCell(pos).addPossibleChanges(r.getElement().getId());
                }
            }
        }
    }



    public Element getElementAt(Pos pos) {
        return map.getElement(pos);
    }

    public boolean isOnMap(Pos pos){
        return pos.getX()>=0 && pos.getX()<map.getWidth() && pos.getY()>=0 && pos.getY()<map.getHeight();
    }

    public Cell[][] getMapData() {
        return map.getMapData();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        this.tempMap=map.clone();
    }


    public RuleLibrary getRules() {
        return ruleLibrary;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void finishGame() {
        this.gameEnded = true;
    }

    public  String toString(){
        return map.toString();
    }



    //------------private-------------------------------------------------------------------


    private void put(Element element, Pos pos){

        tempMap.put(element,pos);
    }



}
