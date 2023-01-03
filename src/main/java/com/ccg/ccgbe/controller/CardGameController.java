package com.ccg.ccgbe.controller;


import com.ccg.ccgbe.GameManager.GameManager;
import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.bot.SimpleBot;
import com.ccg.ccgbe.cardgame.draw.DoNothingDraw;
import com.ccg.ccgbe.cardgame.draw.PlaceCardDraw;
import com.ccg.ccgbe.cardgame.state.map.Pos;
import com.ccg.ccgbe.dto.GameDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/")
@Slf4j
public class CardGameController {


    @Autowired
    private GameManager gameManager;

    //später
    //new game
    //join
    //....




    //check update
    //update gamedata

    //doDraw player draw


    @GetMapping("gameData")
    public ResponseEntity<GameDataDTO> getGameData(){
        CardGame game = gameManager.getFirstGame();
        return ResponseEntity.ok(game.getDTO());
    }

    @GetMapping("randomDraw")
    public ResponseEntity<GameDataDTO> ramdomDraw(){
        CardGame game = gameManager.getFirstGame();
        SimpleBot.doDrawOn(game);
        return ResponseEntity.ok(game.getDTO());
    }

    @GetMapping("endTurn")
    public ResponseEntity<GameDataDTO> endTurn(){
        CardGame game = gameManager.getFirstGame();
        game.doDraw(new DoNothingDraw(game.getTurn()));
        return ResponseEntity.ok(game.getDTO());
    }

    @GetMapping("draw/{cardId}/{x}/{y}")
    public ResponseEntity<GameDataDTO> draw(@PathVariable UUID cardId,@PathVariable int x,@PathVariable int y){
        CardGame game = gameManager.getFirstGame();
        PlaceCardDraw d = new PlaceCardDraw(game.getTurn(),game.getTurn().getHand().getByUuid(cardId),new Pos(x,y));
        game.doDraw(d);
        return ResponseEntity.ok(game.getDTO());
    }


    @GetMapping("newGame")
    public ResponseEntity<String> newGame(){
        gameManager.clearGames();
        gameManager.createGame_Test();
        return ResponseEntity.ok("jop");
    }

}
