package com.example.badtiev_sobes.demo.service;

import com.example.badtiev_sobes.demo.model.AnswerInputString;
import com.example.badtiev_sobes.demo.model.AnswerStatistics;

import java.util.List;

public interface AnswerStringService {
     List<AnswerInputString> analizator(String inputString);
     List<AnswerStatistics> analizStatistic();
}
