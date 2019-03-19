package com.codecool.scrabble.Controller;

import com.codecool.scrabble.Model.*;
import com.codecool.scrabble.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;


@Service
public class GameController {

    private DictionaryServiceImpl dictService;
//    private MockDictionaryService dictService;

    private DrawServiceImpl drawService;
    private PointsCounterServiceImpl pointsService;
    private MoveValidatorImpl moveValidator;
    private ResponseService responseService;
    private ResponseAfterMove responseAfterMove;
    private ResponseAfterDrawing responseAfterDrawing;
    private User user;


    @Autowired
    public GameController(
            DictionaryServiceImpl dictService,
            DrawServiceImpl drawService,
            PointsCounterServiceImpl pointsService,
            MoveValidatorImpl moveValidator,
            ResponseService responseService,
            ResponseAfterMove responseAfterMove,
            ResponseAfterDrawing responseAfterDrawing,
            User user) {
        this.dictService = dictService;
        this.drawService = drawService;
        this.pointsService = pointsService;
        this.moveValidator = moveValidator;
        this.responseService = responseService;
        this.responseAfterMove = responseAfterMove;
        this.responseAfterDrawing = responseAfterDrawing;
        this.user = user;
    }


    public ResponseAfterDrawing drawLetters(Character[] letters) {

        int penaltyPoints = -5;
        int size = letters.length;
        int roundScore = (size * (penaltyPoints));
        user.setTotalScore(user.getTotalScore() + roundScore);
        LinkedList<Character> drawnLetters = drawService.drawLetters(size);
        drawService.getLettersBack(letters);

        responseAfterDrawing.clearLetters();
        responseAfterDrawing.setLetters(drawnLetters);
        responseAfterDrawing.setTotalScore(user.getTotalScore());
        responseAfterDrawing.setRoundScore(roundScore);
        responseAfterMove.setTotalScore(user.getTotalScore());

        return responseAfterDrawing;
    }

    public ResponseAfterDrawing drawLetters(int number) {

        LinkedList<Character> drawnLetters = drawService.drawLetters(number);    //initial amount of drawn letters

        responseAfterDrawing.clearLetters();
        responseAfterDrawing.setLetters(drawnLetters);

        return responseAfterDrawing;
    }

    public ResponseAfterMove serveMove(Board board) {


        responseService.clearResponse();
        pointsService.clearScore();

        moveValidator.setNewBoard(board);
        moveValidator.trialBoardPrint();

        LinkedList<String> foundWords = moveValidator.checkMoveValidity();
        LinkedList<Cell> newCells = moveValidator.getNewCells();


        int wordPoints;
        Board actualBoard;
        boolean isWordValid;
        boolean isMoveValid = false;

        if (!(foundWords.size() == 0)) {

            for (String foundWord : foundWords) {
                isMoveValid = true;
                isWordValid = dictService.isWordInDict(foundWord);

                if (isWordValid) {
                    wordPoints = pointsService.countWordScore(foundWord, newCells);
                    pointsService.addToRoundScore(wordPoints);
                } else {
                    wordPoints = 0;
                    isMoveValid = false;

                }
                responseService.createResponseAfterMove(foundWord, wordPoints, isWordValid);
            }
        }
        actualBoard = moveValidator.setActualBoard(isMoveValid);

        int roundScore = 0;
        if (isMoveValid) {
            roundScore = pointsService.getRoundScore();
        }
        int totalScore = user.getTotalScore();
        user.setTotalScore(totalScore + roundScore);
        responseAfterMove.setRoundScore(roundScore);
        responseAfterMove.setTotalScore(user.getTotalScore()); //total score powinien być przypisany do usera, którego jezcze nie ma

        responseAfterMove.setActualBoard(actualBoard);
        moveValidator.updateState(isMoveValid);

        return responseAfterMove;
    }

    public ResponseAfterMove restart() {
        moveValidator.clearBoard();
        drawService.resetLetterPool();
        user.setTotalScore(0);
        responseAfterMove.reset();

        return responseAfterMove;
    }


}

