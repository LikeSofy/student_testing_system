Листинг исходного кода файла src\sample\entity\exceptions\ServerException.java

package sample.entity.exceptions;

public class ServerException extends Exception{
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


Листинг исходного кода файла src\sample\entity\observer\impl\QuestionObserverImpl.java

package sample.entity.observer.impl;

import sample.entity.test.Question;
import sample.entity.observer.CustomObserver;
import sample.entity.observer.CustomQuestionEvent;
import sample.windows.admin.table.QuestionAdapter;
import sample.windows.admin.table.TableData;

public class QuestionObserverImpl implements CustomObserver {
    private TableData tableData;

    public QuestionObserverImpl(TableData tableData) {
        this.tableData = tableData;
    }

    @Override
    public void addObject(CustomQuestionEvent event) {
        tableData.add(new QuestionAdapter((Question)event.getSource()));
    }

    @Override
    public void removeObject(CustomQuestionEvent event) {
        tableData.remove((Question)event.getSource());
    }

}


Листинг исходного кода файла src\sample\entity\observer\CustomObserver.java

package sample.entity.observer;

public interface CustomObserver {
    void addObject(CustomQuestionEvent event);

    void removeObject(CustomQuestionEvent event);
}


Листинг исходного кода файла src\sample\entity\observer\CustomQuestionEvent.java

package sample.entity.observer;

import java.util.EventObject;

public class CustomQuestionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    public CustomQuestionEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }
}




Листинг исходного кода файла src\sample\entity\observer\EventEnum.java

package sample.entity.observer;

public enum EventEnum {
    ADD_OBJECT,
    REMOVE_OBJECT,
    PARAMETR_CHANGED
}


Листинг исходного кода файла src\sample\entity\observer\Observable.java

package sample.entity.observer;

import sample.entity.test.Question;

public interface Observable {
    void attach(CustomObserver observer);

    void detach(CustomObserver observer);

    void notifyObserver(EventEnum type, Question question);
}


Листинг исходного кода файла src\sample\entity\results\Result.java

package sample.entity.results;


import sample.entity.test.Test;

public class Result {
    private String owner;
    private Test test;

    public Result(String owner, Test test) {
        this.owner = owner;
        this.test = test;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }


}


Листинг исходного кода файла src\sample\entity\results\Results.java

package sample.entity.results;

import java.util.LinkedList;
import java.util.List;

public class Results {
    private List<Result> results = new LinkedList<Result>();

    public Results() {}

    public void add(Result result){
        results.add(result);
    }

    public Result get(int i){
        return results.get(i);
    }

    public List<Result> toList(){
        return  results;
    }

}


Листинг исходного кода файла src\sample\entity\send_commands\Command.java

package sample.entity.send_commands;

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
}


Листинг исходного кода файла src\sample\entity\send_commands\CommandEnum.java

package sample.entity.send_commands;

public enum CommandEnum {
    SEND_TEST,
    GET_RESULTS,
    SUCCSESS,
}


Листинг исходного кода файла src\sample\entity\test\Answer.java

package sample.entity.test;

import com.google.gson.annotations.Expose;

public class Answer {
    @Expose
    private String text = "";
    @Expose
    private boolean isTrue = false;

    public Answer() {
    }

    public Answer(String text, boolean isTrue) {
        this.text = text;
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
                "text='" + text + '\'' +
                ", isTrue=" + isTrue +
                '}';
    }
}


Листинг исходного кода файла src\sample\entity\test\Question.java

package sample.entity.test;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

public class Question {
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
import sample.entity.observer.CustomObserver;
import sample.entity.observer.CustomQuestionEvent;
import sample.entity.observer.EventEnum;
import sample.entity.observer.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test implements Observable, Iterable<Question>{
    @Expose
    private List<Question> questions = new ArrayList<>();
    private transient List<CustomObserver> observers = new ArrayList<>();

    public Test(CustomObserver observer) {
        attach(observer);
    }

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
        notifyObserver(EventEnum.ADD_OBJECT, question);
    }

    public void remove(Question question){
        questions.remove(question);
        notifyObserver(EventEnum.REMOVE_OBJECT, question);
    }

    public Question find(int i){
        return questions.get(i);
    }

    public int length(){
        return questions.size();
    }

