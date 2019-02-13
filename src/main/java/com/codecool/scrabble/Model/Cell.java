package com.codecool.scrabble.Model;

import org.springframework.stereotype.Component;

@Component
public class Cell {

    int cellIndex;
    Character letter;
    int letterBonus;
    int wordBonus;

    public Cell() {
        letterBonus = 1;
        wordBonus = 1;
    }

    public Character getLetter() {

        if (letter == null) {
            char emptyCell = '-';
            return emptyCell;
        }
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getLetterBonus() {
        return letterBonus;
    }

    public void setLetterBonus(int letterBonus) {
        this.letterBonus = letterBonus;
    }

    public int getWordBonus() {
        return wordBonus;
    }

    public void setWordBonus(int wordBonus) {
        this.wordBonus = wordBonus;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public boolean cellContainsLetter() {
        return this.getLetter() != '-';
    }
}
