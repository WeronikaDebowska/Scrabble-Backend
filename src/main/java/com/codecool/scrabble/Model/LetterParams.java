package com.codecool.scrabble.Model;

import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class LetterParams {

    // map with a letter as a key and points and initial amount in value list
    private HashMap<Character, Integer[]> letterPointsAmount = new HashMap<Character, Integer[]>();

    public LetterParams() {
        createLetterPointsAmountMap();
    }

    private void createLetterPointsAmountMap() {
        letterPointsAmount.put('a', new Integer[]{1, 9});
        letterPointsAmount.put('b', new Integer[]{3, 2});
        letterPointsAmount.put('c', new Integer[]{2, 3});
        letterPointsAmount.put('d', new Integer[]{2, 3});
        letterPointsAmount.put('e', new Integer[]{1, 7});
        letterPointsAmount.put('f', new Integer[]{5, 1});
        letterPointsAmount.put('g', new Integer[]{3, 2});
        letterPointsAmount.put('h', new Integer[]{3, 2});
        letterPointsAmount.put('i', new Integer[]{1, 8});
        letterPointsAmount.put('j', new Integer[]{3, 2});
        letterPointsAmount.put('k', new Integer[]{2, 3});
        letterPointsAmount.put('l', new Integer[]{2, 3});
        letterPointsAmount.put('ł', new Integer[]{3, 2});
        letterPointsAmount.put('m', new Integer[]{2, 3});
        letterPointsAmount.put('n', new Integer[]{1, 5});
        letterPointsAmount.put('o', new Integer[]{1, 6});
        letterPointsAmount.put('p', new Integer[]{2, 3});
        letterPointsAmount.put('r', new Integer[]{1, 4});
        letterPointsAmount.put('s', new Integer[]{1, 4});
        letterPointsAmount.put('t', new Integer[]{2, 3});
        letterPointsAmount.put('u', new Integer[]{3, 2});
        letterPointsAmount.put('w', new Integer[]{1, 4});
//        letterPointsAmount.put('x', new Integer[]{ 1 });
        letterPointsAmount.put('y', new Integer[]{2, 4});
        letterPointsAmount.put('z', new Integer[]{1, 5});
        letterPointsAmount.put('ą', new Integer[]{5, 1});
        letterPointsAmount.put('ę', new Integer[]{5, 1});
        letterPointsAmount.put('ó', new Integer[]{5, 1});
        letterPointsAmount.put('ś', new Integer[]{5, 1});
        letterPointsAmount.put('ć', new Integer[]{6, 1});
        letterPointsAmount.put('ń', new Integer[]{7, 1});
        letterPointsAmount.put('ż', new Integer[]{5, 1});
        letterPointsAmount.put('ź', new Integer[]{9, 1});

    }

    public int getPoints(char letter) {
        return letterPointsAmount.get(letter)[0];
    }

    int getInitialAmount(char letter) {
        return letterPointsAmount.get(letter)[1];
    }

    Character[] getAllLetters() {
        Character[] allLetters = new Character[letterPointsAmount.size()];
//        return (Character[]) letterPointsAmount.keySet().toArray();
        int i = 0;
        for (Character letterKey : letterPointsAmount.keySet()) {
            allLetters[i] = letterKey;
            i++;
        }
        return allLetters;

    }

}