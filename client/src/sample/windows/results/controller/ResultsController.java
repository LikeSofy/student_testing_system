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


