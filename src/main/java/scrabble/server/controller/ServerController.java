package scrabble.server.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrabble.server.model.ServerModel;
import scrabble.server.network.ServerNetworkHandler;

import java.io.IOException;

public class ServerController {
    @FXML
    private TextField portField;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private ListView<String> logArea;

    private ServerModel model;
    private ServerNetworkHandler networkHandler;

    private boolean isRunning;

     private final ObservableList<String> logMessages;

    public ServerController() {
        this.isRunning = false;
        logMessages = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        portField.setText("5555");
        logArea.setItems(logMessages);
    }

    public void setPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Server of WordPot");
        primaryStage.setOnCloseRequest(e -> stopServer());
    }

    @FXML
    private void handleStartServer() {
        if (!isRunning) {
            try {
                this.model = new ServerModel();
                int port = Integer.parseInt(portField.getText());
                this.networkHandler = new ServerNetworkHandler(model);
                networkHandler.start(port);
                isRunning = true;
                log("Server started on port " + port);
                startButton.setDisable(true);
                stopButton.setDisable(false);
            } catch (NumberFormatException e) {
                log("Enter correct port number");
                isRunning = false;
            } catch (IOException e) {
                log("Error while starting the server: " + e.getMessage());
                isRunning = false;
            }
        }
    }

    @FXML
    private void handleStopServer() {
        stopServer();
        startButton.setDisable(false);
        stopButton.setDisable(true);
    }

    public void stopServer() {
        if (isRunning && networkHandler != null) {
            networkHandler.stop();
            this.model = null;
            isRunning = false;
            log("Server stopped");
        }
    }

     private void log(String message) {
        logMessages.add(message);
        System.out.println("[SERVER] " + message);
        logArea.scrollTo(logMessages.size() - 1);
    }

    public boolean isRunning() {
        return isRunning;
    }
}