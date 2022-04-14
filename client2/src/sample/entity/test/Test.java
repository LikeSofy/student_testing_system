package sample.entity.test;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Test implements Iterable<Question>, Cloneable{
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

    public int length(){
        return questions.size();
    }

    public List<Question> toListQuestions(){
        return questions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions);
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<Question> questionsClone = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++){
            questionsClone.add((Question) questions.get(i).clone());
        }
        return new Test(questionsClone);
    }

    @Override
    public String toString() {
        return "Test{" +
                "questions=" + questions +
                '}';
    }
}
