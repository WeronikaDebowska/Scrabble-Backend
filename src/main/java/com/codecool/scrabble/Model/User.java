package com.codecool.scrabble.Model;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String userName;
    private int totalScore;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }


}
