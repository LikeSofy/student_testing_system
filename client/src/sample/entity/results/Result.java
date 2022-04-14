package sample.entity.results;


import sample.entity.test.Test;

public class Result {
    private String owner;
    private Test test;

    public Result(String owner, Test test) {
        this.owner = owner;
        this.test = test;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }


}
