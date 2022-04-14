Листинг исходного кода файла src\sample\entity\command\Command.java

package sample.entity.command;

import com.google.gson.annotations.Expose;

public class Command {
    @Expose
    private CommandEnum typeCommand;
    @Expose
    private String data;

    public Command(CommandEnum typeCommand, String data) {
        this.typeCommand = typeCommand;
        this.data = data;
    }

    public CommandEnum getTypeCommand() {
        return typeCommand;
    }

    public void setTypeCommand(CommandEnum typeCommand) {
        this.typeCommand = typeCommand;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Command{" +
                "typeCommand=" + typeCommand +
                ", data='" + data + '\'' +
                '}';
    }
}


Листинг исходного кода файла src\sample\entity\command\CommandEnum.java

package sample.entity.command;

public enum CommandEnum {
    GET_TEST,
    SEND_RESULT,
    SUCCSESS,
}


Листинг исходного кода файла src\sample\entity\exception\ServerException.java

package sample.entity.exception;

public class ServerException extends Exception {
    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }
}


Листинг исходного кода файла src\sample\entity\result\Result.java

package sample.entity.result;

import com.google.gson.annotations.Expose;
import sample.entity.test.Test;

public class Result {
    @Expose
    private String owner;
    @Expose
    private Test test;

    public Result(String owner, Test test) {
        this.owner = owner;
        this.test = test;
    }
}


Листинг исходного кода файла src\sample\entity\test\Answer.java

package sample.entity.test;

import com.google.gson.annotations.Expose;

public class Answer implements Cloneable{
    @Expose
    private String text = "";
    @Expose
    private boolean isTrue = false;

    public Answer() {
    }

    public Answer(String answer, boolean isTrue) {
        this.text = answer;
        this.isTrue = isTrue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (isTrue != answer.isTrue) return false;
        return text != null ? text.equals(answer.text) : answer.text == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (isTrue ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + text + '\'' +
                ", isTrue=" + isTrue +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Answer(text, isTrue);
    }
}


Листинг исходного кода файла src\sample\entity\test\Question.java

package sample.entity.test;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

public class Question implements Cloneable{
    @Expose
    private String textQuestion = "";
    @Expose
    private Answer[] answers = new Answer[5];

    {
        for (int i = 0; i < answers.length; i++){
            answers[i] = new Answer();
        }
    }

    public Question() {
    }

    public Question(String textQuestion, Answer[] answers) {
        this.textQuestion = textQuestion;
        this.answers = answers;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public Answer findAnswer(int i) {
        return answers[i];
    }

    public void setAnswer(int i, Answer answer) {
        this.answers[i] = answer;
    }

    public int lenth(){
        return answers.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (textQuestion != null ? !textQuestion.equals(question.textQuestion) : question.textQuestion != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        int result = textQuestion != null ? textQuestion.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(answers);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Answer[] answersCopy = new Answer[answers.length];
        for (int i = 0; i < answers.length; i++){
            answersCopy[i] = (Answer) answers[i].clone();
        }
        return new Question(textQuestion, answersCopy);
    }

    @Override
    public String toString() {
        return "Question{" +
                "textQuestion='" + textQuestion + '\'' +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}


Листинг исходного кода файла src\sample\entity\test\Test.java

package sample.entity.test;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Test implements Iterable<Question>, Cloneable{
    @Expose
    private List<Question> questions = new ArrayList<>();

    public Test(List<Question> questions) {
        this.questions = questions;
    }

    public void add (Test test){
        for (Question question : test){
            add(question);
        }
    }

    public void add (Question question){
        questions.add(question);
    }

    public void remove(Question question){
        questions.remove(question);
    }

    public Question find(int i){
        return questions.get(i);
    }

    public int length(){
        return questions.size();
    }

    public List<Question> toListQuestions(){
        return questions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions);
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<Question> questionsClone = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++){
            questionsClone.add((Question) questions.get(i).clone());
        }
        return new Test(questionsClone);
    }

    @Override
    public String toString() {
        return "Test{" +
                "questions=" + questions +
                '}';
    }
}


Листинг исходного кода файла src\sample\entity\LoginWindowData.java

package sample.entity;

import java.util.Objects;

public class LoginWindowData {
    private String username;
    private String ip;
    private int port;

    public LoginWindowData() {
    }

    public LoginWindowData(String username, String ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginWindowData loginWindowData = (LoginWindowData) o;
        return port == loginWindowData.port && Objects.equals(username, loginWindowData.username) && Objects.equals(ip, loginWindowData.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, ip, port);
    }
}


Листинг исходного кода файла src\sample\entity\UdpDao.java

package sample.entity;

import com.google.gson.Gson;
import sample.entity.command.Command;
import sample.entity.command.CommandEnum;
import sample.entity.result.Result;
import sample.entity.test.Test;
import sample.utils.udp_connection.UdpConnection;

import java.io.IOException;
import java.rmi.ServerException;

public class UdpDao {
    private String ip;
    private int port;
    private Gson gson = new Gson();

