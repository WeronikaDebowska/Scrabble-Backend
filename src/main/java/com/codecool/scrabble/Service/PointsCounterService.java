package com.codecool.scrabble.Service;

import com.codecool.scrabble.Model.Cell;

import java.util.LinkedList;

public interface PointsCounterService {
    int countWordScore(String word, LinkedList<Cell> newCells);

    void addToRoundScore(int points);

    int getRoundScore();

    void clearScore();
}
