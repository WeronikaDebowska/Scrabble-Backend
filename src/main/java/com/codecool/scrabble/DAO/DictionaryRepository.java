package com.codecool.scrabble.DAO;

import com.codecool.scrabble.Model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Word, Long> {

    Word findDictionaryByWord(String word);

}