    public UdpDao(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Test getTest() throws IOException {
        Command command = new Command(CommandEnum.GET_TEST, "");
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.GET_TEST){
            throw new ServerException("Incorect returned data");
        }

        if (returnCommand.getData().equals("null")){
            throw new ServerException("Test not upload");
        }

        Test test = gson.fromJson(returnCommand.getData(), Test.class);

        return test;
    }

    public void sendResult(Result result) throws IOException {
        String json = gson.toJson(result);
        Command command = new Command(CommandEnum.SEND_RESULT, json);
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.SUCCSESS){
            throw new ServerException("Incorect returned data");
        }

        if (! returnCommand.getData().equals("ok")){
            throw new ServerException("Test not upload");
        }
    }

    public Command send(Command command) throws IOException {
        UdpConnection connection = new UdpConnection();
        String send = gson.toJson(command);
        String recive = connection.sendData(send, ip, port);
        Command reciveCommand = gson.fromJson(recive, Command.class);

        return reciveCommand;
    }
}


Листинг исходного кода файла src\sample\utils\comparator\TestService.java

package sample.utils.comparator;

import sample.entity.test.Test;

import java.util.LinkedList;
import java.util.List;

public class TestService {
    public static List<Boolean> Compare (Test origin, Test test){
        List<Boolean> rightAnswers = new LinkedList<>();
        for (int i = 0; i < origin.length(); i++){
            rightAnswers.add(origin.find(i).equals(test.find(i)));
        }

        return rightAnswers;
    }
}


Листинг исходного кода файла src\sample\utils\udp_connection\UdpConnection.java

package sample.utils.udp_connection;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpConnection {
    public String sendData(String data, String ip, int port) throws IOException {
        /* РЎРѕР·РґР°Р№С‚Рµ СЌРєР·РµРјРїР»СЏСЂ РєР»РёРµРЅС‚СЃРєРѕРіРѕ СЃРѕРєРµС‚Р°.
        РќРµС‚ РЅРµРѕР±С…РѕРґРёРјРѕСЃС‚Рё РІ РїСЂРёРІСЏР·РєРµ Рє РѕРїСЂРµРґРµР»РµРЅРЅРѕРјСѓ РїРѕСЂС‚Сѓ */
        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(1000);

        // РџРѕР»СѓС‡РёС‚Рµ IP-Р°РґСЂРµСЃ СЃРµСЂРІРµСЂР°
        InetAddress IPAddress = InetAddress.getByName(ip);

        // РЎРѕР·РґР°Р№С‚Рµ СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРµ Р±СѓС„РµСЂС‹
        byte[] sendingDataBuffer = new byte[102400];
        byte[] receivingDataBuffer = new byte[102400];

        /* РџСЂРµРѕР±СЂР°Р·СѓР№С‚Рµ РґР°РЅРЅС‹Рµ РІ Р±Р°Р№С‚С‹
          Рё СЂР°Р·РјРµСЃС‚РёС‚Рµ РІ Р±СѓС„РµСЂР°С… */
        sendingDataBuffer = data.getBytes(StandardCharsets.UTF_8);

        // РЎРѕР·РґР°Р№С‚Рµ UDP-РїР°РєРµС‚
        DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,sendingDataBuffer.length,IPAddress, port);

        // РћС‚РїСЂР°РІСЊС‚Рµ UDP-РїР°РєРµС‚ СЃРµСЂРІРµСЂСѓ
        clientSocket.send(sendingPacket);

        // РџРѕР»СѓС‡РёС‚Рµ РѕС‚РІРµС‚ РѕС‚ СЃРµСЂРІРµСЂР°, С‚.Рµ. РїСЂРµРґР»РѕР¶РµРЅРёРµ РёР· Р·Р°РіР»Р°РІРЅС‹С… Р±СѓРєРІ
        DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,receivingDataBuffer.length);
        clientSocket.receive(receivingPacket);

        // Р’С‹РІРµРґРёС‚Рµ РЅР° СЌРєСЂР°РЅРµ РїРѕР»СѓС‡РµРЅРЅС‹Рµ РґР°РЅРЅС‹Рµ
        String receivedData = new String(receivingPacket.getData());
        receivedData = receivedData.trim();

        // Р—Р°РєСЂРѕР№С‚Рµ СЃРѕРµРґРёРЅРµРЅРёРµ СЃ СЃРµСЂРІРµСЂРѕРј С‡РµСЂРµР· СЃРѕРєРµС‚
        clientSocket.close();

        return receivedData;
    }
}

Листинг исходного кода файла src\sample\windows\login\controller\LoginController.java

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

Листинг исходного кода файла src\sample\windows\login\LoginWindow.java

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


Листинг исходного кода файла src\sample\windows\question\controller\QuestionController.java

package sample.windows.question.controller;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.entity.test.Question;
import sample.entity.test.Test;

