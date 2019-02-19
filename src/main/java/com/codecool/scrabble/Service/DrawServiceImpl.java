package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.DrawnLetters;
import com.codecool.scrabble.Model.LetterPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class DrawServiceImpl {

    private LetterPool pool;
    private DrawnLetters drawnLetters;

    @Autowired
    public DrawServiceImpl(LetterPool pool
    ) {
        this.pool = pool;
        this.drawnLetters = new DrawnLetters();
    }


    public DrawnLetters drawLetters(int number) {
        drawnLetters.getLetters().clear();
        int lettersAmount = pool.countAllLettersInPool();
        if (pool.countAllLettersInPool() < number) {
            number = lettersAmount;
        }
        System.out.println("losuję " + number);
        for (int i = 0; i < number; i++) {
            if (!pool.isPoolEmpty()) {
                Character drawn = pool.takeLetterFromPool();
                System.out.println(drawn);
                drawnLetters.getLetters().add(Character.toUpperCase(drawn));
            } else {
                System.out.println("PUSTO");
            }
        }
        return drawnLetters;
    }


    public void getLettersBack(Character[] letters) {
        for (Character letter : letters) {
            pool.getLetterBack(letter);
        }
        System.out.println("pool " + pool.countAllLettersInPool());
    }
}
