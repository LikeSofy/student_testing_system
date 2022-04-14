package sample.windows.question.controller;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.entity.test.Question;
import sample.entity.test.Test;

public class QuestionController {
    Stage stage;
    Test test;
    int currentQuestion = 0;

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Выход из программы");
                alert.setHeaderText("При выходе из программы результат не будет сохранен");
                alert.setContentText("Выйти?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.exit(0);
                } else {
                    we.consume();
                }
            }
        });
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="questionTextArea"
    private TextArea questionTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="firstQuestionTextField"
    private TextField firstQuestionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="secondQuestionTextField"
    private TextField secondQuestionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="thirdQuestionTextField"
    private TextField thirdQuestionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="fourthQuestionTextField"
    private TextField fourthQuestionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="fifthQuestionTextField"
    private TextField fifthQuestionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="firstQuestionCheckbox"
    private CheckBox firstQuestionCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="secondQuestionCheckbox"
    private CheckBox secondQuestionCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="thirdQuestionCheckbox"
    private CheckBox thirdQuestionCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="fourthQuestionCheckbox"
    private CheckBox fourthQuestionCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="fifthQuestionCheckbox"
    private CheckBox fifthQuestionCheckbox; // Value injected by FXMLLoader

    @FXML
    void nextButtonClicked(MouseEvent event) {
        test.find(currentQuestion - 1).findAnswer(0).setTrue(firstQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(1).setTrue(secondQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(2).setTrue(thirdQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(3).setTrue(fourthQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(4).setTrue(firstQuestionCheckbox.isSelected());
        if (currentQuestion < test.length()){
            setQuestion(test.find(currentQuestion));
            currentQuestion++;
        }
        else {stage.close();}
    }

    public void setTest(Test test){
        this.test = test;
        setQuestion(test.find(currentQuestion));
        currentQuestion++;
    }

    public void setQuestion(Question question){
        for (int i = 0; i < question.lenth(); i++){
            question.findAnswer(i).setTrue(false);
        }
        firstQuestionCheckbox.setSelected(false);
        secondQuestionCheckbox.setSelected(false);
        thirdQuestionCheckbox.setSelected(false);
        fourthQuestionCheckbox.setSelected(false);
        fifthQuestionCheckbox.setSelected(false);
        questionTextArea.setText(question.getTextQuestion());
        firstQuestionTextField.setText(question.findAnswer(0).getText());
        secondQuestionTextField.setText(question.findAnswer(1).getText());
        thirdQuestionTextField.setText(question.findAnswer(2).getText());
        fourthQuestionTextField.setText(question.findAnswer(3).getText());
        fifthQuestionTextField.setText(question.findAnswer(4).getText());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert questionTextArea != null : "fx:id=\"questionTextArea\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert firstQuestionTextField != null : "fx:id=\"firstQuestionTextField\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert secondQuestionTextField != null : "fx:id=\"secondQuestionTextField\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert thirdQuestionTextField != null : "fx:id=\"thirdQuestionTextField\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert fourthQuestionTextField != null : "fx:id=\"fourthQuestionTextField\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert fifthQuestionTextField != null : "fx:id=\"fifthQuestionTextField\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert firstQuestionCheckbox != null : "fx:id=\"firstQuestionCheckbox\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert secondQuestionCheckbox != null : "fx:id=\"secondQuestionCheckbox\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert thirdQuestionCheckbox != null : "fx:id=\"thirdQuestionCheckbox\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert fourthQuestionCheckbox != null : "fx:id=\"fourthQuestionCheckbox\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
        assert fifthQuestionCheckbox != null : "fx:id=\"fifthQuestionCheckbox\" was not injected: check your FXML file 'CreateQuestionView.fxml'.";
    }
}


