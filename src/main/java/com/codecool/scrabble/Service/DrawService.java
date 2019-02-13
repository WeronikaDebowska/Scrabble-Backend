package com.codecool.scrabble.Service;

public interface DrawService {
    Character[] drawLetters(int number);

    void getLettersBack(Character[] letters);
}
