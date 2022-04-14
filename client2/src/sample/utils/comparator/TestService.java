package sample.utils.comparator;

import sample.entity.test.Test;

import java.util.LinkedList;
import java.util.List;

public class TestService {
    public static List<Boolean> Compare (Test origin, Test test){
        List<Boolean> rightAnswers = new LinkedList<>();
        for (int i = 0; i < origin.length(); i++){
            rightAnswers.add(origin.find(i).equals(test.find(i)));
        }

        return rightAnswers;
    }
}
