package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.Cell;
import com.codecool.scrabble.Model.LetterParams;
import com.codecool.scrabble.Model.LetterPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class PointsCounterServiceImpl {

    private LetterPool pool;
    private LetterParams letterParams;

    private int roundScore;

    @Autowired
    public PointsCounterServiceImpl(LetterPool pool, LetterParams letterParams) {
        this.pool = pool;
        this.letterParams = letterParams;
        this.roundScore = 0;
    }

    public int countWordScore(String word, LinkedList<Cell> newCells) {
        int wordScore = 0;
        int wordBonus = 1;
        for (Cell cell : newCells) {
            if (cell.getWordBonus() != 0) {
                wordBonus *= cell.getWordBonus();
            }
        }
        System.out.println("wordBonus " + wordBonus);
        int i = 0;
        for (char wordLetter : word.toCharArray()) {
            int letterPoints = letterParams.getPoints(wordLetter);
            int letterBonus = 1;

            if (newCells.size() > i) {
                if (wordLetter == newCells.get(i).getLetter()) {
                    letterBonus = newCells.get(i).getLetterBonus();
                    i++;
                }
            }
            System.out.println("letter points " + letterPoints + " letter bonus " + letterBonus);
            wordScore += letterPoints * letterBonus;
        }
        wordScore *= wordBonus;
        return wordScore;
    }

    public void addToRoundScore(int points) {
        roundScore += points;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public void clearScore() {
        roundScore = 0;
    }
}
