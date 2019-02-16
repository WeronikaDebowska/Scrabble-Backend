package com.codecool.scrabble.Model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class ResponseAfterMove {

    private boolean isMoveValid;
    private LinkedList<WordDetails> wordsDetails = new LinkedList<>();
    private LinkedList<String> messages = new LinkedList<>();
    private String user;
    private int totalScore;
    private int roundScore;
    private Board actualBoard;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void addWord(WordDetails word) {
        wordsDetails.add(word);
    }


    public Board getActualBoard() {
        return actualBoard;
    }

    public void setActualBoard(Board acctualBoard) {
        this.actualBoard = acctualBoard;
    }

    public LinkedList<WordDetails> getWordsDetails() {
        return wordsDetails;
    }

    public void setWordsDetails(LinkedList<WordDetails> wordsDetails) {
        this.wordsDetails = wordsDetails;
    }

    public LinkedList<String> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<String> messages) {
        this.messages = messages;
    }

    public boolean setValidity() {
        for (WordDetails wordDetails : wordsDetails) {
            if (!wordDetails.isValid()) {
                isMoveValid = false;
                setRoundScore(0);
                return false;
            }
        }
        isMoveValid = true;
        return true;
    }

    public void increaseRoundScore(int points) {
        roundScore += points;
    }


}