    @Override
    public void attach(CustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(CustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(EventEnum type, Question question) {
        CustomQuestionEvent event = new CustomQuestionEvent(question);
        for (CustomObserver observer : observers){
            if (type == EventEnum.ADD_OBJECT){
                observer.addObject(event);
            }
            else if (type == EventEnum.REMOVE_OBJECT){
                observer.removeObject(event);
            }
        }
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public String toString() {
        return "Test{" +
                "questions=" + questions +
                ", observers=" + observers +
                '}';
    }
}


Листинг исходного кода файла src\sample\entity\UdpDao.java

package sample.entity;

import com.google.gson.Gson;
import sample.entity.results.Results;
import sample.entity.send_commands.Command;
import sample.entity.send_commands.CommandEnum;
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

    public void sendTest(Test test) throws IOException {
        Command command = new Command(CommandEnum.SEND_TEST, gson.toJson(test));
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.SUCCSESS){
            throw new ServerException("Incorect returned data");
        }

        if (! returnCommand.getData().equals("ok")){
            throw new ServerException("Test not upload");
        }
    }

    public Results getResults() throws IOException {
        Command command = new Command(CommandEnum.GET_RESULTS, "");
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.GET_RESULTS){
            throw new ServerException("Incorect returned data");
        }

        return (gson.fromJson(returnCommand.getData(), Results.class));
    }

    public Command send(Command command) throws IOException {
        UdpConnection connection = new UdpConnection();
        String send = gson.toJson(command);
        String recive = connection.sendData(send, ip, port);

        return (gson.fromJson(recive, Command.class));
    }
}


Листинг исходного кода файла src\sample\service\TestService.java

package sample.service;

import sample.entity.test.Test;

import java.util.LinkedList;
import java.util.List;

public class TestService {
    public double getMark (Test origin, Test test){
        List<Boolean> rightAnswers = getRightAnswer (origin, test);
        int numsRight = 0;
        for (Boolean answerRight : rightAnswers){
            if (answerRight){
                numsRight++;
            }
        }

        return ((double) numsRight * 10 / (double) rightAnswers.size());
    }

    public List<Boolean> getRightAnswer (Test origin, Test test){
        List<Boolean> rightAnswers = new LinkedList<Boolean>();
        for (int i = 0; i < origin.length(); i++){
            rightAnswers.add(origin.find(i).equals(test.find(i)));
        }

        return rightAnswers;
    }
}


Листинг исходного кода файла src\sample\utils\reader\TestReader.java

package sample.utils.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.entity.test.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestReader {
    public final static String[] SUPPORTED_FORMAT = new String[] { "*.json"};
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Test open(File file) throws IOException {
        FileReader reader = new FileReader(file);
        String result = "";
        int c;
        while((c=reader.read())!=-1){
            result += (char) c;
        }
        Test test = gson.fromJson(result, Test.class);
        return test;
    }
}


Листинг исходного кода файла src\sample\utils\saver\TestSaver.java

