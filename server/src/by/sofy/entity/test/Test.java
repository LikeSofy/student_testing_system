package by.sofy.entity.test;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test implements Iterable<Question>{
    @Expose
    private List<Question> questions = new ArrayList<>();

    public Test(List<Question> questions) {
        this.questions = questions;
    }

    public void add (Test test){
        for (Question question : test){
            add(question);
        }
    }

    public void add (Question question){
        questions.add(question);
    }

    public void remove(Question question){
        questions.remove(question);
    }

    public Question find(int i){
        return questions.get(i);
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public String toString() {
        return "Test{" +
                "questions=" + questions +
                '}';
    }
}
