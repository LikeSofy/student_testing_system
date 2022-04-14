package sample.entity.result;

import com.google.gson.annotations.Expose;
import sample.entity.test.Test;

public class Result {
    @Expose
    private String owner;
    @Expose
    private Test test;

    public Result(String owner, Test test) {
        this.owner = owner;
        this.test = test;
    }
}
