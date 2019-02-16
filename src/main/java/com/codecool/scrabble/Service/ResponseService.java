package com.codecool.scrabble.Service;


import com.codecool.scrabble.Model.Board;
import com.codecool.scrabble.Model.ResponseAfterMove;
import com.codecool.scrabble.Model.WordDetails;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    private ResponseAfterMove responseAfterMove;

    public ResponseService(ResponseAfterMove responseAfterMove) {
        this.responseAfterMove = responseAfterMove;
    }

    public void clearResponse() {
        responseAfterMove.clearRoundParams();
    }

    public void createResponseAfterMove(String foundWord, int wordPoints, boolean valid) {
        WordDetails composedWord = new WordDetails();

        composedWord.setValid(valid);
        composedWord.setWord(foundWord);
        composedWord.setPoints(wordPoints);

        if (valid) {
            responseAfterMove.addMessage("Brawo! Ułożyłeś słowo: " + foundWord + " warte " + wordPoints + " pkt.");
        } else {
            responseAfterMove.addMessage("Niestety, słowa: " + foundWord + " nie ma w słowniku języka polskiego.");
        }
        responseAfterMove.addWord(composedWord);
//        responseAfterMove.setActualBoard(board);
    }
}
