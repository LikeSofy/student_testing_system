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