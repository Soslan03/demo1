package com.example.badtiev_sobes.demo.repository;

import com.example.badtiev_sobes.demo.model.AnswerInputString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerStringRepository extends JpaRepository<AnswerInputString, Long> {
  @Query("select distinct letter from AnswerInputString ")
    List<AnswerInputString> findDistinctByLetter();
  @Query("select letter from AnswerInputString group by letter having letter =: letterI")
    Long countByLetter(Character letterI);
  @Query("select AVG(countLetters) from AnswerInputString  group by letter having letter =: letterI1 ")
  Double avgByCount(Character letterI1);
  @Query("select AVG(lengthOfStringMessage) from AnswerInputString  group by letter having letter =: letterI1 ")
  Double avgByLengthOfStringMessage(Character letterI1);
}

