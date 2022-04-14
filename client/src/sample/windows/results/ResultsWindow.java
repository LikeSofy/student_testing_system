package sample.windows.results;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.results.Results;
import sample.entity.test.Test;
import sample.windows.results.controller.ResultsController;

import java.io.IOException;

public class ResultsWindow {
    private Stage stage;

    public ResultsWindow(Stage stage) {
        this.stage = stage;
    }

    public void showWindow(Test test, Results results) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/resultView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Results");
        ResultsController controller = loader.getController();
        controller.setStage(stage);
        controller.setResults(results);
        controller.setTest(test);
        Scene scene = new Scene(root, 828, 449);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
