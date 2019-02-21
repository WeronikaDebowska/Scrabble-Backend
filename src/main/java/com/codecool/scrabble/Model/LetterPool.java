package com.codecool.scrabble.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Random;

@Component
public class LetterPool {

    private LetterParams letterParams;

    public LinkedList<Character> getLetterPool() {
        return letterPool;
    }

    private LinkedList<Character> letterPool = new LinkedList<>();


    @Autowired
    public LetterPool(LetterParams letterParams) {
        this.letterParams = letterParams;
        initializeLetterPool();
        randomizePool();
    }


    //creating pool with initial amount of letters
    private void initializeLetterPool() {
        addAllLettersToPool();
    }

    private void addAllLettersToPool() {
        for (Character letter : letterParams.getAllLetters()) {
            for (int i = 0; i < letterParams.getInitialAmount(letter); i++) {
                letterPool.add(letter);
            }
        }
    }

    private void randomizePool() {

        LinkedList<Character> newLetterPool = new LinkedList<Character>();

        do {
            Random rand = new Random();
            int randomInt = rand.nextInt(countAllLettersInPool());

            newLetterPool.add(letterPool.get(randomInt));
            letterPool.remove(randomInt);
        } while (!isPoolEmpty());
        this.letterPool = newLetterPool;
    }

    public int countAllLettersInPool() {
        return letterPool.size();
    }

    public boolean isPoolEmpty() {
        return countAllLettersInPool() == 0;
    }

    public Character takeLetterFromPool() {
        return letterPool.poll();
    }

    public void getLetterBack(Character letter) {
        letterPool.add(letter);
    }

    public void resetPool() {
        letterPool.clear();
        initializeLetterPool();
        randomizePool();
    }


}
