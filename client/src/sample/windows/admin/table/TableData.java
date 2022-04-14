package sample.windows.admin.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.test.Question;

import java.util.ArrayList;

public class TableData {
    ObservableList<QuestionAdapter> tableData = FXCollections.observableList(new ArrayList<>());

    public TableData() {
    }

    public ObservableList<QuestionAdapter> getTableData() {
        return tableData;
    }

    public void add(QuestionAdapter questionAdapter){
        tableData.add(questionAdapter);
    }

    public void remove(Question question){
        for (QuestionAdapter questionAdapter : tableData){
            if (questionAdapter.getQuestion() == question){
                tableData.remove(questionAdapter);
                break;
            }
        }
    }

    public QuestionAdapter find(int id){
        return tableData.get(id);
    }
}
