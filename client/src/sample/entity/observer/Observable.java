package sample.entity.observer;

import sample.entity.test.Question;

public interface Observable {
    void attach(CustomObserver observer);

    void detach(CustomObserver observer);

    void notifyObserver(EventEnum type, Question question);
}
