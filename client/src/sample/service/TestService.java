package sample.service;

import sample.entity.test.Test;

import java.util.LinkedList;
import java.util.List;

public class TestService {
    public double getMark (Test origin, Test test){
        List<Boolean> rightAnswers = getRightAnswer (origin, test);
        int numsRight = 0;
        for (Boolean answerRight : rightAnswers){
            if (answerRight){
                numsRight++;
            }
        }

        return ((double) numsRight * 10 / (double) rightAnswers.size());
    }

    public List<Boolean> getRightAnswer (Test origin, Test test){
        List<Boolean> rightAnswers = new LinkedList<Boolean>();
        for (int i = 0; i < origin.length(); i++){
            rightAnswers.add(origin.find(i).equals(test.find(i)));
        }

        return rightAnswers;
    }
}
