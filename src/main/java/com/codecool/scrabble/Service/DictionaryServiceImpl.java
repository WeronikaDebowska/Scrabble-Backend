package com.codecool.scrabble.Service;

import com.codecool.scrabble.DAO.DictionaryRepository;
import com.codecool.scrabble.Model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DictionaryServiceImpl")
public class DictionaryServiceImpl implements DictionaryService {

    private DictionaryRepository dictRepository;

    @Autowired
    public DictionaryServiceImpl(DictionaryRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    @Override
    public Boolean isWordInDict(String term) {
        Word word = dictRepository.findDictionaryByWord(term);
        if (word == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
