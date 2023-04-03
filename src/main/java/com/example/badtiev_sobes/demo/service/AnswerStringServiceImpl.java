package com.example.badtiev_sobes.demo.service;

import com.example.badtiev_sobes.demo.model.AnswerInputString;
import com.example.badtiev_sobes.demo.model.AnswerStatistics;
import com.example.badtiev_sobes.demo.repository.AnswerStringRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
public class AnswerStringServiceImpl implements AnswerStringService {
    AnswerStringRepository answerStringRepository;

    public AnswerStringServiceImpl(AnswerStringRepository answerStringRepository) {
        this.answerStringRepository = answerStringRepository;
    }

    @Transactional
    @Override
    public List<AnswerInputString> analizator(String inputString) {
        StringBuilder str = new StringBuilder(inputString);

        List<AnswerInputString> listAnswerInputString = new ArrayList<>();
        TreeMap<Character, Integer> userList = new TreeMap<>();
        TreeMap<Character, Integer> userList1 = new TreeMap<>();
        int tempCount = 0;

        int count = 1;


        for (int i = 0; i < str.length(); i++) {
            if (userList.containsKey(str.charAt(i))) {
                tempCount = userList.get(str.charAt(i)) + 1;
                userList.put(str.charAt(i), tempCount);
            } else {
                userList.put(str.charAt(i), 1);
            }
            if (!userList1.containsKey(str.charAt(i))) {
                userList1.put(str.charAt(i), 1);
            }

            if ((i != (str.length() - 1)) && (str.charAt(i) == str.charAt(i + 1))) {
                count++;
                if (userList.get(str.charAt(i)) < count) {

                    userList1.put(str.charAt(i), count);
                }
            } else {
                count = 1;
            }
        }
        for (Character character : userList.navigableKeySet()) {
            listAnswerInputString.add(new AnswerInputString(character, userList.get(character), userList1.get(character)));
            answerStringRepository.save(new AnswerInputString(character, userList.get(character), userList1.get(character)));
        }

        return listAnswerInputString;
    }

    @Override
    public List<AnswerStatistics> analizStatistic() {
        List<AnswerInputString> list = answerStringRepository.findDistinctByLetter();
        List<AnswerStatistics> answerStatisticsList = new ArrayList<>();
        for (AnswerInputString a: list) {

            answerStatisticsList.add(new AnswerStatistics(answerStringRepository.countByLetter(a.getLetter()),
                    answerStringRepository.avgByCount(a.getLetter()),
                    answerStringRepository.avgByLengthOfStringMessage(a.getLetter()),a.getLetter()));

        }
        return answerStatisticsList;
    }
}
