package sample.entity.test;

import com.google.gson.annotations.Expose;
import sample.entity.observer.CustomObserver;
import sample.entity.observer.CustomQuestionEvent;
import sample.entity.observer.EventEnum;
import sample.entity.observer.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test implements Observable, Iterable<Question>{
    @Expose
    private List<Question> questions = new ArrayList<>();
    private transient List<CustomObserver> observers = new ArrayList<>();

    public Test(CustomObserver observer) {
        attach(observer);
    }

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
        notifyObserver(EventEnum.ADD_OBJECT, question);
    }

    public void remove(Question question){
        questions.remove(question);
        notifyObserver(EventEnum.REMOVE_OBJECT, question);
    }

    public Question find(int i){
        return questions.get(i);
    }

    public int length(){
        return questions.size();
    }

    @Override
    public void attach(CustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(CustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(EventEnum type, Question question) {
        CustomQuestionEvent event = new CustomQuestionEvent(question);
        for (CustomObserver observer : observers){
            if (type == EventEnum.ADD_OBJECT){
                observer.addObject(event);
            }
            else if (type == EventEnum.REMOVE_OBJECT){
                observer.removeObject(event);
            }
        }
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public String toString() {
        return "Test{" +
                "questions=" + questions +
                ", observers=" + observers +
                '}';
    }
}
