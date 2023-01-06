package com.ccg.ccgbe.controller;

import com.ccg.ccgbe.GameManager.GameManager;
import com.ccg.ccgbe.cardgame.CardGame;
import com.ccg.ccgbe.cardgame.draw.PlaceCardDraw;
import com.ccg.ccgbe.cardgame.rules.RuleLibrary;
import com.ccg.ccgbe.cardgame.rules.element.Element;
import com.ccg.ccgbe.cardgame.rules.rule.Rule;
import com.ccg.ccgbe.cardgame.state.map.Pos;
import com.ccg.ccgbe.dto.ElementPortFolioDTO;
import com.ccg.ccgbe.dto.GameDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/library/")
@Slf4j
public class RuleLibraryController {


    @Autowired
    private GameManager gameManager;

    @GetMapping("elements")
    public ResponseEntity<ArrayList<Element>> getElements(){
        CardGame game = gameManager.getFirstGame();
        RuleLibrary library = game.getRuleLibrary();
        return ResponseEntity.ok(library.getE().getAllElements());
    }


    @GetMapping("element/{elementId}")
    public ResponseEntity<ElementPortFolioDTO> getElementPortfolio(@PathVariable String elementId){
        CardGame game = gameManager.getFirstGame();
        RuleLibrary library = game.getRuleLibrary();
        return ResponseEntity.ok(new ElementPortFolioDTO(library,library.getE().get(elementId)));
    }


    @GetMapping("rules")
    public ResponseEntity<ArrayList<Rule>> getRules(){
        CardGame game = gameManager.getFirstGame();
        RuleLibrary library = game.getRuleLibrary();
        return ResponseEntity.ok(library.getRuleSet());
    }

}
