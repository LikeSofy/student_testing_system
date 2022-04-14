package sample;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.entity.*;
import sample.entity.result.Result;
import sample.entity.test.Question;
import sample.entity.test.Test;
import sample.utils.comparator.TestService;
import sample.windows.login.LoginWindow;
import sample.windows.question.QuestionWindow;
import sample.windows.result.ResultWindow;

import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Test test;
        Test origin;
        UdpDao udpDao;
        LoginWindowData loginWindowData;
        while (true){
            LoginWindow loginWindow = new LoginWindow(new Stage());
            loginWindowData = loginWindow.showWindow();

            udpDao = new UdpDao(loginWindowData.getIp(), loginWindowData.getPort());

            try {
                test = udpDao.getTest();
                origin = (Test) test.clone();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(e.getMessage());
                alert.setContentText(e.toString());
                alert.showAndWait();
                continue;
            }

            break;
        }
        QuestionWindow questionWindow = new QuestionWindow(new Stage());
        questionWindow.showWindow(test);

        Result result = new Result(loginWindowData.getUsername(), test);

        try {
            udpDao.sendResult(result);
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(e.toString());
            alert.showAndWait();
        }

        List<Boolean> rightAnswers = TestService.Compare(origin, test);
        List<Question> questions = origin.toListQuestions();

        ResultWindow resultWindow = new ResultWindow(new Stage());
        resultWindow.showWindow(questions, rightAnswers);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
