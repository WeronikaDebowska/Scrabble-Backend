package com.codecool.scrabble.Model;

public class Cell {

    int cellIndex;
    Character letter;
    int letterBonus;
    int wordBonus;

    public Cell() {
        this.letterBonus = 1;
        this.wordBonus = 1;
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

    public boolean containsLetter() {
        return this.getLetter() != '-';
    }

    public boolean inFirstRow() {
        return (this.getCellIndex() <= 14);
    }

    public boolean inLastRow() {
        return (this.getCellIndex() >= 1400);
    }

    public boolean inFirstColumn() {
        return (this.getCellIndex() % 100 == 0);
    }

    public boolean inLastColumn() {
        return (this.getCellIndex() % 100 == 14);
    }
}
