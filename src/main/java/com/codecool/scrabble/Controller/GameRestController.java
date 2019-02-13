package com.codecool.scrabble.Controller;

import com.codecool.scrabble.Model.Board;
import com.codecool.scrabble.Model.Word;
import com.codecool.scrabble.Model.Cell;
import com.codecool.scrabble.Model.WordDetails;
import com.codecool.scrabble.Service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/scrabble")

public class GameRestController {


    private MockDictionaryService dictService;
    private DrawService drawService;
    private PointsCounterService pointsService;
    private MoveValidatorImpl moveValidator;
    private String message;

    @Autowired
    public GameRestController(MockDictionaryService dictService,
                              PointsCounterService pointsService,
                              DrawService drawService,
                              MoveValidatorImpl moveValidatorService) {
        this.dictService = dictService;
        this.pointsService = pointsService;
        this.drawService = drawService;
        this.moveValidator = moveValidatorService;
    }

    @PostMapping
    public void postBoard(@RequestBody Cell[] cells) {
        for (Cell cell : cells) {
            System.out.println(cell.getCellIndex());
        }
    }


    @PostMapping(path = "/board")
    public void postWord(@RequestBody Board words) {
        Board newBoard = new Board();
        for (Cell cell : words.getBoard()) {
            int cellIndex = cell.getCellIndex();
            char cellLetter = cell.getLetter();
            newBoard.getCellByIndex(cellIndex).setLetter(cellLetter);

        }

        moveValidator.setNewBoard(newBoard);
        LinkedList<String> foundWords = moveValidator.checkMoveValidity();
        LinkedList<Cell> newCells = moveValidator.getNewCells();

        for (String foundWord : foundWords) {
            System.out.println("nowe słowo " + foundWord);


            if (dictService.isWordInDict(foundWord)) {
                System.out.println("znalezione w bazie!");
                int score = pointsService.countWordScore(foundWord, newCells);
                System.out.println("SCORE " + score);
            }
        }
        moveValidator.updateState();

    }


    @GetMapping(path = "/draw")
    public ResponseEntity<Character[]> drawLetters(@RequestParam(value = "draw", defaultValue = "7") int number) {
        Character[] drawnLetters = drawService.drawLetters(number);
        return new ResponseEntity<>(drawnLetters, HttpStatus.OK);

    }

    @PostMapping(path = "/draw")
    public void getBackLetters(@RequestBody Character[] letters) {
        drawService.getLettersBack(letters);

    }


}
