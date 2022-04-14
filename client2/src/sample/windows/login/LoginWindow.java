package sample.windows.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.LoginWindowData;
import sample.windows.login.controller.LoginController;

import java.io.IOException;

public class LoginWindow {
    private Stage stage;

    public LoginWindow(Stage stage) {
        this.stage = stage;
    }

    public LoginWindowData showWindow() throws IOException {
        LoginWindowData loginWindowData = new LoginWindowData();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/loginView.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login");
        LoginController controller = loader.getController();
        controller.setStage(stage);
        controller.setUserData(loginWindowData);
        Scene scene = new Scene(root, 339,  162);
        stage.setScene(scene);
        stage.showAndWait();

        return loginWindowData;
    }
}
