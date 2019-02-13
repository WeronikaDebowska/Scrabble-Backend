package com.codecool.scrabble.Controller;

import com.codecool.scrabble.Model.Board;
//import com.codecool.scrabble.Model.Word;
import com.codecool.scrabble.Model.Cell;
import com.codecool.scrabble.Model.ResponseAfterMove;
import com.codecool.scrabble.Model.WordDetails;
import com.codecool.scrabble.Service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import net.bytebuddy.dynamic.scaffold.MethodGraph;
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


    private DictionaryServiceImpl dictService;
    private DrawService drawService;
    private PointsCounterService pointsService;
    private MoveValidatorImpl moveValidator;
    private ResponseAfterMove responseAfterMove;

    @Autowired
    public GameRestController(DictionaryServiceImpl dictService,
                              PointsCounterService pointsService,
                              DrawService drawService,
                              MoveValidatorImpl moveValidatorService,
                              ResponseAfterMove responseAfterMove) {
        this.dictService = dictService;
        this.pointsService = pointsService;
        this.drawService = drawService;
        this.moveValidator = moveValidatorService;
        this.responseAfterMove = responseAfterMove;
    }

    @PostMapping
    public void postBoard(@RequestBody Cell[] cells) {
        for (Cell cell : cells) {
            System.out.println(cell.getCellIndex());
        }
    }


    @PostMapping(path = "/board")
    public ResponseEntity<ResponseAfterMove> postWord(@RequestBody Board words) {

        ResponseAfterMove response = new ResponseAfterMove();

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

            WordDetails composedWord = new WordDetails();

            if (dictService.isWordInDict(foundWord)) {
                int wordPoints = pointsService.countWordScore(foundWord, newCells);

                composedWord.setValid(true);
                composedWord.setWord(foundWord);

                composedWord.setPoints(wordPoints);
                response.addWord(composedWord);
                response.setActualBoard(moveValidator.getNewBoard());
                response.increaseRoundScore(wordPoints);
            } else {
                composedWord.setValid(false);
                composedWord.setWord(foundWord);
                composedWord.setPoints(0);
                response.addWord(composedWord);
            }
        }

        response.setValidity();

        moveValidator.updateState();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping(path = "/draw")
    public ResponseEntity<Character[]> drawLetters(@RequestParam(value = "draw", defaultValue = "7") int number) {
        Character[] drawnLetters = drawService.drawLetters(number);
        return new ResponseEntity<>(drawnLetters, HttpStatus.OK);

    }

    @PostMapping(path = "/draw")
    public ResponseEntity<String> getBackLetters(@RequestBody Character[] letters) {
        drawService.getLettersBack(letters);
        int size = letters.length;
        return new ResponseEntity<>(size + " literki oddane", HttpStatus.OK);


    }


}
