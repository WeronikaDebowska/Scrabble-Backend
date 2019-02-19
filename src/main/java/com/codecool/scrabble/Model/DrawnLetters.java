package com.codecool.scrabble.Model;


import org.springframework.stereotype.Component;

import java.util.LinkedList;


public class DrawnLetters {

    private LinkedList<Character> letters = new LinkedList<>();

    public LinkedList<Character> getLetters() {
        return letters;
    }

    public void setLetters(LinkedList<Character> letters) {
        this.letters = letters;
    }
}
