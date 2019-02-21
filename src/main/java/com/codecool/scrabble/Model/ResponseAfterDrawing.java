package com.codecool.scrabble.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class ResponseAfterDrawing {

    private LinkedList<Character> letters = new LinkedList<>();
    private User user;

    private int totalScore;

    private int roundScore;

    @Autowired
    public ResponseAfterDrawing(User user) {
        this.user = user;
    }


//    public void actualizeTotalScore(){
//        this.totalScore = user.getTotalScore();
//    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public LinkedList<Character> getLetters() {
        return letters;
    }


    public void setLetters(LinkedList<Character> letters) {
        this.letters = letters;
    }

    public void clearLetters() {
        letters.clear();
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }
}
