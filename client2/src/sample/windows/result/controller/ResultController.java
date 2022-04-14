package sample.windows.result.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.entity.test.Question;
import sample.windows.result.table.AnswerColumn;
import sample.windows.result.table.TableData;

public class ResultController {
    private TableData tableData = new TableData();
    Stage stage;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="resultTable"
    private TableView<AnswerColumn> resultTable; // Value injected by FXMLLoader

    @FXML // fx:id="answerColumn"
    private TableColumn<AnswerColumn, AnswerColumn> answerColumn; // Value injected by FXMLLoader

    @FXML // fx:id="markTextLabel"
    private Label markTextLabel; // Value injected by FXMLLoader

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTableData(List<Question> questions, List<Boolean> rightAnswers){
        int countRight = 0;
        for (Boolean answer : rightAnswers){
            if (answer){
                countRight++;
            }
        }

        double mark = (double) countRight / (double) rightAnswers.size() * 10;
        markTextLabel.setText(Double.toString(mark));

        for (int i = 0; i < questions.size(); i++){
            tableData.add(new AnswerColumn(questions.get(i), rightAnswers.get(i)));
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert resultTable != null : "fx:id=\"resultTable\" was not injected: check your FXML file 'resultView.fxml'.";
        assert answerColumn != null : "fx:id=\"answerColumn\" was not injected: check your FXML file 'resultView.fxml'.";
        assert markTextLabel != null : "fx:id=\"markTextLabel\" was not injected: check your FXML file 'resultView.fxml'.";
        answerColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        answerColumn.setCellFactory(param -> new TableCell<AnswerColumn, AnswerColumn>(){
            @Override
            public void updateItem(AnswerColumn answerColumn, boolean empty) {
                super.updateItem(answerColumn, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Label label = new Label(answerColumn.getQuestion().getTextQuestion());
                    if (answerColumn.isRightAnswers()){
                        label.setTextFill(Color.GREEN);
                    }
                    else{
                        label.setTextFill(Color.RED);
                    }
                    setGraphic(label);
                    setText(null);
                }
            }
        });
        resultTable.setItems(tableData.getTableData());
    }
}

