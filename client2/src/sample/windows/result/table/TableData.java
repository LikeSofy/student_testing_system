package sample.windows.result.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;

public class TableData {
    ObservableList<AnswerColumn> tableData = FXCollections.observableList(new LinkedList<>());

    public ObservableList<AnswerColumn> getTableData(){
        return tableData;
    }

    public void add(AnswerColumn answerColumn){
        tableData.add(answerColumn);
    }

    public AnswerColumn find(int i){
        return tableData.get(i);
    }
}
