package com.ccg.ccgbe.cardgame;


import com.ccg.ccgbe.cardgame.builder.Config;
import com.ccg.ccgbe.cardgame.card.Card;
import com.ccg.ccgbe.cardgame.draw.DoNothingDraw;
import com.ccg.ccgbe.cardgame.draw.Draw;
import com.ccg.ccgbe.cardgame.draw.PlaceCardDraw;
import com.ccg.ccgbe.cardgame.player.Player;
import com.ccg.ccgbe.cardgame.rules.Rules;
import com.ccg.ccgbe.cardgame.state.CardGameState;
import com.ccg.ccgbe.cardgame.state.map.Map;
import com.ccg.ccgbe.dto.GameDataDTO;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class CardGame {

    /**
     * State without playerlogic
     */

    private final UUID gameId;
    private CardGameState state;

    private int playerNumber;
    private ArrayList<Player> players;
    private Player turn;

    private ArrayList<Player> winner = new ArrayList<>();

    private final int w = Config.DEFAULT_WIDTH;

    private final int h =Config.DEFAULT_HEIGHT;


    public CardGame(ArrayList<Player> players, Rules rules) {
        gameId = UUID.randomUUID();
        this.players = players;
        this.state = new CardGameState(rules, w,h);
        init();
    }

    public CardGame(Player player1, Player player2, Rules rules) {
        gameId = UUID.randomUUID();
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        this.players = players;
        this.state = new CardGameState(rules,Config.DEFAULT_WIDTH,Config.DEFAULT_HEIGHT);
        init();
    }

    public CardGame(Player player1, Player player2, Rules rules, Map map) {
        gameId = UUID.randomUUID();
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        this.players = players;
        this.state = new CardGameState(rules,map);
        init();
    }

    private void init(){


        turn = players.get(new Random().nextInt(players.size()));
        for(Player p:players){
            newCard(p);
            newCard(p);
            newCard(p);
            newCard(p);
            newCard(p);
        }
    }

    public void doDraw(Draw draw){
        if(!getWinner().isEmpty())return;
        if(draw instanceof DoNothingDraw){
            endTurn();
            return;
        }

        PlaceCardDraw d = (PlaceCardDraw)draw;
        if(draw.getPlayer().equals(turn) && turn.getHand().includes(d.getCard())){
            Player player = draw.getPlayer();
            Card card = d.getCard();
            player.getHand().removeCard(card);
            state.placeCard(d.getCard(),d.getPos());

            endTurn();
        }

    }
    public void endTurn(){
        if(allHandsEmpty()){
            finishGame();
        }
        computeWinner();
        if(winner.isEmpty()){
            nextPlayer();
        }

    }

    public boolean gameEnded(){
        return state.isGameEnded();
    }

    private void nextPlayer(){
        if(players.indexOf(turn)<players.size()-1){
            turn = players.get(players.indexOf(turn)+1);
        }else {
            state.performEndRoundFunctions();
          //  System.out.println("------------- performed end round" );
            turn =players.get(0);

        }
        turn.drawNewCard();
    }

    private boolean allHandsEmpty(){
        for(Player p:players){
            if(!p.hasEmptyHand()){
                return false;
            }
        }
        return true;
    }

    private void finishGame(){
        state.finishGame();
    }

    public ArrayList<Player> getWinner(){
        return winner;
    }

    private void computeWinner(){
        ArrayList<Integer> indizes = state.getRules().computeWinner(state);
        for(int i:indizes){
            winner.add(players.get(i));
        }
    }


    public Player getTurn() {
        return turn;
    }

    private void newCard(Player player){
        player.drawNewCard();
    }


    public String toString(){
        return state.toString();
    }

    public UUID getGameId() {
        return gameId;
    }

    public int getWidth(){
        return w;
    }

    public int getHeight(){
        return h;
    }

    public GameDataDTO getDTO(){
       return new GameDataDTO(state.getMap(), turn.getHand(), turn.getDTO());
    }
}
