/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

package sample.windows.login.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.entity.LoginWindowData;

public class LoginController {

    private Stage stage;
    private LoginWindowData loginWindowData;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="ipTextField"
    private TextField ipTextField; // Value injected by FXMLLoader

    @FXML // fx:id="portTextField"
    private TextField portTextField; // Value injected by FXMLLoader

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void setUserData(LoginWindowData loginWindowData) {
        this.loginWindowData = loginWindowData;
    }

    @FXML
    void connectButtonClicked(MouseEvent event) {
        if (ipTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Input ip address");
            alert.showAndWait();
            return;
        }
        if (usernameTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Input username");
            alert.showAndWait();
            return;
        }
        loginWindowData.setIp(ipTextField.getText());
        loginWindowData.setUsername(usernameTextField.getText());
        loginWindowData.setPort(Integer.parseInt(portTextField.getText()));
        stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'sample.fxml'.";
        assert ipTextField != null : "fx:id=\"ipTextField\" was not injected: check your FXML file 'sample.fxml'.";
        assert portTextField != null : "fx:id=\"portTextField\" was not injected: check your FXML file 'sample.fxml'.";

    }
}