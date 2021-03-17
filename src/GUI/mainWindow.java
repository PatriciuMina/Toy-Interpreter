package GUI;


import Controller.Controller;
import Model.ProgramState.PrgState;
import Model.Value.Value;
import Model.Value.stringValue;
import Model.Value.*;
import View.Command;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;


import java.io.BufferedReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class mainWindow implements Initializable {

    private Command program;
    private Controller controller;

    @FXML
    private TextField nrProgramsField;

    @FXML
    private Button startButton;

    @FXML
    private ListView<String> outPut;

    @FXML
    private ListView<String> exeStack;

    @FXML
    private ListView<Integer> programID;

    @FXML
    private TableView<Map.Entry<Integer, Value>> heapTable;
    @FXML
    private TableColumn<Map.Entry<Integer,Value> , String> heapAddress;
    @FXML
    private TableColumn<Map.Entry<Integer,Value> , String> heapValue;

    @FXML
    private TableView<Map.Entry<stringValue, BufferedReader>> fileTable;
    @FXML
    private TableColumn<Map.Entry<stringValue,BufferedReader> ,String> fileTableFd;
    @FXML
    private TableColumn<Map.Entry<stringValue,BufferedReader> , String> fileName;

    @FXML
    private TableView<Map.Entry<String,Value>> symTable;
    @FXML
    private TableColumn<Map.Entry<String,Value>,String> symTableVar;
    @FXML
    private TableColumn<Map.Entry<String,Value> , String> symTableVal;


    @FXML
    private TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphoreTable;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> semaphoreIndex;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> semaphoreNumber;

    @FXML
    private TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> semaphoreList;

    public mainWindow(Command prg, Controller ctrll)
    {
        program = prg;
        controller = ctrll;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heapAddress.setCellValueFactory(data -> new ReadOnlyStringWrapper(Integer.toString(data.getValue().getKey())));
        heapValue.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getValue().toString()));

        fileName.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getKey().toString()));
        fileTableFd.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getValue().toString()));

        symTableVar.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getKey()));
        symTableVal.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getValue().toString()));


        semaphoreIndex.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getKey()));
        semaphoreNumber.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getKey()));
        semaphoreList.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getValue()));
    }

    @FXML
    void startProgram(javafx.event.ActionEvent event)
    {
        if(controller.getRepository().getPrgList().size() == 0)
        {
            program.reset();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("-");
            alert.setHeaderText(null);
            alert.setContentText("Program finished");
            alert.show();
            return;
        }

        try
        {
            controller.runOneStep();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Exception");
            alert.setHeaderText("Exception");
            alert.setContentText(exception.getMessage());
            alert.show();
        }

        if(controller.getRepository().getPrgList().size() > 0)
        {
            populate();
        }
        controller.getRepository().setPrgList(controller.removeCompletedPrg(controller.getRepository().getPrgList()));
        populateProgramId();

    }

    private void populate() {
        setTextF();
        populateHeapTable();
        populateOutputTable();
        populateFileT();
        populateProgramId();
        if(programID.getSelectionModel().getSelectedItem() == null)
        {
            programID.getSelectionModel().selectFirst();
        }
        populateSymTable();
        populateExeStack();
        populateSemaphoreTable();
    }

    private void populateFileT() {
        ObservableList<Map.Entry<stringValue,BufferedReader>> fileList = FXCollections.observableArrayList();
        fileList.addAll(controller.getRepository().getPrgList().get(0).getFileTable().getElements().entrySet());
        fileTable.setItems(fileList);
        fileTable.refresh();
    }

    private void populateOutputTable() {
        ObservableList<String> outList = FXCollections.observableArrayList();
        controller.getRepository().getPrgList().get(0).getOut().getElements().forEach(out->outList.add(out.toString()));
        outPut.setItems(outList);
    }

    private void populateHeapTable() {
        ObservableList<Map.Entry<Integer,Value>> heapList = FXCollections.observableArrayList();
        heapList.addAll(controller.getRepository().getPrgList().get(0).getHeap().getContent().entrySet());
        heapTable.setItems(heapList);
        heapTable.refresh();
    }

    private void setTextF() {
        nrProgramsField.setText(String.valueOf(controller.getRepository().getPrgList().size()));
    }

    private void populateProgramId() {
        ObservableList<Integer> idList = FXCollections.observableArrayList();
        idList.addAll(controller.getRepository().getPrgList().stream().map(programState -> programState.getId()).collect(Collectors.toList()));
        programID.setItems(idList);
    }

    @FXML
    void changeSelectedThread(MouseEvent event)
    {
        populateSymTable();
        populateExeStack();
    }

    private void populateExeStack() {
        Integer currProgramID = programID.getSelectionModel().getSelectedItem();
        PrgState currProgram = controller.getRepository().getPrgList().stream().filter(programState -> programState.getId() == currProgramID).collect(Collectors.toList()).get(0);

        ObservableList<String> stackList = FXCollections.observableArrayList(currProgram.getExeStack().getElements().stream().map(exeStack ->exeStack.toString()).collect(Collectors.toList()));
        exeStack.setItems(stackList);
    }

    private void populateSymTable() {
        Integer currProgramID = programID.getSelectionModel().getSelectedItem();
        PrgState currProgram = controller.getRepository().getPrgList().stream().filter(programState -> programState.getId() == currProgramID).collect(Collectors.toList()).get(0);

        ObservableList<Map.Entry<String,Value>> list = FXCollections.observableArrayList();
        list.addAll(currProgram.getSymTable().getContent().entrySet());
        symTable.setItems(list);
        symTable.refresh();

    }

    public void populateSemaphoreTable(){
        ObservableList<Map.Entry<Integer, Pair<Integer, List<Integer>>>> semaphoreTableList = FXCollections.observableArrayList();
        semaphoreTableList.addAll(controller.getRepository().getPrgList().get(0).getSemaphore().getContent().entrySet());
        semaphoreTable.setItems(semaphoreTableList);
        semaphoreTable.refresh();
    }
}
