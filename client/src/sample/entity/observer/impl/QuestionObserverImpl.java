package sample.entity.observer.impl;

import sample.entity.test.Question;
import sample.entity.observer.CustomObserver;
import sample.entity.observer.CustomQuestionEvent;
import sample.windows.admin.table.QuestionAdapter;
import sample.windows.admin.table.TableData;

public class QuestionObserverImpl implements CustomObserver {
    private TableData tableData;

    public QuestionObserverImpl(TableData tableData) {
        this.tableData = tableData;
    }

    @Override
    public void addObject(CustomQuestionEvent event) {
        tableData.add(new QuestionAdapter((Question)event.getSource()));
    }

    @Override
    public void removeObject(CustomQuestionEvent event) {
        tableData.remove((Question)event.getSource());
    }

}
