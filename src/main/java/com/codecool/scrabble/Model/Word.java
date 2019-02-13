package com.codecool.scrabble.Model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "Dictionary")
public class Word {

    @Id
    private Long id;
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