package sample.utils.saver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.entity.test.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestSaver {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public void save(Test test, File file) throws IOException {
        String result = gson.toJson(test);
        FileWriter writer = new FileWriter(file, false);
        writer.write(result);
        writer.flush();
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

Листинг исходного кода файла src\sample\windows\admin\controller\AdminController.java

package sample.windows.admin.controller;

import com.google.gson.Gson;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.entity.results.Results;
import sample.entity.test.Question;
import sample.entity.test.Test;
import sample.entity.UdpDao;
import sample.entity.observer.impl.QuestionObserverImpl;
import sample.utils.reader.TestReader;
import sample.utils.saver.TestSaver;
import sample.windows.admin.table.QuestionAdapter;
import sample.windows.admin.table.TableData;
import sample.windows.create_question.CreateQuestionWindow;
import sample.windows.results.ResultsWindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {
    private static final String SUPPORTED_FILES = "Supported files";
    private static final String SELECT_FILE_TO_LOAD = "Select file to load";
    UdpDao dao;
    Gson gson = new Gson();


    private Stage stage;
    private TableData tableData = new TableData();
    private Test test = new Test(new QuestionObserverImpl(tableData));

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField portTextField;

    @FXML // fx:id="table"
    private TableView<QuestionAdapter> table; // Value injected by FXMLLoader

    @FXML // fx:id="questionColumn"
    private TableColumn<QuestionAdapter, String> questionColumn; // Value injected by FXMLLoader

    @FXML // fx:id="editColumn"
    private TableColumn<QuestionAdapter, QuestionAdapter> editColumn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteColumn"
    private TableColumn<QuestionAdapter, QuestionAdapter> deleteColumn; // Value injected by FXMLLoader

    @FXML
    void addTestMouseClicked(MouseEvent event) {
        CreateQuestionWindow window = new CreateQuestionWindow(new Stage());
        try {
            Question question = window.showWindow();
            //System.out.println(question);
            test.add(question);
            //tableData.add(new QuestionAdapter(question));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openTestMouseClicked(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(SUPPORTED_FILES, TestReader.SUPPORTED_FORMAT));

        chooser.setTitle(SELECT_FILE_TO_LOAD);

        File newFile = chooser.showOpenDialog(stage);

        if (newFile == null) {
            return;
        }

        TestReader reader = new TestReader();
        try {
            Test testRead = reader.open(newFile);
            test.add(testRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resultButtonClicked(MouseEvent event) {
        if (dao == null){
            setIpAndPort();
        }

        ResultsWindow resultsWindow = new ResultsWindow(new Stage());
        Results results;
        try {
            results = dao.getResults();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
        try {
            resultsWindow.showWindow(test, results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveTestMouseClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("output.json");
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File newFile = fileChooser.showSaveDialog(stage);

        if (newFile == null) {
            return;
        }

        try {
            TestSaver saver = new TestSaver();
            saver.save(test, newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void uploadTestMouseClicked(MouseEvent event) {
        if (dao == null){
            setIpAndPort();
        }

        try {
            dao.sendTest(test);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }

    void setIpAndPort(){
        if (ipTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Input ip address");
            alert.showAndWait();
            return;
        }
        String ip = ipTextField.getText();
        int port;
        try {
            port = Integer.parseInt(portTextField.getText());
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("Incorect port");
            alert.showAndWait();
            return;
        }

        dao = new UdpDao(ip, port);
    }

    void deleteButtonClicked(QuestionAdapter questionAdapter){
        test.remove(questionAdapter.getQuestion());
    }

    void editButtonClicked(QuestionAdapter questionAdapter){
        CreateQuestionWindow window = new CreateQuestionWindow(new Stage());
        try {
            window.showWindow(questionAdapter.getQuestion());
            table.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        questionColumn.setCellValueFactory(
                new PropertyValueFactory<>("textQuestion")
        );
        editColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        editColumn.setCellFactory(param->  new TableCell<QuestionAdapter, QuestionAdapter>() {
            Button btn;

            @Override
            public void updateItem(QuestionAdapter questionAdapter, boolean empty) {
                super.updateItem(questionAdapter, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn = questionAdapter.getEditButton();
                    btn.setOnAction(event -> {
                        editButtonClicked(questionAdapter);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
        deleteColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        deleteColumn.setCellFactory(param->  new TableCell<QuestionAdapter, QuestionAdapter>() {
            Button btn;

            @Override
            public void updateItem(QuestionAdapter questionAdapter, boolean empty) {
                super.updateItem(questionAdapter, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn = questionAdapter.getDeleteButton();
                    btn.setOnAction(event -> {
                        deleteButtonClicked(questionAdapter);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
        table.setItems(tableData.getTableData());
    }
}

Листинг исходного кода файла src\sample\windows\admin\table\QuestionAdapter.java

package sample.windows.admin.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import sample.entity.test.Question;


public class QuestionAdapter {
    private Question question;
    private Button editButton = new Button("Edit");
    private Button deleteButton = new Button("Delete");

    public QuestionAdapter(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public SimpleStringProperty textQuestionProperty() {
        return new SimpleStringProperty(question.getTextQuestion());
    }
}


Листинг исходного кода файла src\sample\windows\admin\table\TableData.java

package sample.windows.admin.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.test.Question;

import java.util.ArrayList;

public class TableData {
    ObservableList<QuestionAdapter> tableData = FXCollections.observableList(new ArrayList<>());

    public TableData() {
    }

    public ObservableList<QuestionAdapter> getTableData() {
        return tableData;
    }

    public void add(QuestionAdapter questionAdapter){
        tableData.add(questionAdapter);
    }

    public void remove(Question question){
        for (QuestionAdapter questionAdapter : tableData){
            if (questionAdapter.getQuestion() == question){
                tableData.remove(questionAdapter);
                break;
            }
        }
    }

    public QuestionAdapter find(int id){
        return tableData.get(id);
    }
}


Листинг исходного кода файла src\sample\windows\admin\AdminWindow.java

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


Листинг исходного кода файла src\sample\windows\create_question\controller\CreateQuestionController.java

package sample.windows.create_question.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.entity.test.Question;

public class CreateQuestionController {

    private Stage stage;
    private Question question;

    public void setStage(Stage stage) {
        this.stage = stage;
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
    void endButtonClicked(MouseEvent event) {

    }

    public void setQuestion(Question question){
        this.question = question;
        questionTextArea.setText(question.getTextQuestion());
        questionTextArea.textProperty().addListener((observable, oldValue, newValue)->{
            question.setTextQuestion(newValue);
        });
        firstQuestionTextField.setText(question.findAnswer(0).getText());
        firstQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(0).setText(newValue);
        });
        firstQuestionCheckbox.setSelected(question.findAnswer(0).isTrue());
        firstQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(0).setTrue(newValue);
        });
        secondQuestionTextField.setText(question.findAnswer(1).getText());
        secondQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(1).setText(newValue);
        });
        secondQuestionCheckbox.setSelected(question.findAnswer(1).isTrue());
        secondQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(1).setTrue(newValue);
        });
        thirdQuestionTextField.setText(question.findAnswer(2).getText());
        thirdQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(2).setText(newValue);
        });
        thirdQuestionCheckbox.setSelected(question.findAnswer(2).isTrue());
        thirdQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(2).setTrue(newValue);
        });
        fourthQuestionTextField.setText(question.findAnswer(3).getText());
        fourthQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(3).setText(newValue);
        });
        fourthQuestionCheckbox.setSelected(question.findAnswer(3).isTrue());
        fourthQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(3).setTrue(newValue);
        });
        fifthQuestionTextField.setText(question.findAnswer(4).getText());
        fifthQuestionTextField.textProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(4).setText(newValue);
        });
        firstQuestionCheckbox.setSelected(question.findAnswer(4).isTrue());
        firstQuestionCheckbox.selectedProperty().addListener((observable, oldValue, newValue)->{
            question.findAnswer(4).setTrue(newValue);
        });
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



Листинг исходного кода файла src\sample\windows\create_question\CreateQuestionWindow.java

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


Листинг исходного кода файла src\sample\windows\results\controller\ResultsController.java

/**
 * Sample Skeleton for 'resultView.fxml' Controller Class
 */

package sample.windows.results.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.entity.results.Result;
import sample.entity.results.Results;
import sample.entity.test.Test;
import sample.service.TestService;

public class ResultsController {

    Stage stage;
    ObservableList<Result> tableData;
    Test test;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<Result> table; // Value injected by FXMLLoader

    @FXML // fx:id="usernameColumn"
    private TableColumn<Result, Result> usernameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="markColumn"
    private TableColumn<Result, Result> markColumn; // Value injected by FXMLLoader

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setResults(Results results){
        tableData = FXCollections.observableList(results.toList());
        table.setItems(tableData);
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'resultView.fxml'.";
        assert usernameColumn != null : "fx:id=\"usernameColumn\" was not injected: check your FXML file 'resultView.fxml'.";
        assert markColumn != null : "fx:id=\"markColumn\" was not injected: check your FXML file 'resultView.fxml'.";
        usernameColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        usernameColumn.setCellFactory(param -> new TableCell<Result, Result>(){
                @Override
                public void updateItem(Result result, boolean empty) {
                    super.updateItem(result, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setGraphic(new Label(result.getOwner()));
                        setText(null);
                    }
                }
            }
        );
        markColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        markColumn.setCellFactory(param -> new TableCell<Result, Result>(){
            @Override
            public void updateItem(Result result, boolean empty) {
                super.updateItem(result, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    TestService testService = new TestService();
                    Label label = new Label(Double.toString(testService.getMark(test, result.getTest())));
                    setGraphic(label);
                    setText(null);
                }
            }
        });

    }
}




Листинг исходного кода файла src\sample\windows\results\ResultsWindow.java

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


Листинг исходного кода файла src\sample\Main.java

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


Листинг исходного кода файла generated_source_text.java



