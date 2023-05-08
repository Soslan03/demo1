package com.example.badtiev_sobes.demo.controllers;

import com.example.badtiev_sobes.demo.model.AnswerInputString;
import com.example.badtiev_sobes.demo.model.AnswerStatistics;
import com.example.badtiev_sobes.demo.service.AnswerStringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MyRestController {
    AnswerStringService answerStringService;

    public MyRestController(AnswerStringService answerStringService) {
        this.answerStringService = answerStringService;
    }

    @GetMapping("/analyze/{input_string}")
    public ResponseEntity<List<AnswerInputString>> showAnaliz(@PathVariable String input_string) {
            List<AnswerInputString> answerInputString = answerStringService.analizator(input_string);


        return  answerInputString != null && !answerInputString.isEmpty()
                ? new ResponseEntity<>(answerInputString, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/statistics")
    public ResponseEntity<List<AnswerStatistics>> showStatistics() {
        List<AnswerStatistics> answerStatistics = answerStringService.analizStatistic();


        return  answerStatistics != null && !answerStatistics.isEmpty()
                ? new ResponseEntity<>(answerStatistics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