public class QuestionController {
    Stage stage;
    Test test;
    int currentQuestion = 0;

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Р’С‹С…РѕРґ РёР· РїСЂРѕРіСЂР°РјРјС‹");
                alert.setHeaderText("РџСЂРё РІС‹С…РѕРґРµ РёР· РїСЂРѕРіСЂР°РјРјС‹ СЂРµР·СѓР»СЊС‚Р°С‚ РЅРµ Р±СѓРґРµС‚ СЃРѕС…СЂР°РЅРµРЅ");
                alert.setContentText("Р’С‹Р№С‚Рё?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.exit(0);
                } else {
                    we.consume();
                }
            }
        });
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
    void nextButtonClicked(MouseEvent event) {
        test.find(currentQuestion - 1).findAnswer(0).setTrue(firstQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(1).setTrue(secondQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(2).setTrue(thirdQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(3).setTrue(fourthQuestionCheckbox.isSelected());
        test.find(currentQuestion - 1).findAnswer(4).setTrue(firstQuestionCheckbox.isSelected());
        if (currentQuestion < test.length()){
            setQuestion(test.find(currentQuestion));
            currentQuestion++;
        }
        else {stage.close();}
    }

    public void setTest(Test test){
        this.test = test;
        setQuestion(test.find(currentQuestion));
        currentQuestion++;
    }

    public void setQuestion(Question question){
        for (int i = 0; i < question.lenth(); i++){
            question.findAnswer(i).setTrue(false);
        }
        firstQuestionCheckbox.setSelected(false);
        secondQuestionCheckbox.setSelected(false);
        thirdQuestionCheckbox.setSelected(false);
        fourthQuestionCheckbox.setSelected(false);
        fifthQuestionCheckbox.setSelected(false);
        questionTextArea.setText(question.getTextQuestion());
        firstQuestionTextField.setText(question.findAnswer(0).getText());
        secondQuestionTextField.setText(question.findAnswer(1).getText());
        thirdQuestionTextField.setText(question.findAnswer(2).getText());
        fourthQuestionTextField.setText(question.findAnswer(3).getText());
        fifthQuestionTextField.setText(question.findAnswer(4).getText());
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




Листинг исходного кода файла src\sample\windows\question\QuestionWindow.java

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


Листинг исходного кода файла src\sample\windows\result\controller\ResultController.java

package sample.windows.result.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.entity.test.Question;
import sample.windows.result.table.AnswerColumn;
import sample.windows.result.table.TableData;

public class ResultController {
    private TableData tableData = new TableData();
    Stage stage;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="resultTable"
    private TableView<AnswerColumn> resultTable; // Value injected by FXMLLoader

    @FXML // fx:id="answerColumn"
    private TableColumn<AnswerColumn, AnswerColumn> answerColumn; // Value injected by FXMLLoader

    @FXML // fx:id="markTextLabel"
    private Label markTextLabel; // Value injected by FXMLLoader

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTableData(List<Question> questions, List<Boolean> rightAnswers){
        int countRight = 0;
        for (Boolean answer : rightAnswers){
            if (answer){
                countRight++;
            }
        }

        double mark = (double) countRight / (double) rightAnswers.size() * 10;
        markTextLabel.setText(Double.toString(mark));

        for (int i = 0; i < questions.size(); i++){
            tableData.add(new AnswerColumn(questions.get(i), rightAnswers.get(i)));
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert resultTable != null : "fx:id=\"resultTable\" was not injected: check your FXML file 'resultView.fxml'.";
        assert answerColumn != null : "fx:id=\"answerColumn\" was not injected: check your FXML file 'resultView.fxml'.";
        assert markTextLabel != null : "fx:id=\"markTextLabel\" was not injected: check your FXML file 'resultView.fxml'.";
        answerColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        answerColumn.setCellFactory(param -> new TableCell<AnswerColumn, AnswerColumn>(){
            @Override
            public void updateItem(AnswerColumn answerColumn, boolean empty) {
                super.updateItem(answerColumn, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Label label = new Label(answerColumn.getQuestion().getTextQuestion());
                    if (answerColumn.isRightAnswers()){
                        label.setTextFill(Color.GREEN);
                    }
                    else{
                        label.setTextFill(Color.RED);
                    }
                    setGraphic(label);
                    setText(null);
                }
            }
        });
        resultTable.setItems(tableData.getTableData());
    }
}



Листинг исходного кода файла src\sample\windows\result\table\AnswerColumn.java

package sample.windows.result.table;

import sample.entity.test.Question;

public class AnswerColumn {
    private Question question;
    private boolean  rightAnswers;

    public AnswerColumn(Question question, boolean rightAnswers) {
        this.question = question;
        this.rightAnswers = rightAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(boolean rightAnswers) {
        this.rightAnswers = rightAnswers;
    }
}


Листинг исходного кода файла src\sample\windows\result\table\TableData.java

package sample.windows.result.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;

public class TableData {
    ObservableList<AnswerColumn> tableData = FXCollections.observableList(new LinkedList<>());

    public ObservableList<AnswerColumn> getTableData(){
        return tableData;
    }

    public void add(AnswerColumn answerColumn){
        tableData.add(answerColumn);
    }

    public AnswerColumn find(int i){
        return tableData.get(i);
    }
}


Листинг исходного кода файла src\sample\windows\result\ResultWindow.java

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


Листинг исходного кода файла src\sample\Main.java

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


