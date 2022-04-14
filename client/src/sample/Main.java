package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.windows.admin.AdminWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AdminWindow adminWindow = new AdminWindow(primaryStage);
        adminWindow.showWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
