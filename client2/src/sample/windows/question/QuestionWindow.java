package sample.windows.question;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.LoginWindowData;
import sample.entity.test.Question;
import sample.entity.test.Test;
import sample.windows.question.controller.QuestionController;

import java.io.IOException;

public class QuestionWindow {
    private Stage stage;

    public QuestionWindow(Stage stage) {
        this.stage = stage;
    }

    public LoginWindowData showWindow(Test test) throws IOException {
        LoginWindowData loginWindowData = new LoginWindowData();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/questionView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Question");
        QuestionController controller = loader.getController();
        controller.setStage(stage);
        controller.setTest(test);
        Scene scene = new Scene(root, 600,  400);
        stage.setScene(scene);
        stage.showAndWait();

        return loginWindowData;
    }
}
