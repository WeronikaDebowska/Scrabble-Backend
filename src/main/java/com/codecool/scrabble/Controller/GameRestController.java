package com.codecool.scrabble.Controller;

import com.codecool.scrabble.Model.*;
import com.codecool.scrabble.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/scrabble")

public class GameRestController {


    private DictionaryServiceImpl dictService;
//    private MockDictionaryService dictService;


    private DrawService drawService;
    private PointsCounterService pointsService;
    private MoveValidatorImpl moveValidator;
    private ResponseService responseService;
    private ResponseAfterMove responseAfterMove;


    @Autowired
    public GameRestController(
            DictionaryServiceImpl dictService,
//            MockDictionaryService dictService,
            PointsCounterService pointsService,
            DrawService drawService,
            MoveValidatorImpl moveValidatorService,
            ResponseService responseService,
            ResponseAfterMove responseAfterMove) {
        this.dictService = dictService;
        this.pointsService = pointsService;
        this.drawService = drawService;
        this.moveValidator = moveValidatorService;
        this.responseService = responseService;
        this.responseAfterMove = responseAfterMove;
    }

    @PostMapping
    public void postBoard(@RequestBody Cell[] cells) {
        for (Cell cell : cells) {
            System.out.println(cell.getCellIndex());
        }
    }

    @PostMapping(path = "/board")
    public ResponseEntity<ResponseAfterMove> postWord(@RequestBody Board board, User user) {

        moveValidator.setNewBoard(board);
        responseService.clearResponse();
        pointsService.clearScore();

        LinkedList<String> foundWords = moveValidator.checkMoveValidity();
        LinkedList<Cell> newCells = moveValidator.getNewCells();


        int wordPoints;
        Board actualBoard;
        boolean isWordValid;
        boolean isMoveValid = false;

        for (String foundWord : foundWords) {
            isWordValid = dictService.isWordInDict(foundWord);

            if (isWordValid) {
                wordPoints = pointsService.countWordScore(foundWord, newCells);
                pointsService.addToRoundScore(wordPoints);
                isMoveValid = true;
            } else {
                wordPoints = 0;
                isMoveValid = false;

            }
            responseService.createResponseAfterMove(foundWord, wordPoints, isWordValid);
            if (!isMoveValid) {
                break;
            }
        }
        actualBoard = moveValidator.setActualBoard(isMoveValid);

        int roundScore = pointsService.getRoundScore();
        int totalScore = responseAfterMove.getTotalScore();
        responseAfterMove.setRoundScore(roundScore);
        responseAfterMove.setTotalScore(totalScore + roundScore); //total score powinien być przypisany do usera, którego jezcze nie ma

        responseAfterMove.setActualBoard(actualBoard);
        moveValidator.updateState(isMoveValid);

        return new ResponseEntity<>(responseAfterMove, HttpStatus.OK);
    }

    @GetMapping(path = "/draw")
    public ResponseEntity<Character[]> drawLetters(@RequestParam(value = "draw", defaultValue = "7") int number) {
        Character[] drawnLetters = drawService.drawLetters(number);
        return new ResponseEntity<>(drawnLetters, HttpStatus.OK);
    }

    @PostMapping(path = "/draw")
    public ResponseEntity<Character[]> getBackLetters(@RequestBody Character[] letters) {
        int size = letters.length;
        Character[] drawnLetters = drawService.drawLetters(size);
        drawService.getLettersBack(letters);
        return new ResponseEntity<>(drawnLetters, HttpStatus.OK);
    }
}
