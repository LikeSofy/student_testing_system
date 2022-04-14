package sample.windows.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.windows.admin.controller.AdminController;

import java.io.IOException;

public class AdminWindow {
    Stage stage;

    public AdminWindow(Stage stage) {
        this.stage = stage;
    }

    public void showWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(AdminWindow.class.getResource("view/AdminView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Admin");
        AdminController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root, 828, 449);
        stage.setScene(scene);
        stage.show();
    }
}
