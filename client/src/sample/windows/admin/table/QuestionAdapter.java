package sample.windows.admin.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import sample.entity.test.Question;


public class QuestionAdapter {
    private Question question;
    private Button editButton = new Button("Edit");
    private Button deleteButton = new Button("Delete");

    public QuestionAdapter(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public SimpleStringProperty textQuestionProperty() {
        return new SimpleStringProperty(question.getTextQuestion());
    }
}
