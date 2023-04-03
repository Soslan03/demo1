package com.example.badtiev_sobes.demo.model;


import jakarta.persistence.*;

@Entity
public class AnswerInputString {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    Character letter;
    Integer countLetters;
    Integer lengthOfStringMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerInputString() {

    }

    public AnswerInputString(Character letter, Integer count, Integer lengthOfStringMessage) {
        this.letter = letter;
        this.countLetters = count;
        this.lengthOfStringMessage = lengthOfStringMessage;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Integer getCountLetters() {
        return countLetters;
    }

    public void setCountLetters(Integer count) {
        this.countLetters = count;
    }

    public Integer getLengthOfStringMessage() {
        return lengthOfStringMessage;
    }

    public void setLengthOfStringMessage(Integer lengthOfStringMessage) {
        this.lengthOfStringMessage = lengthOfStringMessage;
    }
}
