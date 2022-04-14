package by.sofy.entity.result;

import by.sofy.entity.test.Test;

public class ResultsSingleton {
    private static Results instance = new Results();

    private ResultsSingleton(){};

    public static Results getInstance() {
        return instance;
    }
}
