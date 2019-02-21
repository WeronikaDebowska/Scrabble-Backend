package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.ResponseAfterDrawing;
import com.codecool.scrabble.Model.LetterPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class DrawServiceImpl {

    private LetterPool pool;

    @Autowired
    public DrawServiceImpl(LetterPool pool
    ) {
        this.pool = pool;
    }


    public LinkedList<Character> drawLetters(int number) {

        int lettersAmount = pool.countAllLettersInPool();
        if (pool.countAllLettersInPool() < number) {
            number = lettersAmount;
        }
        LinkedList<Character> drawnLetters = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            if (!pool.isPoolEmpty()) {
                Character drawn = pool.takeLetterFromPool();
                drawnLetters.add(Character.toUpperCase(drawn));
            } else {
            }
        }
        return drawnLetters;
    }

    public void getLettersBack(Character[] letters) {
        for (Character letter : letters) {
            pool.getLetterBack(letter);
        }
    }


    public void resetLetterPool() {
        pool.resetPool();
    }
}
