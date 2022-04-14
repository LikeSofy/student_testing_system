package sample.entity.results;

import java.util.LinkedList;
import java.util.List;

public class Results {
    private List<Result> results = new LinkedList<Result>();

    public Results() {}

    public void add(Result result){
        results.add(result);
    }

    public Result get(int i){
        return results.get(i);
    }

    public List<Result> toList(){
        return  results;
    }

}
