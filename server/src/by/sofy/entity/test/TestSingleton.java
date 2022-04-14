package by.sofy.entity.test;

public class TestSingleton {
    private static TestSingleton instance = new TestSingleton();
    private Test test;

    private TestSingleton(){};

    public static TestSingleton getInstance() {
        return instance;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
