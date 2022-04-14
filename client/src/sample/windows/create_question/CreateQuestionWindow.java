package sample.windows.create_question;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.test.Question;
import sample.windows.create_question.controller.CreateQuestionController;

import java.io.IOException;

public class CreateQuestionWindow {
    private Stage stage;

    public CreateQuestionWindow(Stage stage) {
        this.stage = stage;
    }

    public Question showWindow(Question question) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/CreateQuestionView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Question");
        CreateQuestionController controller = loader.getController();
        controller.setStage(stage);
        controller.setQuestion(question);
        Scene scene = new Scene(root, 600,  400);
        stage.setScene(scene);
        stage.showAndWait();

        return question;
    }

    public Question showWindow() throws IOException {
        Question question = new Question();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/CreateQuestionView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Question");
        CreateQuestionController controller = loader.getController();
        controller.setStage(stage);
        controller.setQuestion(question);
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.showAndWait();

        return question;
    }
}
