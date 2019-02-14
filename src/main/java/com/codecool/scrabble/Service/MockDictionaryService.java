package com.codecool.scrabble.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MockDictionaryService implements DictionaryService {

    private ArrayList<String> dictionary = new ArrayList<String>();

    public MockDictionaryService() {
        dictionary.add("aa");
        dictionary.add("aaa");
        dictionary.add("aaronowa");
        dictionary.add("baran");
        dictionary.add("aa");
        dictionary.add("źrebię");
        dictionary.add("koziororzec");
        dictionary.add("wodnik");
        dictionary.add("ryby");
        dictionary.add("baran");
        dictionary.add("byk");
        dictionary.add("bliźnięta");
        dictionary.add("rak");
        dictionary.add("lew");
        dictionary.add("panna");
        dictionary.add("waga");
        dictionary.add("skorpion");
        dictionary.add("strzelec");
        dictionary.add("pikieta");
        dictionary.add("wrona");
        dictionary.add("car");
        dictionary.add("bar");
        dictionary.add("ar");
        dictionary.add("kok");
        dictionary.add("kokaina");
        dictionary.add("rata");

    }

    @Override
    public Boolean isWordInDict(String word) {
        return dictionary.contains(word);
    }
}
