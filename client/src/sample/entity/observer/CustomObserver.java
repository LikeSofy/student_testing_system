package sample.entity.observer;

public interface CustomObserver {
    void addObject(CustomQuestionEvent event);

    void removeObject(CustomQuestionEvent event);
}
