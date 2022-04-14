package sample.windows.result;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.LoginWindowData;
import sample.entity.test.Question;
import sample.windows.result.controller.ResultController;

import java.io.IOException;
import java.util.List;

public class ResultWindow {
    private Stage stage;

    public ResultWindow(Stage stage) {
        this.stage = stage;
    }

    public LoginWindowData showWindow(List<Question> questions, List<Boolean> rightAnswers) throws IOException {
        LoginWindowData loginWindowData = new LoginWindowData();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/resultView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Result");
        ResultController controller = loader.getController();
        controller.setStage(stage);
        controller.setTableData(questions, rightAnswers);
        Scene scene = new Scene(root, 600,  448.0);
        stage.setScene(scene);
        stage.showAndWait();

        return loginWindowData;
    }
}
