package com.example.badtiev_sobes.demo.model;

public class AnswerStatistics {
    Long countAnswers;
    Double avgAnswers;
    Double avgLength;
    Character letter;

    public AnswerStatistics(Long countAnswers, Double avgAnswers, Double avgLength, Character letter) {
        this.countAnswers = countAnswers;
        this.avgAnswers = avgAnswers;
        this.avgLength = avgLength;
        this.letter = letter;
    }

    public Long getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(Long countAnswers) {
        this.countAnswers = countAnswers;
    }

    public Double getAvgAnswers() {
        return avgAnswers;
    }

    public void setAvgAnswers(Double avgAnswers) {
        this.avgAnswers = avgAnswers;
    }

    public Double getAvgLength() {
        return avgLength;
    }

    public void setAvgLength(Double avgLength) {
        this.avgLength = avgLength;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }
}
