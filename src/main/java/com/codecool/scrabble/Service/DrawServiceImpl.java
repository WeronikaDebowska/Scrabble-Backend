package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.LetterPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawServiceImpl implements DrawService {

    private LetterPool pool;

    @Autowired
    public DrawServiceImpl(LetterPool pool) {
        this.pool = pool;
    }

    @Override
    public Character[] drawLetters(int number) {
        int lettersAmount = pool.countAllLettersInPool();
        if (pool.countAllLettersInPool() < number) {
            number = lettersAmount;
        }
        Character[] drawnLetters = new Character[number];
        for (int i = 0; i < number; i++) {
            if (!pool.isPoolEmpty()) {
                drawnLetters[i] = pool.takeLetterFromPool();
            }
        }
        return drawnLetters;
    }

    @Override
    public void getLettersBack(Character[] letters) {
        for (Character letter : letters) {
            pool.gettLetterBack(letter);
        }
    }
}
