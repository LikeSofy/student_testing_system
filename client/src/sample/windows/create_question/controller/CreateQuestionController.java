package sample.windows.create_question.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.entity.test.Question;

public class CreateQuestionController {

    private Stage stage;
    private Question question;

    public void setStage(Stage stage) {
        this.stage = stage;
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
    void endButtonClicked(MouseEvent event) {

    }

    public void setQuestion(Question question){
        this.question = question;
        questionTextArea.setText(question.getTextQuestion());
        questionTextArea.textProperty().addListener((observable, oldValue, newValue)->{
            question.setTextQuestion(newValue);
        });
        firstQuestionTextField.setText(question.findAnswer(0).getText());
        firstQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(0).setText(newValue);
        });
        firstQuestionCheckbox.setSelected(question.findAnswer(0).isTrue());
        firstQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(0).setTrue(newValue);
        });
        secondQuestionTextField.setText(question.findAnswer(1).getText());
        secondQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(1).setText(newValue);
        });
        secondQuestionCheckbox.setSelected(question.findAnswer(1).isTrue());
        secondQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(1).setTrue(newValue);
        });
        thirdQuestionTextField.setText(question.findAnswer(2).getText());
        thirdQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(2).setText(newValue);
        });
        thirdQuestionCheckbox.setSelected(question.findAnswer(2).isTrue());
        thirdQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(2).setTrue(newValue);
        });
        fourthQuestionTextField.setText(question.findAnswer(3).getText());
        fourthQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(3).setText(newValue);
        });
        fourthQuestionCheckbox.setSelected(question.findAnswer(3).isTrue());
        fourthQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(3).setTrue(newValue);
        });
        fifthQuestionTextField.setText(question.findAnswer(4).getText());
        fifthQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(4).setText(newValue);
        });
        firstQuestionCheckbox.setSelected(question.findAnswer(4).isTrue());
        firstQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(4).setTrue(newValue);
        });
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

