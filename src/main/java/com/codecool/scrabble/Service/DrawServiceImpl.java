package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.DrawnLetters;
import com.codecool.scrabble.Model.LetterPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class DrawServiceImpl implements DrawService {

    private LetterPool pool;
    private DrawnLetters drawnLetters;

    @Autowired
    public DrawServiceImpl(LetterPool pool,
                           DrawnLetters drawnLetters) {
        this.pool = pool;
        this.drawnLetters = drawnLetters;
    }

    @Override
    public DrawnLetters drawLetters(int number) {
        drawnLetters.getLetters().clear();
        int lettersAmount = pool.countAllLettersInPool();
        if (pool.countAllLettersInPool() < number) {
            number = lettersAmount;
        }
        for (int i = 0; i < number; i++) {
            if (!pool.isPoolEmpty()) {
                drawnLetters.getLetters().add(Character.toUpperCase(pool.takeLetterFromPool()));
            }
        }
        return drawnLetters;
    }

    @Override
    public void getLettersBack(Character[] letters) {
        for (Character letter : letters) {
            pool.getLetterBack(letter);
        }
    }
}
