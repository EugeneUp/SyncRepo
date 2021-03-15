package lesson7.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lesson7.client.ChatGB;
import lesson7.client.models.Network;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class ChatController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Label usernameTitle;

    private Network network;
    private String selectedRecipient;

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(ChatGB.USERS_TEST_DATA));
        sendButton.setOnAction(event -> ChatController.this.sendMessage());
        textField.setOnAction(event -> ChatController.this.sendMessage());

        usersList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = usersList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                usersList.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell;
        });
    }

    private void sendMessage() {
        String message = textField.getText().trim();

        if (message.length() == 0) {
            return;
        }
        appendMessage("Я: " + message);
        textField.clear();

        try {
            if (selectedRecipient != null) {
                network.sendPrivateMessage(message, selectedRecipient);
            } else {
                network.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!Ошибка при отправке сообщения");
        }

    }

    public void appendMessage(String message) {
        String timeStamp = DateFormat.getInstance().format(new Date());


        chatHistory.appendText(timeStamp);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(System.lineSeparator());
    }

    public void setUsernameTitle(String username) {
        this.usernameTitle.setText(username);
    }
}