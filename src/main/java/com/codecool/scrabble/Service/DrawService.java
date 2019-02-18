package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.DrawnLetters;

import java.util.LinkedList;

public interface DrawService {
    DrawnLetters drawLetters(int number);

    void getLettersBack(Character[] letters);
}
